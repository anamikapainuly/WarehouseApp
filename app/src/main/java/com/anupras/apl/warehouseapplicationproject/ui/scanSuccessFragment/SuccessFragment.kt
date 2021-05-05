package com.anupras.apl.warehouseapplicationproject.ui.scanSuccessFragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.anupras.apl.warehouseapplicationproject.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_success.view.*


@AndroidEntryPoint
class SuccessFragment : Fragment() {
    private lateinit var successViewModel: SuccessViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_success, container, false)
        successViewModel = ViewModelProvider(this).get(SuccessViewModel::class.java)

        val safeArgs: SuccessFragmentArgs by navArgs()
        val code = safeArgs.code

        successViewModel.getProductPrice(code.toString())


        v.fragment_success_button_back_to_scanner.setOnClickListener {
            findNavController().navigateUp()
        }



        successViewModel.getProductDetail()!!.observe(
            viewLifecycleOwner,
            Observer { productDetail ->
                if (productDetail?.Found == "N") {
                    v.fragment_success_text_view_code.text = "$code \n Product Not Found"
                } else {
                    v.fragment_success_text_view_code.text = "Product Found\n " +
                            "${productDetail?.Products?.Barcode}\n" +
                            "$ ${productDetail?.Products?.Price?.Price}\n" +
                            "${productDetail?.Products?.ItemDescription} "

                    //Setting image url
                    val options: RequestOptions = RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher_round)
                        .error(R.mipmap.ic_launcher_round)

                    Glide.with(this).load(productDetail.Products?.ImageURL).apply(options)
                        .into(v.image_product)

                }
            })

        return v
    }

}