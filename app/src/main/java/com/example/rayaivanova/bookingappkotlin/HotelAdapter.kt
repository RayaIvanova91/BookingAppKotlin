package com.example.rayaivanova.bookingappkotlin

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.example.rayaivanova.bookingappkotlin.model.Hotel
import com.example.rayaivanova.bookingappkotlin.model.MIN_STARS_COUNT
import java.util.ArrayList

class HotelAdapter internal constructor(private val hotels: List<Hotel>, private val context: FragmentActivity?) : RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val inflater = LayoutInflater.from(context)
        val hotelRow = inflater.inflate(R.layout.hotel, parent, false)
        return HotelViewHolder(hotelRow)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hotel = hotels[position]
        holder.image.setImageResource(hotel.image)

        holder.image.setOnClickListener {
            if (context is HotelsActivity) {
                Log.e("onClickListener", "imagea e kliknat")
                context.showDetails(hotel)
            }
        }


        holder.name.setText(hotel.name)
        val rating = hotel.rating
        holder.rating.text = "" + rating
        val hotelRating = getStringRating(rating)
        holder.ratingString.text = hotelRating
        holder.address.text = "" + hotel.distanceFromCenter + context?.resources?.getString(R.string.distance) + " " + hotel.address
        holder.price.text = "" + hotel.price + context?.resources?.getString(R.string.currency)
        holder.prepayment.text = ""
        for (i in 0..4) {
            holder.stars[i].visibility = View.INVISIBLE
        }
        if (hotel.countStars > MIN_STARS_COUNT) {
            val numStars = hotel.countStars
            for (i in 0 until numStars) {
                holder.stars[i].visibility = View.VISIBLE
            }
        }
        if (context is HotelsActivity) {
            if (!hotel.prepayment) {
                holder.prepayment.text = context.getResources().getString(R.string.noPrepayment)
            }
        }
    }

    private fun getStringRating(rating: Double): String? {
        var hotelRating :String?=""
        if (rating < 8) {
            hotelRating = context?.resources?.getString(R.string.good)
        }
        if (rating >= 8 && rating <= 8.5) {
            hotelRating = context?.resources?.getString(R.string.veryGood)
        }
        if (rating > 8.5 && rating < 9) {
            hotelRating = context?.resources?.getString(R.string.excellent)
        }
        if (rating >= 9) {
            hotelRating = context?.resources?.getString(R.string.wonderful)
        }
        return hotelRating
    }


    override fun getItemCount(): Int {
        return hotels.size
    }


    //the View Holder
    inner class HotelViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var image: ImageView
        var name: TextView
        var stars: MutableList<RatingBar>
        var rating: TextView
        var ratingString: TextView
        var address: TextView
        var prepayment: TextView
        var price: TextView

        init {
            stars = ArrayList()
            this.image = v.findViewById(R.id.hotel_image)
            this.name = v.findViewById(R.id.hotel_name)
            this.rating = v.findViewById(R.id.hotel_rating_num)
            this.ratingString = v.findViewById(R.id.hotel_rating_string)
            this.address = v.findViewById(R.id.hotel_address)
            this.prepayment = v.findViewById(R.id.hotel_prepayment)
            this.price = v.findViewById(R.id.hotel_price)
            stars.add(v.findViewById(R.id.hotel_ratingStar1) as RatingBar)
            stars.add(v.findViewById(R.id.hotel_ratingStar2) as RatingBar)
            stars.add(v.findViewById(R.id.hotel_ratingStar3) as RatingBar)
            stars.add(v.findViewById(R.id.hotel_ratingStar4) as RatingBar)
            stars.add(v.findViewById(R.id.hotel_ratingStar5) as RatingBar)

        }
    }
}