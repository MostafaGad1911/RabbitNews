package mostafa.projects.grand.data.remote.apis

import mostafa.projects.grand.data.remote.model.NewsModel
import retrofit2.Response
import io.reactivex.rxjava3.core.Observable

class NewsRepository {

    var apiInterface: NewsApis = RabbitNewsSettings.getRabbitService()


    fun getNews(): Observable<Response<NewsModel>> =
        apiInterface.getNews()


}