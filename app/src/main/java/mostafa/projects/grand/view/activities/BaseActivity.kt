package mostafa.projects.grand.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import androidx.navigation.navOptions
import mostafa.projects.grand.utils.NewsAnim
import mostafa.projects.grand.utils.NewsString


abstract class BaseActivity : AppCompatActivity() {

    var doubleBackToExitPressedOnce = false
    val options = navOptions {
        anim {
            enter =NewsAnim.fade_in
            exit = NewsAnim.fade_out
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObjects()
        setContentView(getLayoutResource())
        initViews()




    }


    abstract fun getLayoutResource(): View
    abstract fun initObjects()
    abstract fun goBack()
    abstract fun initViews()

    override fun onBackPressed() {
        goBack()
    }




}