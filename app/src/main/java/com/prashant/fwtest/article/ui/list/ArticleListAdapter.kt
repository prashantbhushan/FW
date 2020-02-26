package com.prashant.fwtest.article.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prashant.fwtest.R
import com.prashant.fwtest.article.domain.ArticleListItem

class ArticleListAdapter(var articles: List<ArticleListItem>, val clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<ArticlesListViewHolder>() {

    override fun onCreateViewHolder(vg: ViewGroup, viewType: Int): ArticlesListViewHolder {
        return ArticlesListViewHolder(
            LayoutInflater.from(vg.context).inflate(
                R.layout.item_artciles_list,
                vg,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(viewHolder: ArticlesListViewHolder, position: Int) {
        viewHolder.bind(articles.get(position), clickListener)
    }
}