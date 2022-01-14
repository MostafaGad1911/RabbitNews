package mostafa.projects.grand.data.apis

import io.reactivex.rxjava3.core.Observable
import mostafa.projects.grand.data.model.NewsModel
import retrofit2.Response
import retrofit2.http.*


interface NewsApis {


    @GET(".json")
    fun getNews(
    ): Observable<Response<NewsModel>>



}