package com.paligot.user.connected

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.paligot.style.R

data class UserUi(val username: String, val avatarUrl: String)
data class SettingsUi(@DrawableRes val icon: Int, @StringRes val title: Int)

val settingsUi = arrayListOf(
  SettingsUi(R.drawable.ic_disconnect, com.paligot.user.R.string.user_disconnection)
)