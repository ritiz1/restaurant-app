package com.example.restaurantapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurantapp.ui.theme.LittleLemonColor


@Composable
fun onBoarding(){
    Column(modifier= Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter= painterResource(R.drawable.littlelemonimgtxt)
        , contentDescription = "Top Bar Logo",
            modifier= Modifier.width(200.dp)
                .align(Alignment.CenterHorizontally)
                .padding(20.dp))

        Box(modifier = Modifier.
            fillMaxWidth()
            .background(color= LittleLemonColor.green)
            .padding(50.dp),
            contentAlignment = Alignment.Center){
            Text(text="Let's get to know you",
                style= MaterialTheme.typography.headlineSmall,
                color = LittleLemonColor.cloud)
        }

        Text(text="Personal Information",
            style= MaterialTheme.typography.titleMedium,
            modifier= Modifier
                .align(Alignment.Start)
                .padding(start=10.dp, top=40.dp, bottom=30.dp),
            fontWeight = FontWeight.Bold
        )

        var firstName = remember { mutableStateOf("") }

        FormTextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            label = "First Name"
        )


        var lastName = remember { mutableStateOf("") }
        FormTextField(
            value=lastName.value,
            onValueChange = {lastName.value = it},
            label="Last Name"
        )

        var forEmail = remember { mutableStateOf("") }
        FormTextField(
            value=forEmail.value,
            onValueChange = {forEmail.value = it},
            label="Email",
            keyboardType = KeyboardType.Email
        )

        Button(onClick= {},
            colors = ButtonDefaults.buttonColors(LittleLemonColor.yellow),
            modifier=Modifier.fillMaxWidth(0.8f)
                .padding(top=20.dp)
            ) {
            Text("Register")
        }





    }
}
@Composable
fun FormTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text // Default keyboard type
) {
    Column(modifier=Modifier.padding(10.dp)) {
        // Label above the text field
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall, // Use a smaller style for the label
            modifier = Modifier.padding(bottom = 4.dp,start=10.dp,end =10.dp, top =10.dp)
        )

        // The text field itself
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(), // Make the text field take the full width
            singleLine = true, // Ensure the input is a single line
            shape = MaterialTheme.shapes.medium, // Apply rounded corners
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun obBoardingScreen(){
    onBoarding()

}
