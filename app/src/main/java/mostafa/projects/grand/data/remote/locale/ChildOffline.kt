package mostafa.projects.grand.data.remote.locale

import io.realm.RealmObject
import io.realm.annotations.RealmClass
import java.io.Serializable



// error: cannot inherit from final , cause to add open https://stackoverflow.com/a/48401985/7698605
@RealmClass
open class ChildOffline : RealmObject() , Serializable {
    var childId: Int? = null
    var title:String? = null
    var thumbnail_url:String? = null

}