/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.PuppyAdoptionTheme
import kotlin.random.Random

data class Puppy(
    val name: String,
    val size: String,
    val gender: String,
    val age: Int,
)

@Preview
@Composable
fun PreviewPuppiesList() {
    PuppiesList({ })
}

@Composable
fun PuppiesList(onPuppySelected: (Int) -> Unit) {
    val puppiesData = remember {
        examplePuppiesData
    }

    PuppyAdoptionTheme() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Puppy Adoption")
                    }
                )
            }
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)

            LazyColumn(
                modifier
            ) {
                itemsIndexed(puppiesData) { index, sectionData ->
                    PuppySection(sectionData, index == 0, onPuppySelected)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPuppySection() {
    PuppySection(exampleSection(), true, {})
}

@Composable
fun PuppySection(section: Section, isFeaturedSection: Boolean, onPuppySelected: (Int) -> Unit) {
    val scrollState = rememberLazyListState()

    Column() {
        Text(
            text = section.title,
            style = if (isFeaturedSection) MaterialTheme.typography.h5 else MaterialTheme.typography.h6
        )

        LazyRow(state = scrollState, modifier = Modifier.padding(8.dp)) {
            itemsIndexed(section.items) { index, puppyData ->
                PuppyListItem(puppyData, isFeaturedSection, onPuppySelected)
            }
        }
    }
}

@Preview
@Composable
fun PreviewPuppyListItem() {
    PuppyListItem(fakePuppy(), true, {})
}

@Composable
fun PuppyListItem(puppy: Puppy, isFeaturedSection: Boolean, onPuppySelected: (Int) -> Unit) {
    Card(
        modifier = Modifier.clickable(onClick = { onPuppySelected(1) }),
        elevation = PuppyAdoptionTheme.elevations.card
    ) {
        Column(Modifier.padding(8.dp)) {
            val imgW = if (isFeaturedSection) 300.dp else 150.dp
            val imgH = if (isFeaturedSection) 200.dp else 100.dp

            Image(
                painter = painterResource(randomPuppyImage()),
                contentDescription = null,
                modifier = Modifier
                    .size(imgW, imgH)
                    .clip(shape = RoundedCornerShape(4.dp))
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
            Surface(Modifier.background(MaterialTheme.colors.primary)) {
                Text(
                    text = puppy.name,
                    style = if (isFeaturedSection) MaterialTheme.typography.h5 else MaterialTheme.typography.h6
                )
            }
            if (isFeaturedSection) {
                Spacer(modifier = Modifier.padding(8.dp))
                Column {
                    Row {
                        Text("Size:")
                        Text(" ${puppy.size}")
                    }
                    Row {
                        Text("Age: ")
                        Text(" ${puppy.age}")
                    }
                    Row {
                        Text("Gender: ")
                        Text(" ${puppy.gender}")
                    }
                }
            }
        }
    }
}

/**
 *
 */

val examplePuppiesData = listOf(
    Section(
        "Featured Puppies",
        listOf(
            fakePuppy(),
            fakePuppy(),
            fakePuppy(),
            fakePuppy(),
            fakePuppy(),
        )
    ),
    Section(
        "Male Puppies",
        listOf(
            fakePuppy(),
            fakePuppy(),
            fakePuppy(),
            fakePuppy(),
            fakePuppy(),
        )
    ),
    Section(
        "Small Puppies",
        listOf(
            fakePuppy(),
            fakePuppy(),
            fakePuppy(),
            fakePuppy(),
            fakePuppy(),
        )
    ),
    Section(
        "Senior Puppies",
        listOf(
            fakePuppy(),
            fakePuppy(),
            fakePuppy(),
            fakePuppy(),
            fakePuppy(),
        )
    ),
)

fun exampleSection() =
    Section(
        "Example Section",
        listOf(
            fakePuppy(),
            fakePuppy(),
            fakePuppy(),
            fakePuppy(),
            fakePuppy(),
        )
    )

fun fakePuppy() =
    Puppy(
        listOf("Charlie", "Max", "Coco", "Teddie", "Milo", "Toby", "Jack", "Ollie", "Buddy").random(),
        listOf("S", "M", "L").random(),
        "M",
        (1..5).toList().random(),
    )

data class Section(
    val title: String,
    val items: List<Puppy>,
)

fun randomPuppyImage(): Int {
    val puppiesPhotos = listOf(
        R.drawable.dog1,
        R.drawable.dog2,
        R.drawable.dog3,
        R.drawable.dog4,
        R.drawable.dog6,
        R.drawable.dog7,
        R.drawable.dog8,
        R.drawable.dog9,
    )
    return puppiesPhotos[(Random.nextInt(puppiesPhotos.size))]
}
