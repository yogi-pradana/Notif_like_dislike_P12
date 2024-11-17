package com.example.notifku

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationCompat

class NotifReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        val sharedPref = context?.getSharedPreferences("notifku_prefs", Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()

        when (action) {
            "ACTION_SUKA" -> {
                val countSuka = sharedPref?.getInt("count_suka", 0) ?: 0
                editor?.putInt("count_suka", countSuka + 1)?.apply()
                Toast.makeText(context, "Terima Kasih atas Sukanya! KYAAAA!", Toast.LENGTH_SHORT).show()
            }
            "ACTION_TIDAK_SUKA" -> {
                val countTidakSuka = sharedPref?.getInt("count_tidak_suka", 0) ?: 0
                editor?.putInt("count_tidak_suka", countTidakSuka + 1)?.apply()
                Toast.makeText(context, "Matamu Picek Ta?", Toast.LENGTH_SHORT).show()
            }
        }

        // Memanggil updateCounters dari instance MainActivity
        MainActivity.instance?.updateCounters()
    }

}