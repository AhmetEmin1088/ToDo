package com.ahmeteminyilmaz.todo.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ahmeteminyilmaz.todo.R
import com.ahmeteminyilmaz.todo.model.WeatherModel
import com.ahmeteminyilmaz.todo.service.WeatherAPI
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DecimalFormat

class WeatherFragment : Fragment() {

    //48c1d3e2d53046a44b9c7ac34c63b2e0
    //https://api.openweathermap.org/data/2.5/weather?q=Kayseri,tr&APPID=48c1d3e2d53046a44b9c7ac34c63b2e0

    var trCityList = arrayListOf<String>("Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Aksaray",
        "Amasya", "Ankara", "Antalya", "Ardahan", "Artvin", "Aydın", "Balıkesir", "Bartın",
        "Batman", "Bayburt", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
        "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Düzce", "Edirne", "Elazığ", "Erzincan",
        "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkâri", "Hatay", "Iğdır",
        "Isparta", "İstanbul", "İzmir", "Kahramanmaraş", "Karabük", "Karaman", "Kars", "Kastamonu",
        "Kayseri", "Kilis", "Kırıkkale", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya",
        "Malatya", "Manisa", "Mardin", "Mersin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu",
        "Osmaniye", "Rize", "Sakarya", "Samsun", "Şanlıurfa", "Siirt", "Sinop", "Sivas", "Şırnak",
        "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Uşak", "Van", "Yalova", "Yozgat", "Zonguldak")


    private val BASE_URL = "https://api.openweathermap.org/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        searchButton.setOnClickListener {
            val str = chooseCityText.text.toString().trim()
            if(trCityList.contains(str.uppercase()) ||
                trCityList.contains(str.lowercase()) ||
                trCityList.contains(str.replaceFirstChar { it.uppercase() }))
            {
                loadData()
            }
        }


    }

    private fun loadData() {

        val retrofit =  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherAPI::class.java)

        GlobalScope.launch {

            val call = service.getData(chooseCityText.text.toString().trim())

            call.enqueue(object : Callback<WeatherModel> {
                override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {

                    if(response.isSuccessful) {

                        response.body()?.let {
                            cityNameText.text = it.cityName
                            temperatureText.text = DecimalFormat("#.#").format(it.main.temperature - 273) + " C"
                            minTemperatureText.text = "Min Temp : " + DecimalFormat("#.#").format(it.main.temp_minimum - 273) + " C"
                            maxTemperatureText.text = "Max Temp : " + DecimalFormat("#.#").format(it.main.temp_maximum - 273) + " C"
                            humidityText.text = "Humidity : " + it.main.humidity.toString()
                        }

                    }

                }

                override fun onFailure(call: Call<WeatherModel>, t: Throwable) {

                    t.printStackTrace()

                }

            })

        }

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        menu.clear()
        inflater.inflate(R.menu.todo_menu,menu)

        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {

            R.id.add_taskOrActivity_item -> {
                val action = WeatherFragmentDirections.actionWeatherFragmentToAddingTaskOrActivity("camefrommenu")
                Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(action)
            }
            R.id.myTasksAndActivities_item -> {
                val action = WeatherFragmentDirections.actionWeatherFragmentToToDoListFragment()
                Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(action)
            }
            R.id.weather_item -> {
                Toast.makeText(requireActivity(),"You are already in this page.",Toast.LENGTH_LONG).show()
            }
            R.id.aboutApp_item -> {
                val action = WeatherFragmentDirections.actionWeatherFragmentToAboutAppPage()
                Navigation.findNavController(requireActivity(),R.id.fragmentContainerView).navigate(action)
            }
            R.id.exitApp_item -> {
                val alertDialog = AlertDialog.Builder(requireActivity())
                alertDialog
                    .setTitle("Exit App")
                    .setMessage("Are you sure do you want to exit?")
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                        requireActivity().finish()
                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->

                    })
                    .create()
                    .show()
            }

        }

        return super.onOptionsItemSelected(item)
    }



}