package com.tentactil.idenxilo

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

/**
 *
 */
open class Item(
    @PrimaryKey
    var _id:String = "", // id del item en la base de datos

    var nombre:String = "", // descripcion del producto puede ser cualquiera

    var N:Int = 0 // cantidad actual del producto
): RealmObject() {

}