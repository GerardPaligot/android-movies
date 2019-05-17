package com.paligot.user.connected

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
  lateinit var factory: UserViewModelFactory
  private val viewModel: UserViewModel by activityViewModels { factory }
  private val adapter = UserConnectedAdapter(settingsUi)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return FragmentUserConnectedBinding.inflate(inflater, container, false).run {
      user = UserUi("Andr0", "https://image.tmdb.org/t/p/w300_and_h300_face/c2TyIvn2qHHKLmI2ccsgbydbeql.jpg")
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
  }
}