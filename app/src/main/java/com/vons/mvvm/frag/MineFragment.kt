package com.vons.mvvm.frag

import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.vons.mvvm.R
import com.vons.mvvm.base.BaseFragment
import com.vons.mvvm.databinding.FragmentMineBinding

class MineFragment : BaseFragment(), LoaderManager.LoaderCallbacks<Cursor> {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = FragmentMineBinding.inflate(inflater)
        findNavController()
        dataBinding.tvLogin.setOnClickListener {
            val action = MineFragmentDirections.actionMineFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        val args = Bundle().apply {
            putString("name", "李四")
        }
        LoaderManager.getInstance(this).initLoader(0, args, this)
        return dataBinding.root
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {

        val mSearchString = args!!["name"]
        val selectionArgs = arrayOf("%$mSearchString%")
        return CursorLoader(
            requireContext(),
            ContactsContract.Contacts.CONTENT_URI,
            null,
            "",
            selectionArgs,
            ""
        )
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        SimpleCursorAdapter(
            requireContext(),
            R.layout.official_content_item,
            null,
            null,
            null,
            0
        ).swapCursor(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        SimpleCursorAdapter(
            requireContext(),
            R.layout.official_content_item,
            null,
            null,
            null,
            0
        ).swapCursor(null)
    }
}