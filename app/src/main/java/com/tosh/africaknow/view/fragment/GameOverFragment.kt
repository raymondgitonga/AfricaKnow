package com.tosh.africaknow.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tosh.africaknow.R
import com.tosh.africaknow.utils.getSharedPreferencesValue
import kotlinx.android.synthetic.main.fragment_game_over.*

class GameOverFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_over, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scoreFinal.text = "SCORE: ${getSharedPreferencesValue(activity!!, "SCORE")}"
    }
}
