package net.minpro.guideminidictionary

import io.realm.RealmObject

open class WordDB: RealmObject() {

    var strCategory: String? = null
    var strRead: String? = null
    var strJapanese: String? = null
    var strEnglish: String? = null
    var strTips: String? = null
    var isMemorized: Boolean? = null

}