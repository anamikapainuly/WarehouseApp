package com.anupras.apl.warehouseapplicationproject.ui.detailFragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.anupras.apl.warehouseapplicationproject.R
import com.anupras.apl.warehouseapplicationproject.databinding.DetailFragmentBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class DetailFragment : Fragment(R.layout.detail_fragment) {


    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val binding = DetailFragmentBinding.bind(view)

        binding.apply {
            val product = args.product

            Glide.with(view)
                .load(product.Products[0].ImageURL)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        textViewCreator.isVisible = true
                        textViewDescription.isVisible = product.Description != null
                        return false
                    }
                })
                .into(imageView)

            textViewDescription.text = product.Description

            textViewCreator.apply {
                text = product.Description

            }
        }
    }
}