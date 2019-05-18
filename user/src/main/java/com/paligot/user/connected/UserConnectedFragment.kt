package com.paligot.user.connected

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.paligot.user.R
import com.paligot.user.databinding.FragmentUserConnectedBinding
import com.paligot.user.main.UserViewModel
import com.paligot.user.main.UserViewModelFactory
import com.paligot.user.userApplication
import javax.inject.Inject

class UserConnectedFragment : Fragment() {
  @Inject
  lateinit var userFactory: UserViewModelFactory
  @Inject
  lateinit var connectedFactory: UserConnectedViewModelFactory
  private val viewModel: UserViewModel by activityViewModels { userFactory }
  private val connectedViewModel: UserConnectedViewModel by viewModels { connectedFactory }
  private val adapter = UserConnectedAdapter(settingsUi)
  private lateinit var binding: FragmentUserConnectedBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return FragmentUserConnectedBinding.inflate(inflater, container, false).run {
      binding = this
      actionRecyclerView.adapter = adapter
      return@run root
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    userApplication.injector.maybeInject(this)
    adapter.itemSelected.observe(this, Observer {
      viewModel.logout()
    })
    viewModel.sessionDeleted.observe(this, Observer {
      if (it) {
        requireActivity().finish()
      } else {
        Snackbar.make(view, R.string.user_impossible_disconnect_you, Snackbar.LENGTH_SHORT).show()
      }
    })
    connectedViewModel.requestAccount()
    connectedViewModel.user.observe(this, Observer {
      binding.user = it
    })
  }
}