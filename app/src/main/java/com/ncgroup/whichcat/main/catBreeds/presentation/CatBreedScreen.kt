package com.ncgroup.whichcat.main.catBreeds.presentation



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.ncgroup.whichcat.main.catBreeds.domain.model.CatBreed
import com.ncgroup.whichcat.main.catBreeds.domain.model.Image
import com.ncgroup.whichcat.main.catBreeds.domain.model.Weight
import com.ncgroup.whichcat.main.components.SnackBarMessage
import com.ncgroup.whichcat.ui.theme.LocalThemeIsDark
import kotlinx.coroutines.flow.flowOf

private typealias navigateToCatsScreen = (String) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatBreedScreen(
    catsBreedViewModel: CatBreedViewModel = viewModel (factory = CatBreedViewModel.Factory),
    navigateToCatsScreen: navigateToCatsScreen,
){

    val pullToRefreshState = rememberPullToRefreshState()

    val catBreeds = catsBreedViewModel.catBreeds.collectAsLazyPagingItems()

    CatBreedScreenContent(
        pullToRefreshState = pullToRefreshState,
        catBreeds = catBreeds,
        refresh = {
            catBreeds.refresh()
        },
        navigateToCatsScreen = navigateToCatsScreen
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatBreedScreenContent(
    modifier: Modifier = Modifier,
    pullToRefreshState: PullToRefreshState,
    catBreeds: LazyPagingItems<CatBreed>,
    refresh: () -> Unit,
    navigateToCatsScreen: navigateToCatsScreen,
){

    val listState = rememberLazyListState()
    val hostState = remember { SnackbarHostState() }

    var isDark by LocalThemeIsDark.current

    var catsRefreshing by remember {
        mutableStateOf(false)
    }

    if (pullToRefreshState.isRefreshing){
        LaunchedEffect(true) {
            refresh()
        }
    }

    if (catsRefreshing){
        pullToRefreshState.startRefresh()
    } else {
        pullToRefreshState.endRefresh()
    }

    Scaffold(
        snackbarHost = {
            SnackBarMessage(
                hostState = hostState
            )
        }
    ) { paddingValues ->

        Box(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                .nestedScroll(pullToRefreshState.nestedScrollConnection)
        ) {

            when (val loadState = catBreeds.loadState.refresh) {
                is LoadState.Loading -> {
                    catsRefreshing = true
                }

                is LoadState.Error -> {
                    pullToRefreshState.endRefresh()
                    catsRefreshing = false
                    LaunchedEffect(Unit) {
                        hostState.showSnackbar(loadState.error.message.orEmpty())
                    }
                }

                is LoadState.NotLoading -> {
                    catsRefreshing = false
                }
            }

            LazyColumn(
                modifier = modifier.fillMaxSize(),
                state = listState,
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                item {
                    Box(
                        modifier = modifier
                            .padding(
                                top = 24.dp,
                                bottom = 24.dp
                            )
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Discover different\ncat breeds",
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal
                            ),
                            modifier = modifier
                                .padding(end = 16.dp)
                                .align(Alignment.TopStart)
                        )
                        Surface(
                            modifier = modifier
                                .size(24.dp)
                                .align(Alignment.TopEnd)
                                .clickable {
                                    isDark = !isDark
                                }
                        ) {
                            Icon(
                                imageVector = if (isDark) Icons.Default.DarkMode else Icons.Default.LightMode,
                                contentDescription = "dark mode"
                            )
                        }
                    }
                }

                items(
                    items = catBreeds.itemSnapshotList.items
                ) { breed ->

                    OutlinedCard(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(340.dp)
                            .clickable {
                                navigateToCatsScreen(breed.catBreedId)
                            },
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Column(
                            modifier = modifier
                                .fillMaxSize()
                        ) {
                            Box(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .weight(0.80f)
                            ) {
                                AsyncImage(
                                    model = breed.image.url,
                                    contentDescription = breed.name,
                                    modifier = modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Box(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .weight(0.20f)
                            ) {
                                Text(
                                    text = breed.name,
                                    style = TextStyle(
                                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                                        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle
                                    ),
                                    modifier = modifier
                                        .padding(start = 16.dp)
                                        .align(Alignment.CenterStart)
                                )
                            }
                        }
                    }
                }

                item {
                    if (catBreeds.loadState.append is LoadState.Loading) {
                        Box(
                            modifier = modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ){
                            CircularProgressIndicator()
                        }
                    }
                }

            }

            PullToRefreshContainer(
                state = pullToRefreshState,
                modifier = modifier.align(Alignment.TopCenter)
            )

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun CatBreedScreenContentPreview(){
    CatBreedScreenContent(
        pullToRefreshState = rememberPullToRefreshState(),
        catBreeds = flowOf(PagingData.from(
            listOf(
                CatBreed(
                    id = 1,
                    catBreedId = "breed 1",
                    adaptability = 3,
                    affectionLevel = 4,
                    altNames = "Default Cat",
                    cfaUrl = "https://example.com",
                    childFriendly = 4,
                    countryCode = "US",
                    countryCodes = "US",
                    description = "Default cat breed description",
                    dogFriendly = 3,
                    energyLevel = 3,
                    experimental = 0,
                    grooming = 3,
                    hairless = 0,
                    healthIssues = 2,
                    hypoallergenic = 0,
                    image = Image(0, "", "", 0),
                    indoor = 1,
                    intelligence = 3,
                    lap = 4,
                    lifeSpan = "12-15 years",
                    name = "Cat Breed 1",
                    natural = 1,
                    origin = "Unknown",
                    rare = 0,
                    referenceImageId = "default_image_id",
                    rex = 0,
                    sheddingLevel = 2,
                    shortLegs = 0,
                    socialNeeds = 3,
                    strangerFriendly = 3,
                    suppressedTail = 0,
                    temperament = "Friendly",
                    vcahospitalsUrl = "https://example.com/vca",
                    vetstreetUrl = "https://example.com/vetstreet",
                    vocalisation = 2,
                    weight = Weight("", ""),
                    wikipediaUrl = "https://en.wikipedia.org/wiki/Default_Cat_Breed"
                ),
                CatBreed(
                    id = 2,
                    catBreedId = "breed 2",
                    adaptability = 3,
                    affectionLevel = 4,
                    altNames = "Default Cat",
                    cfaUrl = "https://example.com",
                    childFriendly = 4,
                    countryCode = "US",
                    countryCodes = "US",
                    description = "Default cat breed description",
                    dogFriendly = 3,
                    energyLevel = 3,
                    experimental = 0,
                    grooming = 3,
                    hairless = 0,
                    healthIssues = 2,
                    hypoallergenic = 0,
                    image = Image(0, "", "", 0),
                    indoor = 1,
                    intelligence = 3,
                    lap = 4,
                    lifeSpan = "12-15 years",
                    name = "Cat Breed 2 ",
                    natural = 1,
                    origin = "Unknown",
                    rare = 0,
                    referenceImageId = "default_image_id",
                    rex = 0,
                    sheddingLevel = 2,
                    shortLegs = 0,
                    socialNeeds = 3,
                    strangerFriendly = 3,
                    suppressedTail = 0,
                    temperament = "Friendly",
                    vcahospitalsUrl = "https://example.com/vca",
                    vetstreetUrl = "https://example.com/vetstreet",
                    vocalisation = 2,
                    weight = Weight("", ""),
                    wikipediaUrl = "https://en.wikipedia.org/wiki/Default_Cat_Breed"
                ),
                CatBreed(
                    id = 3,
                    catBreedId = "breed 3",
                    adaptability = 3,
                    affectionLevel = 4,
                    altNames = "Default Cat",
                    cfaUrl = "https://example.com",
                    childFriendly = 4,
                    countryCode = "US",
                    countryCodes = "US",
                    description = "Default cat breed description",
                    dogFriendly = 3,
                    energyLevel = 3,
                    experimental = 0,
                    grooming = 3,
                    hairless = 0,
                    healthIssues = 2,
                    hypoallergenic = 0,
                    image = Image(0, "", "", 0),
                    indoor = 1,
                    intelligence = 3,
                    lap = 4,
                    lifeSpan = "12-15 years",
                    name = "Cat Breed 3",
                    natural = 1,
                    origin = "Unknown",
                    rare = 0,
                    referenceImageId = "default_image_id",
                    rex = 0,
                    sheddingLevel = 2,
                    shortLegs = 0,
                    socialNeeds = 3,
                    strangerFriendly = 3,
                    suppressedTail = 0,
                    temperament = "Friendly",
                    vcahospitalsUrl = "https://example.com/vca",
                    vetstreetUrl = "https://example.com/vetstreet",
                    vocalisation = 2,
                    weight = Weight("", ""),
                    wikipediaUrl = "https://en.wikipedia.org/wiki/Default_Cat_Breed"
                )
            )
        )).collectAsLazyPagingItems(),
        refresh = {},
        navigateToCatsScreen = {}
    )
}