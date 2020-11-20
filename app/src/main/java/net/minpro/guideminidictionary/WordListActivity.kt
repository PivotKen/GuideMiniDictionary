package net.minpro.guideminidictionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_word_list.*

class WordListActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    lateinit var realm: Realm
    lateinit var results: RealmResults<WordDB>

    var strCategory: String = ""
    lateinit var wordList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)

        val bundle = intent.extras
        strCategory = bundle!!.getString(getString(R.string.intent_key_strCategory))!!

        textViewStatus.text = strCategory

        btnBack.setOnClickListener{
            finish()
        }

        listView.onItemClickListener = this

        btnSort.setOnClickListener{
            results = realm.where(WordDB::class.java).equalTo(WordDB::strCategory.name, strCategory).findAll().sort(getString(R.string.db_field_memory_flag))
            wordList.clear()
            results.forEach {
                if (it.isMemorized!!) {
                    wordList.add(it.strJapanese.toString() + "  :  " + it.strEnglish.toString() + " 【暗記済】")
                } else {
                    wordList.add(it.strJapanese.toString() + "  :  " + it.strEnglish.toString())
                }

            }
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, wordList)
            listView.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()

        realm = Realm.getDefaultInstance()
        results = realm.where(WordDB::class.java).equalTo(WordDB::strCategory.name, strCategory).findAll().sort(WordDB::strRead.name,Sort.ASCENDING)

        wordList = ArrayList<String>()
        results.forEach {
            if (it.isMemorized!!) {
                wordList.add(it.strJapanese.toString() + "  :  " + it.strEnglish.toString() + " 【暗記済】")
            } else {
                wordList.add(it.strJapanese.toString() + "  :  " + it.strEnglish.toString())
            }

        }

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, wordList)
        listView.adapter = adapter

    }

    override fun onPause() {
        super.onPause()

        realm.close()

    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val selectedDB = results[p2]!!
        val strSelectedRead = selectedDB.strRead
        val strSelectedJapanese = selectedDB.strJapanese
        val strSelectedEnglish = selectedDB.strEnglish
        val strSelectedTips = selectedDB.strTips
        val strSelectedMemoryFlag = selectedDB.isMemorized

        val intent = Intent(this@WordListActivity, DetailActivity::class.java)
        intent.putExtra(getString(R.string.intent_key_strCategory), strCategory)
        intent.putExtra(getString(R.string.intent_key_strRead), strSelectedRead)
        intent.putExtra(getString(R.string.intent_key_strJapanese), strSelectedJapanese)
        intent.putExtra(getString(R.string.intent_key_strEnglish), strSelectedEnglish)
        intent.putExtra(getString(R.string.intent_key_strTips), strSelectedTips)
        intent.putExtra(getString(R.string.intent_key_memory_flag), strSelectedMemoryFlag)
        startActivity(intent)
    }
}