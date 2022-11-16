package com.example.courotinexretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.courotinexretrofit.databinding.ActivityMainBinding
import com.example.courotinexretrofit.ui.PopularMovie.PopularMovieFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<PopularMovieFragment>(R.id.fragmentContainer)
            }
        }
    }
}