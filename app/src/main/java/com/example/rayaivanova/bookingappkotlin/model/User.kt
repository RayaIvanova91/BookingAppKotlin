package com.example.rayaivanova.bookingappkotlin.model

import java.io.Serializable

class User(val username:String, val password:String, val email:String, val isMale:Boolean, val age:Int):Serializable {
}