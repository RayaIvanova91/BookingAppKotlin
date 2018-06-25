package com.example.rayaivanova.bookingappkotlin.model

import java.io.Serializable

class Hotel(countStars: Int, var image: Int, var name: String, var rating: Double, var address: String, var distanceFromCenter: Int, var price: Int, var prepayment: Boolean) : Serializable {
    var countStars: Int = MIN_STARS_COUNT
        set(value) {
            if (value in MIN_STARS_COUNT..MAX_STARS_COUNT) {
                field = value
            }
        }

    init {
        this.countStars = countStars
    }

}

val MIN_STARS_COUNT=0
val MAX_STARS_COUNT=5