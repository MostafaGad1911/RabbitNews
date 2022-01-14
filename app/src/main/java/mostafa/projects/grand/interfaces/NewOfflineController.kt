package mostafa.projects.grand.interfaces

import mostafa.projects.grand.data.remote.locale.ChildOffline
import mostafa.projects.grand.data.remote.model.Child

interface NewOfflineController {
    fun displayFullArticle(child: ChildOffline)
}