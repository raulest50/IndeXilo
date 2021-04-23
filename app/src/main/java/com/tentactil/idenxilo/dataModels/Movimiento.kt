package com.tentactil.idenxilo.dataModels

import io.realm.RealmObject
import io.realm.annotations.Ignore
import org.joda.time.LocalDateTime

/**
 * Cada item tendra muchos movimientos
 */
open class Movimiento(
        var D:Int = 0, // cantidad de items que se suman o restan
        var descripcion:String = "",
        //var fechaJ:LocalDateTime = LocalDateTime.now().toString,
        var fecha:String = LocalDateTime.now().toString()

): RealmObject() {


}