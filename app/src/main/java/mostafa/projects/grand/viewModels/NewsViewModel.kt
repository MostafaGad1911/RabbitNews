package mostafa.projects.grand.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import mostafa.projects.grand.data.remote.apis.NewsRepository
import mostafa.projects.grand.data.remote.locale.ChildOffline
import mostafa.projects.grand.data.remote.model.Child
import mostafa.projects.grand.data.remote.model.NewsModel
import mostafa.projects.grand.utils.getMessage
import mostafa.projects.grand.utils.SingleLiveEvent
import mostafa.projects.grand.utils.getErrorMessage
import retrofit2.Response


class NewsViewModel: ViewModel() {

    var grandRepository: NewsRepository = NewsRepository()

    var error_msg: SingleLiveEvent<String> = SingleLiveEvent()
    var newData: SingleLiveEvent<ArrayList<Child>> = SingleLiveEvent()
    var selectedChildData:SingleLiveEvent<Child> = SingleLiveEvent()
    var selectedOfflineChildData:SingleLiveEvent<ChildOffline> = SingleLiveEvent()

    val errorLiveData: LiveData<String>
        get() = error_msg
    val newsLiveData: LiveData<ArrayList<Child>>
        get() = newData
    val selectedNewChildLiveData: LiveData<Child>
        get() = selectedChildData
    val selectedOfflineChildLiveData: LiveData<ChildOffline>
        get() = selectedOfflineChildData

    init {
    }
    fun getNews(
    ) {
        grandRepository.getNews()
            .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Response<NewsModel>>() {
                override fun onNext(it: Response<NewsModel>) {
                    when (it.code()) {
                        200 -> {
                            newData.postValue(it.body()?.data?.children)
                        }

                        422, 502, 401, 400 -> {
                            val msg = it.errorBody()?.getErrorMessage()
                            error_msg.postValue(msg)

                        }
                    }
                }

                override fun onError(e: Throwable) {
                    val msg = e.getMessage()
                    error_msg.postValue(msg)
                }

                override fun onComplete() {
                }

            })


    }
}