package mostafa.projects.grand.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import mostafa.projects.grand.R
import mostafa.projects.grand.data.model.Child
import mostafa.projects.grand.databinding.FragmentNewDetailsBinding
import mostafa.projects.grand.databinding.FragmentNewsBinding
import mostafa.projects.grand.utils.*
import mostafa.projects.grand.viewModels.NewsViewModel


class NewDetailsFragment : BaseFragment(){

    private lateinit var newsDetailsBinding: FragmentNewDetailsBinding
    private val childDetails:Child by lazy { arguments?.getSerializable("child") as Child }

    override fun getLayoutResource(): View {
        return newsDetailsBinding.root

    }

    override fun goBack(fragment: Int?, args: Bundle?, options: NavOptions?) {
        super.goBack(NewsId.newsHomeFragment, args, options)
    }

    override fun initViews() {
        if (childDetails.data?.secure_media?.oembed?.thumbnail_url != null) {
            newsDetailsBinding.newsDetailsThumbnailImg.show()
            newsDetailsBinding.newsDetailsThumbnailImg.displayImg(source = childDetails.data?.secure_media?.oembed?.thumbnail_url!!)
        } else {
            newsDetailsBinding.newsDetailsThumbnailImg.hide()
        }
        newsDetailsBinding.newsBodyTxt.text = childDetails.data?.title?.capitalize()
    }

    override fun init() {
        newsDetailsBinding = FragmentNewDetailsBinding.inflate(layoutInflater)
    }

}