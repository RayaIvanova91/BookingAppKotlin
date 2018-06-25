package com.example.rayaivanova.bookingappkotlin


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.rayaivanova.bookingappkotlin.model.Hotel
import java.io.Serializable


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */


class HotelDetailsFragment : Fragment(), Serializable {

    private var image: ImageView? = null
    private var hotelName: TextView? = null
    private var rating: TextView? = null
    private var address: TextView? = null
    private var price: TextView? = null
    private var back: Button? = null

    interface DetailsComunicator {
        fun backToHotels()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_hotel_details, container, false)
        if (arguments != null && arguments!!.getSerializable("hotel") != null) {
            val hotel = arguments!!.getSerializable("hotel") as Hotel
            image = root.findViewById(R.id.hotel_image)
            image!!.setImageResource(hotel.image)

            hotelName = root.findViewById(R.id.hotel_name)
            hotelName!!.text = hotel.name

            rating = root.findViewById(R.id.hotel_rating_num)
            rating!!.text = "" + hotel.rating

            address = root.findViewById(R.id.hotel_address)
            address!!.text = hotel.address

            price = root.findViewById(R.id.hotel_price)
            price!!.text = price!!.text.toString() + " " + hotel.price+ " " + resources.getString(R.string.currency)

            back = root.findViewById(R.id.back)
            back!!.setOnClickListener {
                if (activity is DetailsComunicator) {
                    (activity as DetailsComunicator).backToHotels()
                }
            }
        }

        //setRetainInstance(true);
        Log.e("Details Tag ", this.tag)
        return root
    }

}
