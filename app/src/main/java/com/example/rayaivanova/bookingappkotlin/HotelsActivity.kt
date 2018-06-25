package com.example.rayaivanova.bookingappkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.example.rayaivanova.bookingappkotlin.model.Hotel
import kotlinx.android.synthetic.main.activity_hotels.*

class HotelsActivity : AppCompatActivity(), HotelDetailsFragment.DetailsComunicator {

    private lateinit var hotels: List<Hotel>
    private lateinit var allHotels: Fragment
    internal var hotel: Hotel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotels)

        val fm = supportFragmentManager
        val fTransaction = fm.beginTransaction()
        allHotels = HotelListFragment()

        if (land_id == null) {
            fTransaction.addToBackStack(allHotels.tag)
            fTransaction.replace(R.id.hotels_activity, allHotels, "HotelList").commit()
        } else {
            fTransaction.replace(land_id.id, allHotels, "HotelList").commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("HotelsActivity", "onDestroy")
    }

    fun showDetails(hotel: Hotel) {
        val fm = supportFragmentManager

        val bundle = Bundle()
        bundle.putSerializable("hotel", hotel)
        val hotelDetailsFragment = HotelDetailsFragment()
        hotelDetailsFragment.arguments = bundle
        val fTransaction = fm.beginTransaction()

        if (land_id == null) {
            //portrait mode
            //do whatever we do in portrait - replace fragment
            //fTransaction.addToBackStack(hotelDetailsFragment.tag)
            fTransaction.replace(R.id.hotels_activity, hotelDetailsFragment, "HotelDetails").commit()
        } else {
            //land mode
            //add fragment
            //if no details on screen, add fragment
            if (fm.findFragmentByTag("HotelDetails") == null) {
                fTransaction.add(land_id.id, hotelDetailsFragment, "HotelDetails").commit()
            }
            //if any details in landscape, replace just the details fragment (not all fragments on the screen)
            else {
                fTransaction.remove(fm.findFragmentByTag("HotelDetails")).add(land_id.id, hotelDetailsFragment, "HotelDetails").commit()
            }
        }
    }

    override fun backToHotels() {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        //in landscape remove details and only the list should stay
        if (land_id != null) {
            ft.remove(fm.findFragmentByTag("HotelDetails")).commit()
        }
        //in portrait replace the details with the list
        else {
            allHotels = HotelListFragment()
            ft.replace(R.id.hotels_activity, allHotels, "HotelList").commit()
        }
    }
}
