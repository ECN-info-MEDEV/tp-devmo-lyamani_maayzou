package com.example.rate_us

import android.os.Bundle
import android.widget.RatingBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rate_us.ui.theme.Rate_usTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Rate_usTheme {
                // Setup Navigation
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "mainPage") {
                    composable("mainPage") { RateUsPage(navController) }
                    composable("ratingPage") { RatingPage(navController) }
                }
            }
        }
    }
}

@Composable
fun RateUsPage(navController: NavController) {
    val image = painterResource(id = R.drawable.azerrr) // Replace with your actual image resource
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = image, contentDescription = "App Rating")
        Spacer(modifier = Modifier.height(16.dp))

        // The reviews would be displayed here
        val reviews = listOf(
            Review("Amazing!", "Can't live without it anymore", 5f),
            Review("Yes Please", "Glad to find this! Recommend it!", 4f),
            Review("Cool App", "Just wish it had more pro features.", 3.5f)
        )

        reviews.forEach { review ->
            ReviewCardComposable(review = review)
        }

        // Here you can add your buttons for Menu, Rate Us, Home
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Menu Button with title
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { /* TODO: Handle Menu click */ }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
                }
                Text(text = "Menu")
            }

            // Rate Us Button with title
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = {
                    navController.navigate("ratingPage")
                }) {
                    Icon(Icons.Filled.Star, contentDescription = "Rate Us")
                }
                Text(text = "Rate Us")
            }

            // Home Button with title
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { /* TODO: Handle Home click */ }) {
                    Icon(Icons.Filled.Home, contentDescription = "Home")
                }
                Text(text = "Home")
            }
        }
    }
}


@Composable
fun ReviewCardComposable(review: Review) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = review.title, style = MaterialTheme.typography.headlineLarge)
            Text(text = review.content, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            RatingBar(rating = review.rating)
        }
    }
}

@Composable
fun RatingPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Contenu de la page de notation
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = false),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ... Votre contenu ici
            Text(text = "Rate US !", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(24.dp))

            // Ajoutez des éléments pour la notation, comme des étoiles et des questions.
            Text(text = "L'application est-elle facile à utiliser ?")
            RatingBar(rating = 4f) // Définissez la valeur par défaut ou récupérez-la depuis l'état
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Les informations d'accessibilité sont-elles claires ?")
            RatingBar(rating = 4f) // De même ici
            Spacer(modifier = Modifier.height(16.dp))

            // Ajoutez d'autres éléments de notation selon vos besoins

            // Bouton pour soumettre les évaluations
            Button(onClick = { /* TODO: Handle submit click */ }) {
                Text("Submit")
            }
        }

        // Barre de navigation
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Menu Button with title
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { /* TODO: Handle Menu click */ }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
                }
                Text(text = "Menu")
            }


            // Home Button with title
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { /* TODO: Handle Home click */ }) {
                    Icon(Icons.Filled.Home, contentDescription = "Home")
                }
                Text(text = "Home")
            }
        }
    }
}

@Composable
fun RatingBar(rating: Float) {
    Row {
        for (i in 1..5) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Rating",
                modifier = Modifier.size(24.dp),
                tint = if (i <= rating) Color.Yellow else Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    Rate_usTheme {
        RateUsPage(navController)
    }
}

// You'll need to define this data class to represent individual reviews
data class Review(
    val title: String,
    val content: String,
    val rating: Float
)