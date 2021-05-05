package com.anupras.apl.warehouseapplicationproject.ui.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.anupras.apl.warehouseapplicationproject.R
import dagger.hilt.android.AndroidEntryPoint


class MainFragment : Fragment(), View.OnClickListener {
    lateinit var navController: NavController

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    //do what you want here
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.scan_button).setOnClickListener(this)
        view.findViewById<Button>(R.id.search_button).setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.scan_button -> navController!!.navigate(R.id.action_mainFragment_to_scanFragment)
            R.id.search_button -> navController!!.navigate(R.id.action_mainFragment_to_searchFragment)
        }
    }

}