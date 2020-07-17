package com.vons.mvvm.base

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vons.mvvm.frag.WebViewFragment

open class BaseFragment : Fragment() {

    private val exit = Exit()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (this@BaseFragment is WebViewFragment) {
                    if (this@BaseFragment.getWebContent().canGoBack()) {
                        this@BaseFragment.getWebContent().goBack()
                    } else {
                        this@BaseFragment.getWebContent().loadUrl("about:blank")
//                        findNavController().popBackStack()
                        findNavController().navigateUp()
                    }
                } else {
//                    findNavController().navigateUp()
                    exit()
                }
            }
        })
    }


    private fun exit() {
        if (exit.exit) {
            (BaseApplication.getContext() as BaseApplication).exit()
        } else {
            toast("再按一次退出")
            exit.doExit()
        }
    }
}