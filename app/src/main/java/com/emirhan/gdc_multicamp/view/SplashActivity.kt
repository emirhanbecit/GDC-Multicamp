package com.emirhan.gdc_multicamp.view

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.emirhan.gdc_multicamp.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Uygulamamızın daha güvenli çalışması için internet bağlantısı kontrolünü uygulama açılır açılmaz yapıyoruz ->
        val cm =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true


        if (isConnected) {
            // Eğer internet bağlantısı var ise, splash ekranını kapatıp asıl aktivite ekranını gösteriyoruz.
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {

            val alert = AlertDialog.Builder(this)
                // Başlığımızı belirliyoruz ->
                .setTitle(R.string.warning)
                // Ortada yer alacak mesaj metnini belirtiyoruz ->
                .setMessage(R.string.connection_failed)
                //Geri tuşununu pasif hale getiriyoruz ->
                .setCancelable(false)
                //Gösterilecek olan ikonumuzu belirliyoruz ->
                .setIcon(R.drawable.ic_connection)
                // Butonumuzu oluşturup, yer alacak metnini ve tıklanması durumunda olacakları belirliyoruz
                .setPositiveButton(R.string.okey) { dialogInterface: DialogInterface, i: Int ->
                    //Butona tıklanıldığı zaman uygulamayı kapatıyoruz ->
                    finish()
                }
            alert.show() // AlertDialog' un gösterilebilmesi için "show()" methodunu çağırıyoruz.

        }

    }
}