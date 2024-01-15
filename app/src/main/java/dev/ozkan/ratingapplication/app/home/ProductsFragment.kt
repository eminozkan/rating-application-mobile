package dev.ozkan.ratingapplication.app.home

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ozkan.ratingapplication.app.home.models.Products
import dev.ozkan.ratingapplication.app.home.recyclerviewadapter.ProductRecyclerViewAdapter
import dev.ozkan.ratingapplication.core.auth.Session
import dev.ozkan.ratingapplication.core.home.dto.product.ProductResponse
import dev.ozkan.ratingapplication.core.retrofit.MainRetrofitInstance
import dev.ozkan.ratingapplication.databinding.FragmentProductsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsFragment : Fragment() {
    private lateinit var binding: FragmentProductsBinding
    private lateinit var productArrayList: ArrayList<Products>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        getProductsFromAPI()
        return binding.root
    }



    private fun getProductsFromAPI() {
        val call = MainRetrofitInstance.api.getProducts(Session.getBearerToken())

        call.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    binding.recyclerView.layoutManager = LinearLayoutManager(activity)
                    binding.recyclerView.setHasFixedSize(true)
                    productArrayList = arrayListOf()
                    convertResponseBodyToProductItems(response)
                } else {
                    Log.d("item response", "Response is failed ${response.errorBody().toString()}")
                }


            }

            private fun convertResponseBodyToProductItems(response: Response<ProductResponse>) {
                for (product in response.body()!!) {
                    val decodedImageBytes: ByteArray =
                        Base64.decode(product.base64Image, Base64.DEFAULT)
                    val decodedImageBitmap: Bitmap =
                        BitmapFactory.decodeByteArray(decodedImageBytes, 0, decodedImageBytes.size)

                    val p = Products(
                        product.id,
                        decodedImageBitmap,
                        "${product.maker}  ${product.model}",
                        product.ratingOutOfFive
                    )
                    productArrayList.add(p)
                }
                binding.recyclerView.adapter = ProductRecyclerViewAdapter(activity!!, productArrayList)
            }


            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Toast.makeText(activity, "Failed ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}