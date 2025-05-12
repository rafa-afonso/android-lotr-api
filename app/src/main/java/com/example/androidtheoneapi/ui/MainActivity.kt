package com.example.androidtheoneapi.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.androidtheoneapi.databinding.ActivityMainBinding
import com.example.androidtheoneapi.util.Resource
import com.example.androidtheoneapi.viewmodel.TheOneApiViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TheOneApiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView = binding.textView
        val button = binding.button
        button.setOnClickListener {
            viewModel.getBooks()
        }

        viewModel.books.observe(this, Observer { response ->
            when (response) {
                is Resource.Success<*> -> {
                    response.data?.let { responseData ->
                        textView.text = responseData.toString()
                    }
                }

                is Resource.Error<*> -> {
                    response.message?.let { message ->
                        Toast.makeText(this, "Sorry, error $message", Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading<*> -> return@Observer
            }
        })
    }
}