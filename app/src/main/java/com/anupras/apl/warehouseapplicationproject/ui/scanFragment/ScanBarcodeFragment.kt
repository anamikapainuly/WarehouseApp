package com.anupras.apl.warehouseapplicationproject.ui.scanFragment

import android.Manifest.permission.CAMERA
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.anupras.apl.warehouseapplicationproject.R
import com.blackfrogweb.barcodescannersample.BarcodeAnalyzer
import kotlinx.android.synthetic.main.scan_barcode_fragment.*
import kotlinx.android.synthetic.main.scan_barcode_fragment.view.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

typealias BarcodeListener = (barcode: String) -> Unit

class ScanBarcodeFragment : Fragment() {

    private var processingBarcode = AtomicBoolean(false)
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var scanBarcodeViewModel: ScanBarcodeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraExecutor = Executors.newSingleThreadExecutor()
        scanBarcodeViewModel = ViewModelProvider(this).get(ScanBarcodeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.scan_barcode_fragment, container, false)

        scanBarcodeViewModel.progressState.observe(viewLifecycleOwner, {
            v.fragment_scan_barcode_progress_bar.visibility = if (it) View.VISIBLE else View.GONE
        })

        scanBarcodeViewModel.navigation.observe(viewLifecycleOwner, { navDirections ->
            navDirections?.let {
                findNavController().navigate(navDirections)
                scanBarcodeViewModel.doneNavigating()
            }
        })

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (allPermissionsGranted()) {
            startCamera()
        } else {

            requestPermissions(
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
    }


    override fun onResume() {
        super.onResume()
        processingBarcode.set(false)
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())


        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(
                    fragment_scan_barcode_preview_view.surfaceProvider
                )
            }
            // Setup the ImageAnalyzer for the ImageAnalysis use case
            val imageAnalysis = ImageAnalysis.Builder()
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, BarcodeAnalyzer { barcode ->
                        if (processingBarcode.compareAndSet(false, true)) {
                            searchBarcode(barcode)
                        }
                    })
                }

            // Select back camera
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                // Unbind any bound use cases before rebinding
                cameraProvider.unbindAll()
                // Bind use cases to lifecycleOwner
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis)
            } catch (e: Exception) {
                Log.e("PreviewUseCase", "Binding failed! :(", e)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun searchBarcode(barcode: String) {
        scanBarcodeViewModel.searchBarcode(barcode)
    }

    override fun onDestroy() {
        cameraExecutor.shutdown()
        super.onDestroy()
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}