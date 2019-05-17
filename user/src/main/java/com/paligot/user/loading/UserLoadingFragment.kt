package com.paligot.user.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.paligot.user.main.UserViewModel
import com.paligot.user.main.UserViewModelFactory
import com.paligot.user.databinding.FragmentUserLoadingBinding
import com.paligot.user.userApplication
import javax.inject.Inject

class UserLoadingFragment : Fragment() {
  @Inject
  lateinit var factory: UserViewModelFactory
  private val viewModel: UserViewModel by activityViewModels { factory }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return FragmentUserLoadingBinding.inflate(inflater, container, false).root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    userApplication.injector.maybeInject(this)
    viewModel.updateIsLoggedInfo()
    viewModel.isLogged.observe(this, Observer {
      if (it) {
        findNavController().navigate(UserLoadingFragmentDirections.actionLoadingFragmentToUserConnectedFragment())
      } else {
        findNavController().navigate(UserLoadingFragmentDirections.actionLoadingFragmentToUserDisconnectedFragment())
      }
    })
  }
}