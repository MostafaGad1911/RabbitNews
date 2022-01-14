package mostafa.projects.grand.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mostafa.projects.grand.data.model.Child
import mostafa.projects.grand.interfaces.NewController
import mostafa.projects.grand.utils.*

class NewsChildrenAdapter(
    var ctx: Context,
    var childslist: ArrayList<Child>,
    var newsController: NewController
) :
    RecyclerView.Adapter<NewsChildrenAdapter.NewsChildHolder>() {

    var _newController:NewController = newsController

    class NewsChildHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newTitleTxt: TextView = itemView.findViewById(NewsId.newTitleTxt)
        var newsThumbnailImg: ImageView = itemView.findViewById(NewsId.newsThumbnailImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsChildHolder {
        return NewsChildHolder(
            LayoutInflater.from(ctx).inflate(
                NewsLayout.new_item,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        return childslist.size
    }

    override fun onBindViewHolder(holder: NewsChildHolder, position: Int) {
        var newsChild = childslist.get(position)
        if (newsChild.data?.secure_media?.oembed?.thumbnail_url != null) {
            holder.newsThumbnailImg.show()
            holder.newsThumbnailImg.displayImg(source = newsChild.data?.secure_media?.oembed?.thumbnail_url!!)
        } else {
            holder.newsThumbnailImg.hide()
        }
        holder.newTitleTxt.text = newsChild.data?.title?.capitalize()
        holder.itemView.setOnClickListener { _newController.displayFullArticle(child = newsChild) }

    }


}