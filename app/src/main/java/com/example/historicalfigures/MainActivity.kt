package com.example.historicalfigures

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.historicalfigures.adapter.HistoricalFiguresAdapter
import com.example.historicalfigures.databinding.ActivityMainBinding
import com.example.historicalfigures.network.ApiClient
import com.example.historicalfigures.network.ApiService
import com.example.historicalfigures.model.HistoricalFigure
import kotlinx.coroutines.launch
import retrofit2.await
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val apiService: ApiService = ApiClient.retrofit.create(ApiService::class.java)
    private val adapter = HistoricalFiguresAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter


        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    fetchFigures(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    fetchFigures(newText)
                }
                return true
            }
        })


        fetchFigures("julius caesar")
    }

    private fun fetchFigures(query: String) {
        lifecycleScope.launch {
            val response = apiService.getFigures(query)
            Log.d("MainActivity", "Response: $response")
            if (response.isSuccessful) {
                val figures = response.body()
                figures?.let {
                    adapter.submitList(it)
                }
            } else {
                Toast.makeText(this@MainActivity, "Ошибка при загрузке данных", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

