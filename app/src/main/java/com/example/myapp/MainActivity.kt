package com.example.myapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.myapp.ui.Item
import com.example.myapp.ui.theme.MyAppTheme
import com.example.myapp.ui.viewmodel.DataSuccessState
import com.example.myapp.ui.viewmodel.ErrorState
import com.example.myapp.ui.viewmodel.LoadingDataState
import com.example.myapp.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()
        setContent {
            MyAppTheme {


                    displayMyData(viewModel)

            }
        }
    }
}


@Composable
fun displayMyData(viewModel: MainViewModel){
    LaunchedEffect(key1 =Unit) {

            viewModel.getmyData()
        Log.i("Deepthi","Entered displayMyData")
    }

   when(val state = viewModel.myData.observeAsState().value){

        is LoadingDataState ->{
            LoadingState()
        }
        is ErrorState -> {
            NycError()

        }

        is DataSuccessState ->{
            state.myDataItems?.let { getMyDataState(it.items) }

        }
        else -> {}
    }


}

@Composable
fun getMyDataState(items: List<Item>){

    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn {
            itemsIndexed(
                items = items
            ) { index, item ->
               MyDataItem(item)
            }
        }


    }

}
@Composable
fun MyDataItem(item: Item) {

    Card(modifier = Modifier.fillMaxWidth(), elevation = 8.dp,shape = RoundedCornerShape(8.dp)) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        val painter = rememberImagePainter(data = item.img)
        Image(
            painter = painter,
            contentDescription = "Image",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = item.description
        )
    }
}

}

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize().semantics
        {
            contentDescription = "Circular progress indicator"
        }.testTag("ProgressBar"),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = colorResource(id = R.color.purple_200)
        )
    }
}

@Composable
fun NycError() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = "Error while calling API",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            color = Color.Red

        )
    }

}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyAppTheme {
        Greeting("Android")
    }
}