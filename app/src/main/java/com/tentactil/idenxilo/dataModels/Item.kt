package com.tentactil.idenxilo.dataModels

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

/**
 *
 */
open class Item(
    @PrimaryKey
    var _id:String = "", // id del item en la base de datos

    var nombre:String = "", // descripcion del producto puede ser cualquiera

    var N:Int = 0, // cantidad actual del producto

    var movimientos:LinkedList<Movimiento>
    ): RealmObject() {


}