package com.paligot.user.disconnected

import android.annotation.TargetApi
import android.os.Build
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.paligot.shared.livedata.SingleLiveEvent
import com.paligot.shared.services.TheMovieDatabaseService

class LoginWebViewClient : WebViewClient() {
  private val _event by lazy {
    SingleLiveEvent<Status>()
  }

  val event: SingleLiveEvent<Status>
    get() = _event

  override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
    if (url != null) {
      return shouldLoadUrl(url)
    }
    return true
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
    return shouldOverrideUrlLoading(view, request?.url?.toString())
  }

  private fun shouldLoadUrl(url: String): Boolean {
    if (url.contains(TheMovieDatabaseService.DOMAIN).not()) {
      return true
    }
    if (url.endsWith("allow")) {
      _event.setValue(Status.ALLOW)
    } else if (url.endsWith("deny")) {
      _event.setValue(Status.DENY)
    }
    return false
  }
}

enum class Status {
  ALLOW, DENY
}
