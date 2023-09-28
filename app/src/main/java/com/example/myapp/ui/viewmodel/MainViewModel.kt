package com.example.myapp.ui.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.RetroInstance
import com.example.myapp.ui.MyData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


open class MydataSate
object LoadingDataState : MydataSate()
object ErrorState:MydataSate()
 class DataSuccessState(val myDataItems: MyData?):MydataSate()

class MainViewModel:ViewModel() {

     private val _myData = MutableLiveData<MydataSate>()
   internal val myData:LiveData<MydataSate> = _myData

    fun getmyData(){

        val retroInstance = RetroInstance.getRtroInstance()

        viewModelScope.launch(Dispatchers.IO) {

             _myData.postValue(LoadingDataState)

            try {

                val retroResponse = retroInstance.getData()

                _myData.postValue(DataSuccessState(retroResponse.body()))

            }catch (E:Exception){

                _myData.postValue(ErrorState)
            }
        }
    }


}