package com.example.android3

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.android3.databinding.ActivityCardBinding

class CardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        title = "Notifications"

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        toolbar.navigationIcon?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                colorFilter = BlendModeColorFilter(Color.BLACK, BlendMode.SRC_IN)
            } else {
                setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)
            }
        }

        val intent = intent
        val putImage = intent.getIntExtra("putImage", 0)
        val putName = intent.getStringExtra("putName")
        val putDate = intent.getStringExtra("putDate")
        val putGender = intent.getStringExtra("putGender")
        val putDesc = intent.getStringExtra("putDesc")

        binding.imgCard.setImageResource(putImage)
        binding.nameCard.text = putName
        binding.dateCard.text = putDate
        binding.genderCard.text = putGender
        binding.descCard.text = putDesc
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}