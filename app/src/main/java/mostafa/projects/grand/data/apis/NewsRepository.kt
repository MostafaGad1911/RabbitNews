package mostafa.projects.grand.data.apis

import mostafa.projects.grand.data.model.NewsModel
import retrofit2.Response
import io.reactivex.rxjava3.core.Observable

class NewsRepository {

    var apiInterface: NewsApis = RabbitNewsSettings.getRabbitService()


    fun getNews(): Observable<Response<NewsModel>> =
        apiInterface.getNews()


}