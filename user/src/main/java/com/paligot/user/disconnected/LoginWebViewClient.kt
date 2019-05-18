package com.paligot.user.disconnected

import android.annotation.TargetApi
import android.graphics.Bitmap
import android.os.Build
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.LiveData
import com.paligot.shared.livedata.SingleLiveEvent
import com.paligot.shared.services.TheMovieDatabaseService

class LoginWebViewClient : WebViewClient() {
  private val _event by lazy {
    SingleLiveEvent<Status>()
  }

  val event: LiveData<Status>
    get() = _event

  override fun onPageFinished(view: WebView?, url: String?) {
    super.onPageFinished(view, url)
    if (url == null) return
    if (url.endsWith("allow") || url.endsWith("approve")) {
      _event.setValue(Status.ALLOW)
    } else if (url.endsWith("deny")) {
      _event.setValue(Status.DENY)
    } else {
      _event.setValue(Status.UNKNOWN)
    }
  }

  override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
    super.onPageStarted(view, url, favicon)
  }

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
    return url.contains(TheMovieDatabaseService.DOMAIN).not()
  }
}

enum class Status {
  ALLOW, DENY, UNKNOWN
}
