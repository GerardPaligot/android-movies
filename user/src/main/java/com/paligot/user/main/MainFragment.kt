package com.paligot.user.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.paligot.user.R
import com.paligot.user.databinding.FragmentUserMainBinding
import com.paligot.user.userApplication

class MainFragment : Fragment() {

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    context?.let {
      userApplication.onCreate(it)
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return FragmentUserMainBinding.inflate(inflater, container, false).run {
      toolbar.title = getString(R.string.user_toolbar)
      toolbar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_close)
      return@run root
    }
  }
}