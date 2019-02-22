package me.mfathy.mobiquity.ui.products

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import me.mfathy.mobiquity.R

/**
 * Show Product list Fragment.
 */
class ProductsActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.products_activity)

        supportFragmentManager.beginTransaction().add(
            R.id.fragment_container,
            ProductsFragment()
        ).commit()
    }
}
