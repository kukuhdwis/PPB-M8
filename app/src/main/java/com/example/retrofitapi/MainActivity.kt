package com.example.retrofitapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitapi.API.ApiClient
import com.example.retrofitapi.API.ApiResponse
import com.example.retrofitapi.API.Mahasiswa
import com.example.retrofitapi.Adapter.RvAdapter
import com.example.retrofitapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RvAdapter(this@MainActivity, arrayListOf())
        binding.rvMain.adapter = adapter
        binding.rvMain.setHasFixedSize(true)

        remoteGetDataMahasiswa()
    }

    private fun remoteGetDataMahasiswa() {
        ApiClient.apiService.getMahasiswa().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>,
                                    response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val data = apiResponse?.data
                    if (data != null) {
                        setDataToAdapter(data)
                    }
                }
            }
            override fun onFailure(call: Call<ApiResponse>, t:
            Throwable) {
                Log.d("Error", t.stackTraceToString())
            }
        })
    }
    private fun setDataToAdapter(data: List<Mahasiswa>) {
        adapter.setData(data)
    }

}