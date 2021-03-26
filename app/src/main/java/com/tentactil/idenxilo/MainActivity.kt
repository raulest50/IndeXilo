package com.tentactil.idenxilo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration

// NEW VERSION

class MainActivity : AppCompatActivity() {

    val realmName: String = "IdexiloRealm"

    lateinit var config: RealmConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        Realm.init(this) // inicializacion de realm

        config = RealmConfiguration.Builder()
                .name(realmName)
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .build()

        val backgroundThreadRealm : Realm = Realm.getInstance(config)

        backgroundThreadRealm.executeTransaction { transactionRealm ->
            //transactionRealm.insert()
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        InitLoad(config)

        InserTest()

    }

    /**
     * carga los items en recycler view de items
     */
    fun InitLoad(config:RealmConfiguration){
        Realm.getInstanceAsync(config, object : Realm.Callback() {
            override fun onSuccess(realm: Realm) {

                Log.v("EXAMPLE", "Successfully opened a realm with reads and writes allowed on the UI thread.")
                var x = realm.where(Item::class.java).findAll()

                x.forEach{ x->
                    println("${x._id}  ::  ${x.nombre}  :: ${x.N}")
                }
            }
        })
    }

    fun InserTest(){
        Realm.getInstanceAsync(config, object : Realm.Callback(){
            override fun onSuccess(realm: Realm) {
                realm.executeTransaction{ r:Realm ->
                    realm.insertOrUpdate(Item("p3", "contenedor p3 pacax200", 0))
                }
            }
        })
    }

}