package com.example.newapplication.Screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.newapplication.MyViewModel
import java.net.URLEncoder
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun HomeUI(modifier: Modifier, viewModel: MyViewModel, navController: NavController) {
    val response = viewModel.responce.value?.articles ?: emptyList()

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = Color.Green, fontSize = 45.sp, fontWeight = FontWeight.Bold)) { append("N") }
                withStyle(SpanStyle(color = Color.Gray, fontSize = 35.sp)) { append("ews") }
            },
            modifier = Modifier.align(Alignment.Start).padding(20.dp)
        )

        LazyColumn(modifier = Modifier.fillMaxSize().padding(10.dp)) {
            items(response) { article ->
                cardView(
                    title = article.title ?: "No Title",
                    description = article.description ?: "No Description Available",
                    urlToImage = article.urlToImage ?: "",
                    author = article.author ?: "Unknown Author",
                    url = article.url ?: "",
                    content = article.content ?: "No Content Available",
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun cardView(title: String, description: String, urlToImage: String, author: String, url: String, content: String, navController: NavController) {
    val encodedUrl = URLEncoder.encode(urlToImage, StandardCharsets.UTF_8.toString())
    val encodedContent = URLEncoder.encode(content, StandardCharsets.UTF_8.toString()) // Encode content as well

    Card(
        onClick = { navController.navigate("ContentScreen/$encodedUrl/$encodedContent") },
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(120.dp),
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color.Gray)
    ) {
        Row(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            if (urlToImage.isBlank()) {
                CircularProgressIndicator(
                    color = Color.Green,
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.CenterVertically)
                        .size(60.dp)
                )
            } else {
                AsyncImage(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(100.dp),
                    model = urlToImage,
                    contentDescription = null,
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp)
            ) {
                Text(text = title, color = Color.White, fontSize = 20.sp, maxLines = 1, fontWeight = FontWeight.Bold)
                Text(text = description, color = Color.White, fontSize = 15.sp, maxLines = 2)
                Text(
                    text = author,
                    fontSize = 15.sp,
                    color = Color.White,
                    fontFamily = FontFamily.Cursive,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Composable
fun App(modifier: Modifier, viewModel: MyViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "HomeUI") {
        composable("HomeUI") {
            HomeUI(modifier = Modifier, viewModel = viewModel, navController = navController)
        }
        composable("ContentScreen/{imageUrl}/{content}") { backStackEntry -> // Fix: Use separate placeholders
            val imageUrl = backStackEntry.arguments?.getString("imageUrl")?.let {
                URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
            } ?: ""

            val content = backStackEntry.arguments?.getString("content")?.let {
                URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
            } ?: "Content is not available"

            ContentScreen(url = imageUrl, content = content)
        }
    }
}

@Composable
fun ContentScreen(url: String, content: String) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = Color.Green, fontSize = 45.sp, fontWeight = FontWeight.Bold)) { append("N") }
                withStyle(SpanStyle(color = Color.Gray, fontSize = 35.sp)) { append("ews") }
            },
            modifier = Modifier.align(Alignment.Start).padding(20.dp)
        )

        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                model = url,
                contentDescription = "News Image",
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = content,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
