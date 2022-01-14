package mostafa.projects.grand.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmConfiguration
import mostafa.projects.grand.data.remote.locale.ChildOffline
import mostafa.projects.grand.data.remote.model.Child
import mostafa.projects.grand.databinding.FragmentNewsBinding
import mostafa.projects.grand.interfaces.NewController
import mostafa.projects.grand.utils.*
import mostafa.projects.grand.view.adapter.NewsChildrenAdapter
import mostafa.projects.grand.viewModels.NewsViewModel
import mostafa.projects.grand.view.activities.MainActivity

import io.realm.exceptions.RealmMigrationNeededException
import mostafa.projects.grand.interfaces.NewOfflineController
import mostafa.projects.grand.view.adapter.NewsChildrenOfflineAdapter


class NewsFragment : BaseFragment(), NewController, NewOfflineController {

    lateinit var newsBinding: FragmentNewsBinding
    val newsDataViewModel: NewsViewModel by viewModels()
    lateinit var realm: Realm

    override fun getLayoutResource(): View {
        return newsBinding.root

    }

    override fun initViews() {

    }

    override fun init() {
        // Init Realm
        Realm.init(requireContext())
        val config = RealmConfiguration.Builder().name("news.realm").build()
        realm = try {
            Realm.getInstance(config)
        } catch (r: RealmMigrationNeededException) {
            Realm.deleteRealm(config)
            Realm.getInstance(config)
        }

        // View binding configs
        newsBinding = FragmentNewsBinding.inflate(layoutInflater)
        newsBinding.newsRecycler.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
        )
        newsBinding.newsRecycler.setHasFixedSize(true)
        callNewsData()
    }

    override fun onLeave() {
        realm.close()
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
            Log.i("OnlineCount", "${it.size}")
            clearCash()
            saveCash(it)
            var newsAdapter =
                NewsChildrenAdapter(ctx = requireContext(), childslist = it, newsController = this)
            newsBinding.newsRecycler.adapter = newsAdapter
            hideLoading()
        })
        newsDataViewModel.errorLiveData.observe(this, Observer {
            loadCash()
            requireActivity().showToast(it)
        })
    }

    private fun loadCash() {
        realm.beginTransaction()
        val data = realm.where(ChildOffline::class.java).findAll()
        var newsAdapter =
            NewsChildrenOfflineAdapter(
                ctx = requireContext(),
                childslist = data,
                newsController = this
            )
        newsBinding.newsRecycler.adapter = newsAdapter
        hideLoading()
    }

    override fun displayFullArticle(child: Child) {
        var bundle = Bundle()
        bundle.putSerializable("article", child)
        findNavController().navigate(NewsId.newsDetailsFragment, bundle, option)
    }

    private fun clearCash() {
        realm.executeRealmTransaction{
            val count: Long = realm.where(ChildOffline::class.java).count()
            Log.i("OfflineCount", "$count")
        }
        realm.executeRealmTransaction{
            realm.delete(ChildOffline::class.java)
        }
        realm.executeRealmTransaction{
            val countUpdated: Long = realm.where(ChildOffline::class.java).count()
            Log.i("OfflineCountAfterDelete", "$countUpdated")
        }
    }

    private fun saveCash(childsList: ArrayList<Child>) {
        realm.executeRealmTransaction{
            for (i in 0 until childsList.size) {
                var post = realm.createObject(ChildOffline::class.java)
                post.title = childsList[i].data?.title
                post.thumbnail_url = childsList[i].data?.secure_media?.oembed?.thumbnail_url
            }
        }
    }

    override fun displayFullArticle(child: ChildOffline) {
        var bundle = Bundle()
        bundle.putSerializable("article_offline", child)
        findNavController().navigate(NewsId.newsDetailsFragment, bundle, option)
    }


    inline fun Realm.executeRealmTransaction(query: () -> Unit) {
        this.beginTransaction()
        query()
        this.commitTransaction()
    }

}