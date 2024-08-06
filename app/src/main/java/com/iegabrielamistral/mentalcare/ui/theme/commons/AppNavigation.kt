package com.manuelnatera1.loign.ui.commons

//Aqui va sus comentarios!!
enum class Screen {
    LOGIN,
    SIGN_IN,
    SIGN_UP,
    HOME,
    SETTINGS
}
// esto sirve para navecacion
sealed class NavigationItem (val route: String ){
    object Login: NavigationItem(Screen.LOGIN.name)
    object SignIn: NavigationItem(Screen.SIGN_IN.name)
    object SignUp: NavigationItem(Screen.SIGN_UP.name)
    object Home: NavigationItem(Screen.HOME.name)
    object Settings: NavigationItem(Screen.SETTINGS.name)
}