package com.example.cointrackerapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cointrackerapp.viewmodel.CoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.cointrackerapp.presentation.screen.CoinListScreen
import kotlinx.coroutines.flow.asStateFlow
import com.example.cointrackerapp.ui.theme.CoinTrackerAppTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            CoinTrackerAppTheme {
                Scaffold {
                    CoinListScreen(modifier = Modifier.padding(it))
                }
            }
        }
    }
}