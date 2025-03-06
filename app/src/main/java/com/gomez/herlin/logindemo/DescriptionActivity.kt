package com.gomez.herlin.logindemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gomez.herlin.logindemo.dto.BatterDto
import com.gomez.herlin.logindemo.dto.DonutsDto
import com.gomez.herlin.logindemo.dto.ToppingDto
import com.gomez.herlin.logindemo.fragment.ListDetailFragment
import java.util.function.Consumer

class DescriptionActivity : AppCompatActivity() {
    var titleDescriptionTextView: TextView? = null
    var nameDescriptionTextView: TextView? = null
    var typeDescriptionTextView: TextView? = null
    var ppuDescriptionTextView: TextView? = null
    private var textViewUser: TextView? = null

    private var btnLogout: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_description)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val donutDto = intent.getSerializableExtra("DonutsDto") as DonutsDto?
        titleDescriptionTextView = findViewById(R.id.titleDescriptionTextView)
        nameDescriptionTextView = findViewById(R.id.nameDescriptionTextView)
        typeDescriptionTextView = findViewById(R.id.typeDescriptionTextView)
        ppuDescriptionTextView = findViewById(R.id.ppuDescriptionTextView)

        donutDto?.let {
            titleDescriptionTextView?.text = "ID: ${it.id}"
            nameDescriptionTextView?.text = "Name: ${it.name}"
            typeDescriptionTextView?.text = "Type: ${it.type}"
            ppuDescriptionTextView?.text = "PPU: ${it.ppu}"
        }

        textViewUser = findViewById(R.id.textViewUser)
        textViewUser?.text = intent.getStringExtra("username")

        btnLogout = findViewById(R.id.btnLogout)
        btnLogout?.setOnClickListener{
            val intent = Intent(
                this@DescriptionActivity,
                MainActivity::class.java
            )
            this@DescriptionActivity.startActivity(intent)
        }

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val batters: MutableList<String> = ArrayList()
        donutDto?.batters?.batter?.forEach(Consumer { topping: BatterDto -> batters.add(topping.id + " - " + topping.type) })
        val fragment1 = ListDetailFragment(batters)

        val toppings: MutableList<String> = ArrayList()
        donutDto?.topping?.forEach(Consumer { topping: ToppingDto -> toppings.add(topping.id + " - " + topping.type) })
        val fragment2 = ListDetailFragment(toppings)

        fragmentTransaction.add(R.id.fragmentContainerBatters, fragment1)
        fragmentTransaction.add(R.id.fragmentContainerTopping, fragment2)

        fragmentTransaction.commit()
    }
}