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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.PuppyAdoptionTheme

@Composable
@Preview
fun PreviewPuppyDetails() {
    PuppyDetails()
}

@Composable
fun PuppyDetails() {
    PuppyAdoptionTheme() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Puppy Details")
                    }
                )
            }
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)

            Column {
                Image(
                    painter = painterResource(R.drawable.dog1),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(4.dp))
                        .fillMaxWidth(),
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = "Name: Max",
                    style = MaterialTheme.typography.h4
                )
                Text(
                    text = "Size: S",
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = "Age: 3",
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = "Gender: Male",
                    style = MaterialTheme.typography.h5
                )

                Text(
                    text = "The best dog ever",
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}
