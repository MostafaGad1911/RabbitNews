package mostafa.projects.grand.view.activities

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import mostafa.projects.grand.databinding.ActivityMainBinding
import mostafa.projects.grand.utils.NewsId
import mostafa.projects.grand.utils.hide
import mostafa.projects.grand.utils.show

class MainActivity : BaseActivity(), View.OnClickListener {

    lateinit var mainBinding: ActivityMainBinding // init view binding object
    lateinit var controller: NavController // For navigation component

    override fun getLayoutResource(): View {
        return mainBinding.root
    }

    override fun initObjects() {
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
    }

    override fun goBack() {
        mainBack()
    }

    override fun initViews() {
        mainBinding.newsBackImg.setOnClickListener(this)
        setUpNavComponent()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            NewsId.newsBackImg -> {
                mainBack()
            }

        }
    }

    private fun setUpNavComponent() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(NewsId.newsNavigation) as NavHostFragment
        controller = navHostFragment.navController
        controller.addOnDestinationChangedListener { controller, destination, arguments ->
            when (controller.currentDestination?.id) {
                NewsId.newsDetailsFragment -> {
                    mainBinding.newsBackImg.show()
                }
                else -> {
                    mainBinding.newsBackImg.hide()
                }
            }
        }

    }

    private fun mainBack() {
        var currentFragment =
            controller.currentDestination?.id
        if (currentFragment == NewsId.newsDetailsFragment) {
            controller.navigate(NewsId.newsHomeFragment, null, options)
        } else {
            doubleClickExitApp()
        }

    }


}