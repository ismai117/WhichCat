package com.ncgroup.whichcat.main.cats.presentation



import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ncgroup.whichcat.main.cats.domain.model.BreedCatDetail
import com.ncgroup.whichcat.main.cats.domain.model.CatDetail
import com.ncgroup.whichcat.main.cats.domain.model.WeightCatDetail
import io.ktor.http.Url


@Composable
fun CatDetailScreen(
    catDetail: CatDetail?,
    navigateBack: () -> Unit
){

    CatDetailScreenContent(
        catDetail = catDetail,
        navigateBack = navigateBack
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatDetailScreenContent(
    modifier: Modifier = Modifier,
    catDetail: CatDetail?,
    navigateBack: () -> Unit
) {

    val scrollState = rememberScrollState()

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = catDetail?.breed?.name.orEmpty()
                    )
                },
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
        }
    ) { paddingValues ->

        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = modifier
                    .padding(top = 40.dp)
                    .size(280.dp)
                    .border(width = 1.dp, color = Color.White, shape = CircleShape)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ){
                AsyncImage(
                    model = catDetail?.url,
                    contentDescription = catDetail?.breed?.name,
                    modifier = modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }


            Column(
                modifier = modifier
                    .padding(top = 60.dp, bottom = 40.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                CatDetailItem(
                    title = "Breed",
                    body = catDetail?.breed?.name.orEmpty()
                )

                CatDetailItem(
                    title = "Type",
                    body = catDetail?.breed?.altNames.orEmpty()
                )

                CatDetailItem(
                    title = "Origin",
                    body = catDetail?.breed?.origin.orEmpty()
                )

                CatDetailItem(
                    title = "Country Code",
                    body = catDetail?.breed?.countryCode.orEmpty()
                )

                CatDetailItem(
                    title = "Description",
                    body = catDetail?.breed?.description.orEmpty()
                )

                CatDetailItem(
                    title = "Life Span",
                    body = "${catDetail?.breed?.lifeSpan.orEmpty()}  yrs"
                )

                CatDetailItemWiki(
                    title = "Wiki",
                    openLink = {
                        openUrl(context, catDetail?.breed?.wikipediaUrl.orEmpty())
                    }
                )

            }


        }

    }


}

@Composable
fun CatDetailItem(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
){
    Column(
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){

        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = body,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

    }
}

@Composable
fun CatDetailItemWiki(
    modifier: Modifier = Modifier,
    title: String,
    openLink: () -> Unit,
){
    Row(
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){

        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        TextButton(
            onClick = {
                openLink()
            },
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "(View)",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

    }
}

@Preview
@Composable
fun CatDetailScreenContentPreview(){
    CatDetailScreenContent(
        catDetail = CatDetail(
            id = 1,
            breedId = "Breed Id 1",
            breed = BreedCatDetail(
                id = "",
                adaptability = 0,
                affectionLevel = 0,
                altNames = "Nile Cat",
                cfaUrl = "",
                childFriendly = 0,
                countryCode = "EG",
                countryCodes = "",
                description = LoremIpsum(50).values.joinToString(),
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
                lifeSpan = "12-14",
                name = "Chausie",
                natural = 0,
                origin = "Egypt",
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
        navigateBack = {}
    )
}

fun openUrl(context: Context, url: String){
    val uri = Uri.parse(url)
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        data = uri
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(intent)
}