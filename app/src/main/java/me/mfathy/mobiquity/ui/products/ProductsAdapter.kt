package me.mfathy.mobiquity.ui.products

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import me.mfathy.mobiquity.BuildConfig
import me.mfathy.mobiquity.R
import me.mfathy.mobiquity.domain.model.Product
import me.mfathy.mobiquity.utils.GlideApp
import javax.inject.Inject

/**
 * RecyclerView adapter to redraw product item.
 */
class ProductsAdapter @Inject constructor(
    private val mContext: Context,
    private val mProducts: List<Product>,
    val clickListener: (product: Product, imageView: ImageView?) -> Unit
) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_item_product, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mProducts.count()
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        val product = mProducts[position]
        holder.textViewProductTitle?.text = product.name

        holder.imageViewProductThumb?.let { imageView ->
            GlideApp
                .with(mContext)
                .asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .load("${BuildConfig.BASE_URL}${product.url}")
                .placeholder(ContextCompat.getDrawable(mContext, R.drawable.ic_image_place_holder))
                .error(ContextCompat.getDrawable(mContext, R.drawable.ic_broken_image))
                .circleCrop()
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .override(84, 84)
                .into(imageView)

            ViewCompat.setTransitionName(imageView, product.name)
        }

        holder.itemView.setOnClickListener { clickListener(product, holder.imageViewProductThumb) }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewProductTitle: TextView? = view.findViewById(R.id.textViewProductTitle)
        var imageViewProductThumb: ImageView? = view.findViewById(R.id.imgViewProductThumb)
    }
}