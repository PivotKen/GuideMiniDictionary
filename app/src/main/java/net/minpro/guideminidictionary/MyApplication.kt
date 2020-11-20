package net.minpro.guideminidictionary

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val config = RealmConfiguration.Builder().assetFile("initial_data.realm").build()
        Realm.setDefaultConfiguration(config)

    }

}