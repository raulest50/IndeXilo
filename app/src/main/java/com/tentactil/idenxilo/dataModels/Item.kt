package com.tentactil.idenxilo.dataModels

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

/**
 * se deben asignar valores por defecto para que compile,
 * ver :
 * (porque no compile)
 * https://stackoverflow.com/questions/49167886/kotlin-realm-class-must-declare-a-public-constructor-with-no-arguments-if-it-co
 * (como crear constructor vacio
 * https://stackoverflow.com/questions/37873995/how-to-create-empty-constructor-for-data-class-in-kotlin-android
 */
open class Item(

        @PrimaryKey
        var _id:String = "0", // id del item en la base de datos

        var nombre:String = "default", // descripcion del producto puede ser cualquiera

        private var N:Int = 0, // cantidad actual del producto

        private var movimientos: RealmList<Movimiento> = RealmList()

): RealmObject() {

    fun AddMovimiento(mov:Movimiento){
        movimientos.add(mov)
        N = SumMovs()
    }

    fun SumMovs():Int{
        var s = 0
        movimientos.forEachIndexed { index, movimiento ->
            s += movimiento.D
        }
        return s
    }

    /**
     *
     */
    fun getMovs():LinkedList<Movimiento>{
        var r = LinkedList<Movimiento>()
        this.movimientos.forEach({ x-> r.add(x)})
        return r
    }
}