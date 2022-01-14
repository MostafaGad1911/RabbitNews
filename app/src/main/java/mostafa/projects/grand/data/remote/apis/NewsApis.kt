package mostafa.projects.grand.data.remote.apis

import dagger.Module
import io.reactivex.rxjava3.core.Observable
import mostafa.projects.grand.data.remote.model.NewsModel
import retrofit2.Response
import retrofit2.http.*

@Module
interface NewsApis {


    @GET(".json")
    fun getNews(
    ): Observable<Response<NewsModel>>



}