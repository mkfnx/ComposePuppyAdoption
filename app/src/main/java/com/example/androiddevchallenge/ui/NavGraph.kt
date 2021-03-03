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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController

object MainDestinations {
    const val PUPPIES_LIST = "list"
    const val PUPPY_DETAILS = "details"
    const val PUPPY_DETAIL_ID_KEY = "puppyId"
}

@Composable
fun NavGraph(startDestination: String = MainDestinations.PUPPIES_LIST) {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.PUPPIES_LIST) {
            PuppiesList(actions.selectPuppy)
        }
        composable(
            "${MainDestinations.PUPPY_DETAILS}/{${MainDestinations.PUPPY_DETAIL_ID_KEY}}",
            arguments = listOf(
                navArgument(MainDestinations.PUPPY_DETAIL_ID_KEY) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val id =  arguments.getInt(MainDestinations.PUPPY_DETAIL_ID_KEY, -1)
            PuppyDetails()
        }
    }
}

class MainActions(navController: NavHostController) {
    val selectPuppy: (Int) -> Unit = { puppyIndex: Int ->
        navController.navigate("${MainDestinations.PUPPY_DETAILS}/$puppyIndex")
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}
