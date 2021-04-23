package com.tentactil.idenxilo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tentactil.idenxilo.dataModels.Item
import io.realm.Realm
import io.realm.RealmConfiguration



class MainActivity : AppCompatActivity() {

    val realmName: String = "IdexiloRealm2"

    lateinit var config: RealmConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        Realm.init(this) // inicializacion de realm

        config = RealmConfiguration.Builder()
                .name(realmName)
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .build()

        val backgroundThreadRealm : Realm = Realm.getInstance(config)

        // Las inserciones se deben hacer dentro de transactionRealm ->
        backgroundThreadRealm.executeTransaction { transactionRealm ->
            //transactionRealm.insert()
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}