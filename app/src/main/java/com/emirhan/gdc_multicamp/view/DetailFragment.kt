package com.emirhan.gdc_multicamp.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.emirhan.gdc_multicamp.R
import com.emirhan.gdc_multicamp.utils.Constants
import kotlinx.android.synthetic.main.detail_bar.*
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args by navArgs<DetailFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val post = args.Post // NavArgs' dan gelen değeri oluşturduğumuz nesneye atıyoruz.

        action_bar_title.text=post.name
        description.text = post.description
        tv_leader.text = post.leader?.name
        iv_leader.load(post.leader?.photo){
            placeholder(R.drawable.ic_person)
        }


        iv_instagram.setOnClickListener {
            goLink(post.links?.instagram,Constants.INSTAGRAM)
        }

        iv_twitter.setOnClickListener {
            goLink(post.links?.twitter,Constants.TWITTER)
        }

        iv_youtube.setOnClickListener {
            goLink(post.links?.youtube,Constants.YOUTUBE)
        }

        iv_participation.setOnClickListener {
            goLink(post.links?.participation,"")
        }


        iv_back.setOnClickListener {
            activity?.onBackPressed()
        }


    }






     fun goLink(link: String?, packageName: String) {
        // verilen linke gidecek method->

        try {
            // Eğer link'in açılacağı uygulama cihazda yüklü ise öncelikle ona yönlendiriyoruz ->
            startActivity(
                Intent(Intent.ACTION_VIEW,Uri.parse(link))
                    .setPackage(packageName)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        } catch (e: ActivityNotFoundException) {
            // Eğer uygulama yüklü değil ise tarayıcıda açıyoruz ->
            startActivity(
                Intent(
                    Intent.ACTION_VIEW, Uri.parse(link)
                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }
}