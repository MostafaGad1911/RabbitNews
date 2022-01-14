package mostafa.projects.grand.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import mostafa.projects.grand.data.model.Child
import mostafa.projects.grand.databinding.FragmentNewsBinding
import mostafa.projects.grand.interfaces.NewController
import mostafa.projects.grand.utils.*
import mostafa.projects.grand.view.activities.MainActivity
import mostafa.projects.grand.view.adapter.NewsChildrenAdapter
import mostafa.projects.grand.viewModels.NewsViewModel


class NewsFragment : BaseFragment() , NewController {

    lateinit var newsBinding: FragmentNewsBinding
    val newsDataViewModel: NewsViewModel by viewModels()

    override fun getLayoutResource(): View {
        return newsBinding.root

    }

    override fun initViews() {

    }

    override fun init() {
        newsBinding = FragmentNewsBinding.inflate(layoutInflater)
        newsBinding.newsRecycler.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
        )
        newsBinding.newsRecycler.setHasFixedSize(true)
        callNewsData()
    }

    private fun showLoading() {
        newsBinding.newsShimmerLyt.show()
        newsBinding.newsShimmerLyt.startLayoutAnimation()
    }

    private fun hideLoading() {
        newsBinding.newsShimmerLyt.hide()
        newsBinding.newsShimmerLyt.stopShimmer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        newsDataViewModel.newsLiveData.removeObservers(this)
        newsDataViewModel.errorLiveData.removeObservers(this)
    }

    private fun callNewsData() {
        showLoading()
        newsDataViewModel.getNews()
        newsDataViewModel.newsLiveData.observe(this, Observer {
            var newsAdapter = NewsChildrenAdapter(ctx = requireContext(), childslist = it , newsController = this)
            newsBinding.newsRecycler.adapter = newsAdapter
            hideLoading()
        })
        newsDataViewModel.errorLiveData.observe(this, Observer {
            hideLoading()
            requireActivity().showToast(it)
        })
    }

    override fun displayFullArticle(child: Child) {
        var bundle = Bundle()
        bundle.putSerializable("article" , child)
        findNavController().navigate(NewsId.newsDetailsFragment , bundle , option)
    }


}