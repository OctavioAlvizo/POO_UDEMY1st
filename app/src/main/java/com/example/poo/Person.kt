package com.example.poo

open class Person(var nombre: String = "Anonimo", var passport: String? = null) {

    var alive: Boolean = true

    fun Person(){
        nombre = "Araam"
        passport = "AMHA1234"
    }

    fun die(){
        alive = false
    }

}

class Athlete(nombre: String, passport: String?, var sport: String ): Person(nombre , passport)