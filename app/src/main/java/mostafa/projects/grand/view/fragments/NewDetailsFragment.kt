package mostafa.projects.grand.view.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import mostafa.projects.grand.data.remote.locale.ChildOffline
import mostafa.projects.grand.data.remote.model.Child
import mostafa.projects.grand.databinding.FragmentNewDetailsBinding
import mostafa.projects.grand.utils.*


class NewDetailsFragment : BaseFragment() {

    private lateinit var newsDetailsBinding: FragmentNewDetailsBinding
    private val childDetails: Child by lazy { arguments?.getSerializable("article") as Child }
    private val childOfflineDetails: ChildOffline by lazy { arguments?.getSerializable("article_offline") as ChildOffline }

    override fun getLayoutResource(): View {
        return newsDetailsBinding.root

    }

    override fun goBack(fragment: Int?, args: Bundle?, options: NavOptions?) {
        super.goBack(NewsId.newsHomeFragment, args, options)
    }

    override fun initViews() {
        if (arguments?.containsKey("article") == true) {
            if (childDetails.data?.secure_media?.oembed?.thumbnail_url != null) {
                newsDetailsBinding.newsDetailsThumbnailImg.show()
                newsDetailsBinding.newsDetailsThumbnailImg.displayImg(source = childDetails.data?.secure_media?.oembed?.thumbnail_url!!)
            } else {
                newsDetailsBinding.newsDetailsThumbnailImg.hide()
            }
            newsDetailsBinding.newsBodyTxt.text = childDetails.data?.title?.capitalize()

        }else {
            if (childOfflineDetails?.thumbnail_url != null) {
                newsDetailsBinding.newsDetailsThumbnailImg.show()
                newsDetailsBinding.newsDetailsThumbnailImg.displayImg(source = childOfflineDetails?.thumbnail_url!!)
            } else {
                newsDetailsBinding.newsDetailsThumbnailImg.hide()
            }
            newsDetailsBinding.newsBodyTxt.text = childOfflineDetails?.title?.capitalize()

        }
    }

    override fun init() {
        newsDetailsBinding = FragmentNewDetailsBinding.inflate(layoutInflater)
    }

    override fun onLeave() {

    }

}