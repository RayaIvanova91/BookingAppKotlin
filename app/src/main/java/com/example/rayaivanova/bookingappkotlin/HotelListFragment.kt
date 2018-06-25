package com.example.rayaivanova.bookingappkotlin


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rayaivanova.bookingappkotlin.model.Hotel
import kotlinx.android.synthetic.main.fragment_hotel_list.view.*
import java.util.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HotelListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private var hotels: ArrayList<Hotel>? = null

    interface HotelListComunicator {
        fun showDetails(hotel: Hotel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_hotel_list, container, false)

        //1) namiram konkretnoto RecyclerView
        recyclerView = root.hotels

        //2) List s hoteli
        hotels = ArrayList<Hotel>()
        hotels!!.add(Hotel(3, R.drawable.hotel1, "Aspria Hamburg", 7.5, "Winterhude, Hamburg", 12, 220, true))
        hotels!!.add(Hotel(2, R.drawable.hotel2, "Holiday Inn", 8.3, "St. Pauli, Hamburg", 5, 350, true))
        hotels!!.add(Hotel(0, R.drawable.hotel3, "Hotel Mare", 5.0, "Hamburg City Center (New Town), Hamburg", 15, 100, false))
        hotels!!.add(Hotel(5, R.drawable.hotel4, "Dorint Hotel", 9.0, "St. Georg, Hamburg", 2, 400, true))
        hotels!!.add(Hotel(4, R.drawable.hotel5, "Crowne Plaza", 8.0, "Hamburg City Center (Old Town), Hamburg", 4, 300, false))
        hotels!!.add(Hotel(2, R.drawable.hotel6, "NIPPON HOTEL Hamburg Boutique 072 Hamburg St. Georg", 4.0, "Harvestehude, Hamburg", 10, 150, false))
        hotels!!.add(Hotel(5, R.drawable.hotel7, "Sofitel Hamburg", 7.0, "Stellingen, Hamburg", 11, 50, true))
        hotels!!.add(Hotel(1, R.drawable.hotel8, "Hyperion Hotel", 7.5, "Lokstedt, Hamburg", 4, 800, false))
        hotels!!.add(Hotel(1, R.drawable.hotel1, "Aspria Hamburg", 7.5, "Winterhude, Hamburg", 12, 220, true))
        hotels!!.add(Hotel(4, R.drawable.hotel2, "Holiday Inn", 8.3, "St. Pauli, Hamburg", 5, 350, true))
        hotels!!.add(Hotel(5, R.drawable.hotel3, "Hotel Mare", 5.0, "Hamburg City Center (New Town), Hamburg", 15, 100, false))
        hotels!!.add(Hotel(5, R.drawable.hotel4, "Dorint Hotel", 9.0, "St. Georg, Hamburg", 2, 400, true))
        hotels!!.add(Hotel(4, R.drawable.hotel5, "Crowne Plaza", 8.0, "Hamburg City Center (Old Town), Hamburg", 4, 300, false))
        hotels!!.add(Hotel(3, R.drawable.hotel6, "NIPPON HOTEL Hamburg", 4.0, "Harvestehude, Hamburg", 10, 150, false))
        hotels!!.add(Hotel(5, R.drawable.hotel7, "Sofitel Hamburg", 7.0, "Stellingen, Hamburg", 11, 50, true))
        hotels!!.add(Hotel(5, R.drawable.hotel8, "Hyperion Hotel", 7.5, "Lokstedt, Hamburg", 4, 800, false))
        hotels!!.add(Hotel(5, R.drawable.hotel1, "Aspria Hamburg", 7.5, "Winterhude, Hamburg", 12, 220, true))
        hotels!!.add(Hotel(2, R.drawable.hotel2, "Holiday Inn", 8.3, "St. Pauli, Hamburg", 5, 350, true))
        hotels!!.add(Hotel(4, R.drawable.hotel3, "Hotel Mare", 5.0, "Hamburg City Center (New Town), Hamburg", 15, 100, false))
        hotels!!.add(Hotel(4, R.drawable.hotel4, "Dorint Hotel", 9.0, "St. Georg, Hamburg", 2, 400, true))
        hotels!!.add(Hotel(5, R.drawable.hotel5, "Crowne Plaza", 8.0, "Hamburg City Center (Old Town), Hamburg", 4, 300, false))
        hotels!!.add(Hotel(4, R.drawable.hotel6, "NIPPON HOTEL Hamburg", 4.0, "Harvestehude, Hamburg", 10, 150, false))
        hotels!!.add(Hotel(1, R.drawable.hotel7, "Sofitel Hamburg", 7.0, "Stellingen, Hamburg", 11, 50, true))
        hotels!!.add(Hotel(4, R.drawable.hotel8, "Hyperion Hotel", 7.5, "Lokstedt, Hamburg", 4, 800, false))

        //3) Adapter i LayoutManager na RecyclerView-to
        recyclerView.adapter = HotelAdapter(hotels!!, activity)
                //HotelAdapter(hotels, activity)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        //retainInstance=true
        return root
    }
}
