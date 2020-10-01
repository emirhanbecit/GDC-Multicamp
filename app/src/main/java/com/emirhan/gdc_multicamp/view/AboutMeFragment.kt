package com.emirhan.gdc_multicamp.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.emirhan.gdc_multicamp.R
import com.emirhan.gdc_multicamp.utils.Constants
import kotlinx.android.synthetic.main.detail_bar.*
import kotlinx.android.synthetic.main.fragment_about_me.*


class AboutMeFragment : Fragment(R.layout.fragment_about_me) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iv_google_logo.visibility = View.INVISIBLE
        action_bar_title.setText(R.string.about_me)


        iv_back.setOnClickListener {
            //custom barımızda yer alan geri butonuna tıklandığında gerçekleşecek işlemi burada belirtiyoruz.
            activity?.onBackPressed()
        }

        iv_profile.load(R.drawable.me)

        btn_mail.setOnClickListener {
            val mailto = "mailto:${Constants.DEVELOPER_MAIL}" +
                    "?cc=" + "" +
                    "&subject=" + Uri.encode("GDG Multicamp")

            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse(mailto)

            try {
                startActivity(Intent.createChooser(emailIntent, getString(R.string.choose_mail_app)));
            }catch (ex : ActivityNotFoundException){
                Toast.makeText(context, R.string.not_found_mail_app, Toast.LENGTH_SHORT)
                    .show();
            }
        }

        btn_linkedin.setOnClickListener {
            goLink(Constants.DEVELOPER_LINKEDIN,Constants.LINKEDIN)
        }

        btn_instagram.setOnClickListener {
            goLink(Constants.DEVELOPER_INSTAGRAM,Constants.INSTAGRAM)
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