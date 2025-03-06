package com.gomez.herlin.logindemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.gomez.herlin.logindemo.adapter.ListAdapter
import com.gomez.herlin.logindemo.databinding.ActivityHomeBinding
import com.gomez.herlin.logindemo.dto.DonutsDto
import com.gomez.herlin.logindemo.retrofit.RetrofitApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var textViewUser: TextView
    private lateinit var btnLogout: Button

    private var donutsDtoList: List<DonutsDto>? = null
    private lateinit var retrofitApiService: RetrofitApiService

    private lateinit var mAppBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        val drawer = binding.drawerLayout
        val navigationView = binding.navView
        mAppBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_account, R.id.nav_transfer, R.id.nav_payments,
            R.id.nav_maintenance, R.id.nav_mtto, R.id.nav_contact,
            R.id.nav_locations, R.id.nav_request_product, R.id.nav_notifications,
            R.id.nav_blockages, R.id.nav_config, R.id.nav_tutorial
        ).setOpenableLayout(drawer).build()
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration)
        NavigationUI.setupWithNavController(navigationView, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController( R.id.nav_host_fragment_content_main)
        return NavigationUI.navigateUp(navController, mAppBarConfiguration!!)
                || super.onSupportNavigateUp()
    }

    /*fun init() {
        val listAdapter = ListAdapter(
            donutsDtoList, this
        ) { item ->
            moveToDescription(
                item
            )
        }
    }*/

   /* private fun getDonuts() {
        retrofitApiService.donuts.enqueue(object : Callback<List<DonutsDto>> {
            override fun onResponse(call: Call<List<DonutsDto>>, response: Response<List<DonutsDto>>) {
                if (response.isSuccessful) {
                    donutsDtoList = response.body()
                    init()
                }
            }

            override fun onFailure(call: Call<List<DonutsDto>>, t: Throwable) {
                Log.e("Error", t.message ?: "Unknown error")
                Toast.makeText(this@HomeActivity, getString(R.string.errorMessage) + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }*/


    fun moveToDescription(item: DonutsDto?) {
        val intent = Intent(this, DescriptionActivity::class.java)
        intent.putExtra("DonutsDto", item)
        intent.putExtra("username", getIntent().getStringExtra("username"))
        startActivity(intent)
    }

}