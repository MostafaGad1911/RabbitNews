package mostafa.projects.grand.view.fragments

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import mostafa.projects.grand.utils.NewsAnim


abstract class BaseFragment : Fragment() {

    val option = navOptions {
        anim {
            enter = NewsAnim.fade_in
            exit = NewsAnim.fade_out
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return getLayoutResource()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        this.requireView().isFocusableInTouchMode = true
        this.requireView().requestFocus()
        this.requireView().setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                return if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
                    goBack()
                    true
                } else
                    false
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        onLeave()
    }

    abstract fun getLayoutResource(): View // Inflate layout resource
    abstract fun initViews() // init ui views
    abstract fun init() // init objects for custom uses
    abstract fun onLeave()
    open fun goBack(fragment: Int? = null, args: Bundle? = null, options: NavOptions? = option) {
        if (fragment != null) {
            findNavController().navigate(fragment, args, options)
        } else {
            requireActivity().onBackPressed()
        }
    }
}