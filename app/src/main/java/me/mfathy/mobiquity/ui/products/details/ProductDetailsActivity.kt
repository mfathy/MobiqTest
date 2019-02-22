package me.mfathy.mobiquity.ui.products.details

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.content_product_details.*
import me.mfathy.mobiquity.BuildConfig
import me.mfathy.mobiquity.R
import me.mfathy.mobiquity.utils.GlideApp


/**
 * Show a detailed view of a product.
 */
class ProductDetailsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PRODUCT_NAME = "PRODUCT_NAME"
        const val EXTRA_PRODUCT_IMAGE_URL = "PRODUCT_IMAGE_URL"
        const val EXTRA_PRODUCT_PRICE = "PRODUCT_PRICE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        setSupportActionBar(toolbar)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra(EXTRA_PRODUCT_NAME)
        val url = intent.getStringExtra(EXTRA_PRODUCT_IMAGE_URL)
        val price = intent.getStringExtra(EXTRA_PRODUCT_PRICE)

        textViewDetailsName.text = name
        textViewDetailsPrice.text = price

        ViewCompat.setTransitionName(imgViewProductDetails, name)

        GlideApp
            .with(this)
            .asBitmap()
            .format(DecodeFormat.PREFER_ARGB_8888)
            .load("${BuildConfig.BASE_URL}$url")
            .placeholder(ContextCompat.getDrawable(this, R.drawable.ic_image_place_holder))
            .error(ContextCompat.getDrawable(this, R.drawable.ic_broken_image))
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(imgViewProductDetails)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
