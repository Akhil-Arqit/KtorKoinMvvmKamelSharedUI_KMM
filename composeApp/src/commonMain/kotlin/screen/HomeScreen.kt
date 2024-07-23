package screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import model.Data


@Composable
@Preview
fun HomeScreen() {
    MaterialTheme {
        KoinContext {
            Column(Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                GridScreen()
            }
        }
    }
}

@OptIn(KoinExperimentalAPI::class)
@Composable
fun GridScreen() {
    val viewModel = koinViewModel<HomeScreenViewModel>()
    val initialList: MutableList<Data> = mutableListOf(
        Data(1, "adgw", "eg", "egbvws", "aedg"),
        Data(2, "sdgsa", "gdage", "sfdfg", "sdg"),
        Data(3, "dsafg", "aerta", "ader", "adgf"),
        Data(4, "zdf", "zdf", "zdg", "dzdg"),
        Data(5, "dsafg", "aerta", "ader", "adgf"),
        Data(6, "zdf", "zdf", "zdg", "dzdg")

    )
    val uiData by viewModel.data.collectAsState(initialList)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(uiData.size) { item ->
            GridItem(uiData[item])
        }
    }
}

@Composable
fun GridItem(item: Data) {
    Surface(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        color = MaterialTheme.colors.onSurface
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            KamelImage(
                resource = asyncPainterResource(item.avatar),
                contentDescription = "Profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp).clip(RoundedCornerShape(8.dp))
            )
            Text(
                text = item.first_name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
