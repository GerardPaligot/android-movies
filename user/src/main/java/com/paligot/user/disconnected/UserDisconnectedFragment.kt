package com.paligot.user.disconnected

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.paligot.shared.services.TheMovieDatabaseService
import com.paligot.user.R
import com.paligot.user.databinding.FragmentUserDisconnectedBinding
import com.paligot.user.userApplication
import javax.inject.Inject

class UserDisconnectedFragment : Fragment() {
  @Inject
  internal lateinit var viewModel: UserDisconnectedViewModel
  internal lateinit var client: LoginWebViewClient
  internal lateinit var binding: FragmentUserDisconnectedBinding

  @SuppressLint("SetJavaScriptEnabled")
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return FragmentUserDisconnectedBinding.inflate(inflater, container, false).run {
      client = LoginWebViewClient()
      connectionWebView.webViewClient = client
      connectionWebView.webChromeClient = WebChromeClient()
      connectionWebView.settings.apply {
        javaScriptEnabled = true
      }
      binding = this
      return@run root
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    userApplication.injector.maybeInject(this)
    viewModel.updateToken()
    viewModel.token.observe(this, Observer {
      binding.connectionWebView.loadUrl(TheMovieDatabaseService.URL_AUTH.format(it))
    })
    viewModel.sessionSaved.observe(this, Observer {
      findNavController().navigate(UserDisconnectedFragmentDirections.actionUserDisconnectedFragmentToUserConnectedFragment())
    })
    viewModel.error.observe(this, Observer {
      Snackbar.make(view, R.string.user_impossible_connect_server, Snackbar.LENGTH_SHORT).show()
      requireActivity().finish()
    })
    client.event.observe(this, Observer {
      if (it == Status.ALLOW) {
        viewModel.session()
      } else {
        Snackbar.make(view, R.string.user_deny, Snackbar.LENGTH_SHORT).show()
        requireActivity().finish()
      }
    })
  }
}