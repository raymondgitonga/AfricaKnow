package com.tosh.africaknow.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tosh.africaknow.R
import com.tosh.africaknow.view.fragment.GameStart

class MainActivity : AppCompatActivity() {


    private val gameStartFragment = GameStart()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createFormFragment()
    }


    private fun createFormFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFragment, gameStartFragment)
        transaction.commit()
    }
}
