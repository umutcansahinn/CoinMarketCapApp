package com.umutcansahin.coinmarketcapapp.ui.detail

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.umutcansahin.coinmarketcapapp.base.BaseFragment
import com.umutcansahin.coinmarketcapapp.databinding.FragmentDetailBinding
import com.umutcansahin.coinmarketcapapp.model.detail.CoinDetail
import com.umutcansahin.coinmarketcapapp.model.detail.DetailResponse
import com.umutcansahin.coinmarketcapapp.utils.Constants.API_KEY
import com.umutcansahin.coinmarketcapapp.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray
import org.json.JSONObject

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding,DetailViewModel>(
    FragmentDetailBinding::inflate
) {
    override val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateFinished() {
        viewModel.getDetail(API_KEY,args.symbol)
    }

    override fun initializeListeners() {

    }

    override fun observeEvents() {
        with(viewModel) {
            detailResponce.observe(viewLifecycleOwner, Observer {
                parseData(it)
            })
            isLoading.observe(viewLifecycleOwner, Observer {
                handleView(it)
            })
            onError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun parseData(it: DetailResponse?) {
        val gson = Gson()
        val json = gson.toJson(it?.data)
        val jsonObject = JSONObject(json)
        val jsonArray = jsonObject[args.symbol] as JSONArray

        val coin = gson.fromJson(jsonArray.getJSONObject(0).toString(), CoinDetail::class.java)

        coin?.let {
            with(binding) {
                imageViewDetail.loadImage(it.logo)
                textViewDetailTitle.text = it.name
                textViewDetailSeymbol.text = it.symbol
                textViewDetailDescription.text = it.description
            }
        }
    }

    private fun handleView(isLoading: Boolean = false) {
        binding.detailGroup.isVisible = !isLoading
        binding.progressBarDetail.isVisible = isLoading
    }
}