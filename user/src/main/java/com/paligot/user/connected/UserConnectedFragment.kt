package com.paligot.user.connected

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.paligot.user.NavigationUserDirections
import com.paligot.user.databinding.FragmentUserConnectedBinding
import com.paligot.user.userApplication
import javax.inject.Inject

class UserConnectedFragment : Fragment() {
  @Inject
  internal lateinit var viewModel: UserConnectedViewModel
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
    viewModel.updateIsLoggedInfo()
    viewModel.isNotLogged.observe(this, Observer<Boolean> {
      findNavController().navigate(NavigationUserDirections.actionToUserDisconnectedFragment())
    })
    adapter.itemSelected.observe(this, Observer {
      viewModel.logout()
      viewModel.updateIsLoggedInfo()
    })
  }
}