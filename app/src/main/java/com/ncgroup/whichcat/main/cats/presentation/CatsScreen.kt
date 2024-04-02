package com.ncgroup.whichcat.main.cats.presentation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.ncgroup.whichcat.main.cats.domain.model.BreedCatDetail
import com.ncgroup.whichcat.main.cats.domain.model.CatDetail
import com.ncgroup.whichcat.main.cats.domain.model.WeightCatDetail
import com.ncgroup.whichcat.main.components.ProgressBar
import com.ncgroup.whichcat.main.components.SnackBarMessage
import kotlinx.coroutines.flow.flowOf


private typealias navigateToCatDetailScreen = (catDetail: CatDetail) -> Unit
private typealias navigateBack = () -> Unit


@Composable
fun CatsScreen(
    catsViewModel: CatsViewModel = viewModel(factory = CatsViewModel.Factory),
    id: String,
    navigateToCatDetailScreen: navigateToCatDetailScreen,
    navigateBack: navigateBack
) {

    val cats = catsViewModel.cats.collectAsLazyPagingItems()

    LaunchedEffect(id) {
        catsViewModel.getCats(id)
    }

    CatsScreenContent(
        cats = cats,
        navigateToCatDetailScreen = navigateToCatDetailScreen,
        navigateBack = navigateBack
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatsScreenContent(
    modifier: Modifier = Modifier,
    cats: LazyPagingItems<CatDetail>,
    navigateToCatDetailScreen: navigateToCatDetailScreen,
    navigateBack: navigateBack
) {

    val listState = rememberLazyListState()
    val hostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigateBack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "navigate back"
                        )
                    }
                }
            )
        },
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
        ) {

            when (val loadState = cats.loadState.refresh) {

                is LoadState.Loading -> {
                    ProgressBar()
                }

                is LoadState.Error -> {
                    LaunchedEffect(Unit) {
                        hostState.showSnackbar(loadState.error.message.orEmpty())
                    }
                }

                is LoadState.NotLoading -> {

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
                                        bottom = 24.dp
                                    )
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = cats.itemSnapshotList.firstOrNull()?.breed?.name.orEmpty(),
                                    style = TextStyle(
                                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                                        fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                                        fontStyle = MaterialTheme.typography.headlineLarge.fontStyle
                                    )
                                )
                            }
                        }

                        items(
                            items = cats.itemSnapshotList.items
                        ) { cat ->

                            OutlinedCard(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .height(340.dp),
                                shape = RoundedCornerShape(24.dp)
                            ) {
                                Column(
                                    modifier = modifier.fillMaxSize()
                                ) {
                                    Box(
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .weight(0.80f)
                                    ) {
                                        AsyncImage(
                                            model = cat.url,
                                            contentDescription = cat.breed?.name,
                                            modifier = modifier.fillMaxSize(),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                    Box(
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .weight(0.20f)
                                    ) {
                                        TextButton(
                                            onClick = {
                                                navigateToCatDetailScreen(cat)
                                            },
                                            modifier = modifier
                                                .padding(start = 4.dp)
                                                .align(Alignment.CenterStart)
                                        ) {
                                            Text(
                                                text = "View",
                                                fontSize = 16.sp
                                            )
                                        }
                                    }
                                }
                            }

                        }

                        item {
                            if (cats.loadState.append is LoadState.Loading) {
                                CircularProgressIndicator()
                            }
                        }

                    }

                }

            }




        }

    }

}


@Preview
@Composable
fun CatsScreenContentPreview() {
    CatsScreenContent(
        cats = flowOf(PagingData.from(
                listOf(
                    CatDetail(
                        id = 1,
                        breedId = "Breed Id 1",
                        breed = BreedCatDetail(
                            id = "",
                            adaptability = 0,
                            affectionLevel = 0,
                            altNames = "",
                            cfaUrl = "",
                            childFriendly = 0,
                            countryCode = "",
                            countryCodes = "",
                            description = "",
                            dogFriendly = 0,
                            energyLevel = 0,
                            experimental = 0,
                            grooming = 0,
                            hairless = 0,
                            healthIssues = 0,
                            hypoallergenic = 0,
                            indoor = 0,
                            intelligence = 0,
                            lap = 0,
                            lifeSpan = "",
                            name = "Nile Cat",
                            natural = 0,
                            origin = "",
                            rare = 0,
                            referenceImageId = "",
                            rex = 0,
                            sheddingLevel = 0,
                            shortLegs = 0,
                            socialNeeds = 0,
                            strangerFriendly = 0,
                            suppressedTail = 0,
                            temperament = "",
                            vcahospitalsUrl = "",
                            vetstreetUrl = "",
                            vocalisation = 0,
                            weight = WeightCatDetail(
                                imperial = "",
                                metric = ""
                            ),
                            wikipediaUrl = ""
                        ),
                        url = "empty",
                        width = 0,
                        height = 0
                    ),
                    CatDetail(
                        id = 2,
                        breedId = "Breed Id 1",
                        breed = BreedCatDetail(
                            id = "",
                            adaptability = 0,
                            affectionLevel = 0,
                            altNames = "",
                            cfaUrl = "",
                            childFriendly = 0,
                            countryCode = "",
                            countryCodes = "",
                            description = "",
                            dogFriendly = 0,
                            energyLevel = 0,
                            experimental = 0,
                            grooming = 0,
                            hairless = 0,
                            healthIssues = 0,
                            hypoallergenic = 0,
                            indoor = 0,
                            intelligence = 0,
                            lap = 0,
                            lifeSpan = "",
                            name = "Cat 2",
                            natural = 0,
                            origin = "",
                            rare = 0,
                            referenceImageId = "",
                            rex = 0,
                            sheddingLevel = 0,
                            shortLegs = 0,
                            socialNeeds = 0,
                            strangerFriendly = 0,
                            suppressedTail = 0,
                            temperament = "",
                            vcahospitalsUrl = "",
                            vetstreetUrl = "",
                            vocalisation = 0,
                            weight = WeightCatDetail(
                                imperial = "",
                                metric = ""
                            ),
                            wikipediaUrl = ""
                        ),
                        url = "empty",
                        width = 0,
                        height = 0
                    ),
                    CatDetail(
                        id = 3,
                        breedId = "Breed Id 1",
                        breed = BreedCatDetail(
                            id = "",
                            adaptability = 0,
                            affectionLevel = 0,
                            altNames = "",
                            cfaUrl = "",
                            childFriendly = 0,
                            countryCode = "",
                            countryCodes = "",
                            description = "",
                            dogFriendly = 0,
                            energyLevel = 0,
                            experimental = 0,
                            grooming = 0,
                            hairless = 0,
                            healthIssues = 0,
                            hypoallergenic = 0,
                            indoor = 0,
                            intelligence = 0,
                            lap = 0,
                            lifeSpan = "",
                            name = "Cat 3",
                            natural = 0,
                            origin = "",
                            rare = 0,
                            referenceImageId = "",
                            rex = 0,
                            sheddingLevel = 0,
                            shortLegs = 0,
                            socialNeeds = 0,
                            strangerFriendly = 0,
                            suppressedTail = 0,
                            temperament = "",
                            vcahospitalsUrl = "",
                            vetstreetUrl = "",
                            vocalisation = 0,
                            weight = WeightCatDetail(
                                imperial = "",
                                metric = ""
                            ),
                            wikipediaUrl = ""
                        ),
                        url = "empty",
                        width = 0,
                        height = 0
                    )
                ),
            )).collectAsLazyPagingItems(),
        navigateToCatDetailScreen = {},
        navigateBack = {}
    )
}