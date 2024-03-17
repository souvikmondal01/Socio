package com.kivous.socio.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions

object BottomNavContainerScreen : Screen {
    @Composable
    override fun Content() {
        BottomNavContainerScreen()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavContainerScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        TabNavigator(tab = HomeTab) {
            Scaffold(
                bottomBar = {
                    NavigationBar {
                        TabNavigationItems(tab = HomeTab)
                        TabNavigationItems(tab = ProfileTab)
                    }
                }
            ) {
                CurrentTab()
            }
        }
    }
}

@Composable
fun RowScope.TabNavigationItems(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = {
            tabNavigator.current = tab
        },
        icon = {
            Icon(painter = tab.options.icon!!, contentDescription = tab.options.title)
        })
}

object HomeTab : Tab {
    override val options: TabOptions
        @Composable get() {
            val title = "Home"
            val icon = rememberVectorPainter(Icons.Rounded.Home)
            return remember {
                TabOptions(
                    0u, title, icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(screen = HomeScreen)
    }

}

object ProfileTab : Tab {
    override val options: TabOptions
        @Composable get() {
            val title = "Profile"
            val icon = rememberVectorPainter(Icons.Rounded.Person)
            return remember {
                TabOptions(
                    1u, title, icon
                )
            }
        }

    @Composable
    override fun Content() {
        ProfileScreen()
    }

}