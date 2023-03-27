package com.example.mobiletask

import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobiletask.databinding.ActivityMainBinding
import com.example.myapplication.ImageSliderAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.String
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private val Adapter by lazy{ Adapter() }
    private val HoriAdapert by lazy{ HoriAdapert() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Adapter.differ.submitList(loadData())

        binding.apply {
            rvMain.apply {
                layoutManager= LinearLayoutManager(this@MainActivity)
                adapter=Adapter
            }
        }
        HoriAdapert.differ.submitList(loadData())

        binding.apply {
            rMain.apply {
                layoutManager= LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false )
                adapter=HoriAdapert
            }
        }
//        val toggle = ActionBarDrawerToggle(
//            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
//        )
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
        drawer_layout.openDrawer(Gravity.RIGHT)



        nav_view.setNavigationItemSelectedListener(this)
        imageSliderImplementation()
        object : CountDownTimer(100000000, 10) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                textView.setText(hmsTimeFormatter(millisUntilFinished))
                textView1.setText(hmsTimeFormatter(millisUntilFinished))
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {
                textView.setText("done!")
            }
        }.start()


    }

    fun loadData() : MutableList<visitor>{
        val nameList : MutableList<visitor> = mutableListOf()
        nameList.add(visitor("13:59:00","اسم المزايد"))
        nameList.add(visitor("13:59:00","اسم المزايد"))
        nameList.add(visitor("13:59:00","اسم المزايد"))



        return nameList
    }
    private fun hmsTimeFormatter(milliSeconds: Long): kotlin.String? {
        return String.format(
            "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(milliSeconds),
            TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(
                    milliSeconds
                )
            ),
            TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(
                    milliSeconds
                )
            )
        )
    }


    private fun imageSliderImplementation() {

        val adapter = ImageSliderAdapter(this)
        viewpager.adapter = adapter
        tab_layout.setupWithViewPager(viewpager, true)


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
                drawerLayout.closeDrawer(Gravity.RIGHT)
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(Gravity.RIGHT)
        return true
    }
}