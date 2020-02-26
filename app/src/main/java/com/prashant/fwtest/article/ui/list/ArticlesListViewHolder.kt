package com.prashant.fwtest.article.ui.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.prashant.fwtest.article.domain.ArticleListItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_artciles_list.view.*

class ArticlesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: ArticleListItem, clickListener: (Int) -> Unit) {
        itemView.tvTitle.text = item.title
        itemView.tvSummary.text = item.summary
        itemView.tvDate.text = item.date
        Picasso.get()
            .load(item.thumbnailUrl)
            .fit()
            .centerCrop()
            .into(itemView.ivArticleListItem)
        itemView.setOnClickListener { clickListener.invoke(item.id) }
    }
}