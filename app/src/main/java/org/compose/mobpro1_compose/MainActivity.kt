package org.compose.mobpro1_compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.compose.mobpro1_compose.ui.theme.Mobpro1composeTheme

class MainActivity : ComponentActivity() {

    private val data = getData()
    private var index by mutableStateOf(0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mobpro1composeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GaleriLampu(data[index]) {
                        index = if (index == data.size-1) 0 else index+1
                    }
                }
            }
        }
    }

    private fun getData() :List<Lampu> {
        return  listOf(
            Lampu("Hidup", R.drawable.hidup),
            Lampu("Mati", R.drawable.mati),

        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(content: @Composable (Modifier) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { padding ->
        content(Modifier.padding(padding))
    }
}

@Composable
fun GaleriLampu(lampu: Lampu, onClick: () -> Unit = {}) {
    MainScreen { modifier ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(lampu.imagesResId),
                contentDescription = stringResource(R.string.gambar, lampu.nama),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(132.dp)
            )
            Text(
                text = lampu.nama,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
            Button(
                onClick = { onClick() },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(top = 24.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = stringResource(R.string.lanjut))
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true) //untuk tema gelap
@Composable
fun ScreenPreview() {
    Mobpro1composeTheme {
        GaleriLampu(Lampu("Hidup", R.drawable.hidup))
    }
}