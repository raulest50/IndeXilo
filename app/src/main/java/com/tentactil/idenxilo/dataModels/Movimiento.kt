package com.tentactil.idenxilo.dataModels

import org.joda.time.LocalDateTime

/**
 * Cada item tendra muchos movimientos
 */
class Movimiento {

    var D = 0 // cantidad de items que se suman o restan
    var descripcion = ""
    lateinit var fecha:LocalDateTime

    constructor(D:Int, descripcion:String, fecha:LocalDateTime){
        this.D = D
        this.descripcion = descripcion
        this.fecha = fecha
        //this.fecha = LocalDateTime.now()
    }
}