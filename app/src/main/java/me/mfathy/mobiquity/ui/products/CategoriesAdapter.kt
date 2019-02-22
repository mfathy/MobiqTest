package me.mfathy.mobiquity.ui.products

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.mfathy.mobiquity.R
import me.mfathy.mobiquity.domain.model.Category
import me.mfathy.mobiquity.domain.model.Product
import javax.inject.Inject


/**
 * RecyclerView adapter to redraw category item.
 */
class CategoriesAdapter @Inject constructor(
    private val mContext: Context,
    private var mCategories: List<Category>,
    private val clickListener: (product: Product, imageView: ImageView?) -> Unit
) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter.ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_item_category, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mCategories.count()
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.ViewHolder, position: Int) {
        val category = mCategories[position]
        holder.textViewCategoryTitle?.text = category.name

        holder.childRecyclerView?.apply {
            val productsAdapter = ProductsAdapter(mContext, category.products ?: listOf(), clickListener)
            this.layoutManager = LinearLayoutManager(mContext)
            this.adapter = productsAdapter
        }
    }

    fun setCategories(categories: List<Category>) {
        mCategories = categories
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewCategoryTitle: TextView? = view.findViewById(R.id.textViewCategoryTitle)
        var childRecyclerView: RecyclerView? = view.findViewById(R.id.childRecyclerView)
    }
}