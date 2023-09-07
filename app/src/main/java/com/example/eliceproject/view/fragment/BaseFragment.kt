package com.example.eliceproject.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.eliceproject.util.Navigator
import com.example.eliceproject.util.PrintLog
import com.example.eliceproject.util.createDataBinding

abstract class BaseFragment<VB : ViewDataBinding, BD : Navigator>(@LayoutRes private val resId: Int) :
    Fragment() {
    protected val fragmentTag: String = javaClass.simpleName

    abstract val fragmentBundleData: BD

    private lateinit var mBinding: VB
    protected val binding: VB
        get() {
            return if (::mBinding.isInitialized) mBinding
            else throw NullPointerException("ViewDataBinding is Null or Not Initialized.")
        }

    /**
     * UI setup
     */
    abstract fun onSetupUI()

    /**
     * view model observe
     */
    abstract fun observeViewModel()

    override fun onAttach(context: Context) {
        printFragmentLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        printFragmentLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onCreateView(inflater, container, savedInstanceState)

        mBinding = createDataBinding(resId = resId, context = requireContext())
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        printFragmentLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onViewCreated(view, savedInstanceState)

        onSetupUI()

        observeViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        printFragmentLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        printFragmentLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onStart()
    }

    override fun onResume() {
        printFragmentLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onResume()
    }

    override fun onPause() {
        printFragmentLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        printFragmentLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        printFragmentLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onStop()
    }

    override fun onDestroyView() {
        printFragmentLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())

        super.onDestroyView()
    }

    override fun onDestroy() {
        printFragmentLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onDestroy()
    }

    override fun onDetach() {
        printFragmentLifecycle(name = object {}.javaClass.enclosingMethod?.name.toString())
        super.onDetach()
    }

    private fun printFragmentLifecycle(name: String) {
        PrintLog.d(javaClass.simpleName, name, fragmentTag)
    }
}
