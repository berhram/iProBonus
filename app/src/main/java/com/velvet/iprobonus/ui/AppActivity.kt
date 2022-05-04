package com.velvet.iprobonus.ui

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.velvet.iprobonus.App
import com.velvet.iprobonus.databinding.ActivityMainBinding
import com.velvet.ui.BonusFragment

class AppActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        //Font sizes in sp, color in hex
        val fragment = BonusFragment.newInstance(24f, 16f, Color.parseColor("#D2333E"))
        supportFragmentManager.beginTransaction().add(binding!!.fragmentContainer.id, fragment).commit()
        val viewModel: AppViewModel by viewModels {
            (applicationContext as App).component!!.appViewModelFactory()
        }
        viewModel.currentQuantity.observe(this) {
            fragment.setBonusQuantity(it)
        }
        viewModel.expirationDate.observe(this) {
            fragment.setExpirationDate(it)
        }
        viewModel.expirationQuantity.observe(this) {
            fragment.setExpirationQuantity(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}