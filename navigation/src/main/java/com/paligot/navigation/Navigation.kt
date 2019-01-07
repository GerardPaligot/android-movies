package com.paligot.navigation

sealed class Navigation
object UserNavigation : Navigation()
object BackNavigation : Navigation()