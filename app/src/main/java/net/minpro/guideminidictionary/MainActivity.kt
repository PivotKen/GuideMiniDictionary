package net.minpro.guideminidictionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTempleShrine.setOnClickListener(this)
        btnFood.setOnClickListener(this)
        btnPlantAnimal.setOnClickListener(this)
        btnHistory.setOnClickListener(this)
        btnTraditionalArt.setOnClickListener(this)
        btnPopCulture.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {
        val buttonName = p0!!.id

        var japaneseCategory = ""
        when(buttonName) {
            btnTempleShrine.id -> {
                japaneseCategory = getString(R.string.temple_shrine)
            }
            btnFood.id -> {
                japaneseCategory = getString(R.string.food)
            }
            btnPlantAnimal.id -> {
                japaneseCategory = getString(R.string.plant_animal)
            }
            btnHistory.id -> {
                japaneseCategory = getString(R.string.history)
            }
            btnTraditionalArt.id -> {
                japaneseCategory = getString(R.string.traditional_art)
            }
            btnPopCulture.id -> {
                japaneseCategory = getString(R.string.pop_culture)
            }
        }

        val intent = Intent(this@MainActivity, WordListActivity::class.java)
        intent.putExtra(getString(R.string.intent_key_strCategory), japaneseCategory)
        startActivity(intent)

    }
}