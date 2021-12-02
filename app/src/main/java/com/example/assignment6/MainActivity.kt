package com.example.assignment6

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.net.Uri.encode
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.assignment6.databinding.ActivityMainBinding
import java.net.URI

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonMapit.setOnClickListener(
            MapItListener()
        )

        binding.buttonReset.setOnClickListener(
            ResetListener()
        )
    }

    inner class ResetListener : View.OnClickListener {
        override fun onClick(p0: View?) {
            val builder = AlertDialog.Builder(binding.root.context)
            val listener = DialogInterface.OnClickListener { p0, which ->
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    binding.editStreetnumber.setText("")
                    binding.editStreetname.setText("")
                    binding.editCity.setText("")
                    binding.editState.setText("")
                }
            }

            builder
                .setTitle(R.string.dialogue_title)
                .setMessage(R.string.dialogue_confirm)
                .setIcon(R.drawable.ic_baseline_info_24)
                .setPositiveButton(android.R.string.ok, listener)
                .setNegativeButton(android.R.string.cancel, listener)
                .show()
        }
    }

    inner class MapItListener : View.OnClickListener {
        override fun onClick(p0: View?) {
            val streetNumber = binding.editStreetnumber.getText().toString().trim()
            val streetName = binding.editStreetname.getText().toString().trim()
            val city = binding.editCity.getText().toString().trim()
            val state = binding.editState.getText().toString().trim()

            if (streetNumber.length == 0) {
                val builder = AlertDialog.Builder(binding.root.context)
                builder
                    .setTitle(R.string.error_title)
                    .setMessage(R.string.missing_street_number)
                    .setIcon(R.drawable.ic_baseline_warning_24)
                    .setPositiveButton(android.R.string.ok, null)
                    .show()
            } else if (streetName.length == 0) {
                val builder = AlertDialog.Builder(binding.root.context)
                builder
                    .setTitle(R.string.error_title)
                    .setMessage(R.string.missing_street_name)
                    .setIcon(R.drawable.ic_baseline_warning_24)
                    .setPositiveButton(android.R.string.ok, null)
                    .show()
            } else if (city.length == 0) {
                val builder = AlertDialog.Builder(binding.root.context)
                builder
                    .setTitle(R.string.error_title)
                    .setMessage(R.string.missing_city)
                    .setIcon(R.drawable.ic_baseline_warning_24)
                    .setPositiveButton(android.R.string.ok, null)
                    .show()
            } else if (state.length == 0) {
                val builder = AlertDialog.Builder(binding.root.context)
                builder
                    .setTitle(R.string.error_title)
                    .setMessage(R.string.missing_state)
                    .setIcon(R.drawable.ic_baseline_warning_24)
                    .setPositiveButton(android.R.string.ok, null)
                    .show()
            } else {
                val location = Uri.encode("${streetNumber} ${streetName} ${city} ${state}")
                val uriString = "geo:0,0?q=${location}"
                val uri = Uri.parse(uriString)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }
    }
}