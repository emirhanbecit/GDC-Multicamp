package com.emirhan.gdc_multicamp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.emirhan.gdc_multicamp.R
import com.emirhan.gdc_multicamp.adapter.RecyclerAdapter
import com.emirhan.gdc_multicamp.service.RetrofitProvider

import com.emirhan.gdc_multicamp.model.Post
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.main_bar.*


class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Data'yı coroutine kullanarak çekiyoruz -> Bunun sebebi kitlenmeleri engellemek için data çekme işlemlerini async olarak gerçekleştirmektir.
        lifecycleScope.launchWhenCreated {
            val response : List<Post> = RetrofitProvider.newsApi.getTopHeadlines()  // -> Post listemizi retrofit ile internetten çekiyoruz.


            reycler_view.adapter = RecyclerAdapter(response.toMutableList()){
                // Şuan burada onClick methodu içerisindeyiz
                // Layoutumuza tıklandığında model'imizi alarak detay fragmanına gidiyoruz ->
                val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                findNavController().navigate(direction)
            }
            progressBar.visibility = View.INVISIBLE // -> Yükleme işlemi tamamlandıktan sonra progress bar'ı gizliyoruz.

        }

        iv_multicamp.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW, Uri.parse("https://kommunity.com/developer-multicamp")
                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }

        iv_info.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToİnfoFragment()
            findNavController().navigate(direction)
        }


    }
}