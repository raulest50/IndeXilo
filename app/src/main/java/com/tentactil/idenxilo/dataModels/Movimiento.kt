package com.tentactil.idenxilo.dataModels

import org.joda.time.LocalDateTime

/**
 *
 */
class Movimiento {

    var codigo:String = ""
    var D = 0 // cantidad de items que se suman o restan
    lateinit var fecha:LocalDateTime

    constructor(codigo:String, D:Int){
        this.codigo = codigo
        this.D = D
        this.fecha = LocalDateTime.now()
    }
}