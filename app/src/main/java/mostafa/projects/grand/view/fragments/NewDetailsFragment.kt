package mostafa.projects.grand.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import mostafa.projects.grand.data.remote.locale.ChildOffline
import mostafa.projects.grand.data.remote.model.Child
import mostafa.projects.grand.databinding.FragmentNewDetailsBinding
import mostafa.projects.grand.utils.*
import mostafa.projects.grand.viewModels.NewsViewModel


class NewDetailsFragment : BaseFragment() {

    private lateinit var newsDetailsBinding: FragmentNewDetailsBinding
    val newsDataViewModel: NewsViewModel by activityViewModels()

    override fun getLayoutResource(): View {
        return newsDetailsBinding.root

    }

    override fun goBack(fragment: Int?, args: Bundle?, options: NavOptions?) {
        super.goBack(NewsId.newsHomeFragment, args, options)
    }


    override fun initViews() {
        newsDataViewModel.selectedNewChildLiveData.observe(this , Observer {
            if (it.data?.secure_media?.oembed?.thumbnail_url != null) {
                newsDetailsBinding.newsDetailsThumbnailImg.show()
                newsDetailsBinding.newsDetailsThumbnailImg.displayImg(source = it.data?.secure_media?.oembed?.thumbnail_url!!)
            } else {
                newsDetailsBinding.newsDetailsThumbnailImg.hide()
            }
            newsDetailsBinding.newsBodyTxt.text = it.data?.title?.capitalize()

        })
        newsDataViewModel.selectedOfflineChildLiveData.observe(this , Observer {
            if (it?.thumbnail_url != null) {
                newsDetailsBinding.newsDetailsThumbnailImg.show()
                newsDetailsBinding.newsDetailsThumbnailImg.displayImg(source = it?.thumbnail_url!!)
            } else {
                newsDetailsBinding.newsDetailsThumbnailImg.hide()
            }
            newsDetailsBinding.newsBodyTxt.text = it?.title?.capitalize()

        })
    }

    override fun init() {
        newsDetailsBinding = FragmentNewDetailsBinding.inflate(layoutInflater)
    }

    override fun onLeave() {
        newsDataViewModel.selectedNewChildLiveData.removeObservers(this)
        newsDataViewModel.selectedOfflineChildLiveData.removeObservers(this)
    }

}