package com.example.restaurantapp

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.restaurantapp.ui.theme.LittleLemonColor



fun performLogout(context: Context) {
    // 1. Get a reference to your SharedPreferences file
    val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

    // 2. Get an editor
    sharedPreferences.edit {

        // 3. Clear all the data in this preference file
        clear()

        // 4. Apply the changes
    }
}

@Composable
fun ProfileScreen(navController: NavHostController) {

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    // Use the same keys you used when saving
    val firstName = sharedPreferences.getString("FIRST_NAME", "")
    val lastName = sharedPreferences.getString("LAST_NAME", "")
    val email = sharedPreferences.getString("EMAIL", "")
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
            Text(text="Welcome to your profile",
                style= MaterialTheme.typography.headlineSmall,
                color = LittleLemonColor.cloud)
        }
        Text(text="Personal Information",
            style= MaterialTheme.typography.titleMedium,
            modifier= Modifier
                .align(Alignment.Start)
                .padding(start=10.dp, top=40.dp, bottom=30.dp),
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )


        //for FirstName
        ForTextField(
            label = "First Name",
            value = firstName ?: "",
        )

        //for LastName
        ForTextField(
            label = "Last Name",
            value = lastName ?: "",
        )

        //for Email
        ForTextField(
            label = "Email",
            value = email ?: "",
            keyboardType = KeyboardType.Email
        )// Set keyboard type to Email

        Button(
            onClick ={
            performLogout(context)
            //Navigate to the onboarding screen and clear the history
            navController.navigate("onBoarding"){
                popUpTo(navController.graph.startDestinationId){
                    inclusive=true
                }
                launchSingleTop= true

            }

        },
            colors = ButtonDefaults.buttonColors(LittleLemonColor.yellow),
            modifier = Modifier.padding(20.dp)
                .fillMaxWidth(0.8f)
            ){

            Text(text = "Logout", color = Color.Black)
        }

    }
}


@Composable
fun ForTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit={},
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
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            readOnly = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    ProfileScreen(navController = rememberNavController())
}
