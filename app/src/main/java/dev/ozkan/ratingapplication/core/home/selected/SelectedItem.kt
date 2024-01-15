package dev.ozkan.ratingapplication.core.home.selected

import android.graphics.Bitmap
import kotlin.properties.Delegates

object SelectedItem {
    lateinit var productId : String
    lateinit var productBitmap : Bitmap
    lateinit var header : String
    var rating by Delegates.notNull<Double>()
}