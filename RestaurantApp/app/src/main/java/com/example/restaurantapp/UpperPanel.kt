package com.example.restaurantapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurantapp.ui.theme.LittleLemonColor

@Composable
fun UpperPanel(){
    Column(
        modifier= Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .background(color= LittleLemonColor.green)


    ){
        Text(
            text= stringResource(id= R.string.title),
            fontSize= 40.sp,
            fontWeight = FontWeight.Bold,
            color= LittleLemonColor.yellow,
            modifier= Modifier
                .padding(top=20.dp, start=20.dp)
        )
        Text(
            text= stringResource(id= R.string.location),
            fontSize= 24.sp,
            color= LittleLemonColor.cloud,
            modifier= Modifier.padding(start=20.dp, top=5.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier= Modifier
                .padding(top=20.dp)
        ){
            Text(
                text= stringResource(id= R.string.description),
                style=MaterialTheme.typography.bodyMedium,
                modifier= Modifier
                    .padding(bottom= 28.dp, end= 20.dp,start=20.dp)
                    .fillMaxWidth(0.6f),
                color = LittleLemonColor.cloud,

                )
            Image(
                painter= painterResource(id=R.drawable.upperpanelimage),
                contentDescription = "Upper Panel Image",
                modifier= Modifier.clip(RoundedCornerShape(10.dp))
            )


        }
        Button (
            onClick={},
            modifier= Modifier.padding(start=20.dp, bottom=20.dp, end=20.dp)
                .clip(RoundedCornerShape(10.dp)),
            colors= ButtonDefaults.buttonColors(containerColor= LittleLemonColor.yellow),

            ){
            Text(
                text=stringResource(id= R.string.order_button_text),
                color= LittleLemonColor.green,

                )
        }

    }


}


@Preview(showBackground = true)
@Composable
fun UpperPanelPreview(){
    UpperPanel()
}