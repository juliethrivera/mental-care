package com.laura0393.loign.ui.commons

//Aqui va sus comentarios!!
enum class Screen {
    LOGIN,
    SIGN_IN,
    SIGM_UP
}
// esto sirve para navecacion
sealed class NavigationItem (val route: String ){
    object Login: NavigationItem(Screen.LOGIN.name)
    object SignIn: NavigationItem(Screen.SIGN_IN.name)
    object SignUp: NavigationItem(Screen.SIGM_UP.name)
}