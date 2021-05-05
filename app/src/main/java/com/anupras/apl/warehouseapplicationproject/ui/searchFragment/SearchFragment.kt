package com.anupras.apl.warehouseapplicationproject.ui.searchFragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.anupras.apl.warehouseapplicationproject.R
import com.anupras.apl.warehouseapplicationproject.data.Results
import com.anupras.apl.warehouseapplicationproject.databinding.SearchFragmentBinding
import com.anupras.apl.warehouseapplicationproject.ui.searchFragment.adapter.OnItemClickListener
import com.anupras.apl.warehouseapplicationproject.ui.searchFragment.adapter.WarehouseSearchProductAdapter
import com.anupras.apl.warehouseapplicationproject.ui.searchFragment.adapter.WarehouseSearchProductLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.search_fragment),OnItemClickListener {

    private val viewModel by viewModels<SearchViewModel>()

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!
    val adapter = WarehouseSearchProductAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = SearchFragmentBinding.bind(view)
        
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = WarehouseSearchProductLoadStateAdapter { adapter.retry() },
                footer = WarehouseSearchProductLoadStateAdapter { adapter.retry() },
            )
            retryButton.setOnClickListener {
                adapter.retry()
            }
        }



        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                retryButton.isVisible = loadState.source.refresh is LoadState.Error
                errorText.isVisible = loadState.source.refresh is LoadState.Error

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1) {
                    recyclerView.isVisible = false
                    noResultText.isVisible = true
                } else {
                    noResultText.isVisible = false
                }
            }
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)

        //Search Functionality

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null) {

                    viewModel.product.observe(viewLifecycleOwner) {
                        adapter.submitData(viewLifecycleOwner.lifecycle, it)
                    }
                    //Binding on search for recycler view
                    binding.recyclerView.scrollToPosition(0)
                    viewModel.searchProduct(query)

                    //Hide keyboard
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(result: Results) {
       val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(result)
        findNavController().navigate(action)
    }
}