package me.mfathy.mobiquity.ui.products

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.products_fragment.*
import me.mfathy.mobiquity.domain.model.Product
import me.mfathy.mobiquity.exception.ErrorMessageFactory
import me.mfathy.mobiquity.injection.factory.ViewModelFactory
import me.mfathy.mobiquity.state.DataState
import me.mfathy.mobiquity.ui.products.details.ProductDetailsActivity
import javax.inject.Inject


/**
 * Shows a list of products with its category.
 */
class ProductsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(me.mfathy.mobiquity.R.layout.products_fragment, container, false)
    }

    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductsViewModel::class.java)


        activity?.applicationContext?.let { context: Context ->
            categoriesAdapter = CategoriesAdapter(context, listOf()) { product, imageView ->
                onProductClick(product, imageView)
            }

            recyclerview.apply {
                this.layoutManager = LinearLayoutManager(context)
                this.adapter = categoriesAdapter
            }

            observeCategories()

            viewModel.fetchProductsList()
        }
    }

    private fun onProductClick(product: Product, imageView: ImageView?) {
        val intent = Intent(activity, ProductDetailsActivity::class.java)
        intent.putExtra(ProductDetailsActivity.EXTRA_PRODUCT_NAME, product.name)
        intent.putExtra(ProductDetailsActivity.EXTRA_PRODUCT_IMAGE_URL, product.url)
        intent.putExtra(
            ProductDetailsActivity.EXTRA_PRODUCT_PRICE,
            "${product.salePrice.amount} ${product.salePrice.currency}"
        )
        val p1 = Pair(imageView as View, product.name)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity as Activity, p1)
        startActivity(intent, options.toBundle())
    }

    private fun observeCategories() {
        viewModel.getProductsLiveData().observe(this, Observer {
            it?.let { receivedData ->
                when (receivedData) {
                    is DataState.OnLoading -> {
                        showLoading()
                        hideErrorMessage()
                        hideProducts()
                    }
                    is DataState.OnError -> {
                        hideLoading()
                        hideProducts()
                        showErrorMessage(receivedData)
                    }
                    is DataState.OnSuccess -> {
                        hideLoading()
                        hideErrorMessage()
                        showProducts(receivedData)
                    }
                }
            }
        })
    }

    private fun showProducts(receivedData: DataState.OnSuccess) {
        recyclerview.visibility = View.VISIBLE
        categoriesAdapter.setCategories(receivedData.data)
    }

    private fun hideProducts() {
        recyclerview.visibility = View.INVISIBLE
    }

    private fun showErrorMessage(receivedError: DataState.OnError) {
        textViewError.visibility = View.VISIBLE
        textViewError.text = ErrorMessageFactory.create(activity?.applicationContext, receivedError.error)
    }

    private fun hideErrorMessage() {
        textViewError.visibility = View.INVISIBLE
    }

    private fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }
}
