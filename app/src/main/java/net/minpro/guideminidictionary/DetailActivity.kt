package net.minpro.guideminidictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var realm: Realm

    var strSelectedJapanese: String? = null
    var strSelectedEnglish: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle = intent.extras

        val strRead = bundle!!.getString(getString(R.string.intent_key_strRead))
        val strTips = bundle.getString(getString(R.string.intent_key_strTips))
        val boolMemoryFlag = bundle.getBoolean(getString(R.string.intent_key_memory_flag))
        val strCategoryName = bundle.getString(getString(R.string.intent_key_strCategory))
        strSelectedJapanese = bundle.getString(getString(R.string.intent_key_strJapanese))
        strSelectedEnglish = bundle.getString(getString(R.string.intent_key_strEnglish))

        textViewCategory.text = strCategoryName
        textViewKana.text = strRead
        textViewJapanese.text = strSelectedJapanese
        textViewEnglish.text = strSelectedEnglish
        textViewTips.text = strTips
        Linkify.addLinks(textViewTips, Linkify.ALL)
        checkBox.isChecked = boolMemoryFlag

        buttonBack2.setOnClickListener {
            registerToDB()
            finish()
        }

    }

    private fun registerToDB() {
        val selectedDB: WordDB = realm.where(WordDB::class.java).equalTo(WordDB::strJapanese.name, strSelectedJapanese).equalTo(WordDB::strEnglish.name, strSelectedEnglish).findFirst()!!
        realm.beginTransaction()
        selectedDB.isMemorized = checkBox.isChecked
        realm.commitTransaction()

    }

    override fun onResume() {
        super.onResume()

        realm = Realm.getDefaultInstance()
    }

    override fun onPause() {
        super.onPause()

        realm.close()
    }
}
