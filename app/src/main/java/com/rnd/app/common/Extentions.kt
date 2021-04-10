package com.rnd.app.common

import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.rnd.app.common.glide.GlideApp
import com.rnd.app.main.MainActivity

/**
 * @author Vladyslav Havrylenko
 * @since 24.12.2020
 */
fun FragmentActivity.root() = this as? MainActivity

fun String?.defIfNull() = this ?: ""
fun Int?.defIfNull(def: Int = 0) = this ?: def
fun Long?.defIfNull(def: Long = 0) = this ?: def
fun Double?.defIfNull(def: Double = 0.0) = this ?: def
fun Boolean?.defIfNull() = this ?: false
fun <V : Any> List<V>?.defIfNull() = this ?: mutableListOf()
fun <K : Any, V : Any> Map<K, V>?.defIfNull() = this ?: mutableMapOf()

fun empty() = ""
fun comma() = ","
fun blank() = " "
fun newLine() = "\n"

fun ImageView.loadFromUrl(url: String, error: Int = 0) {
    GlideApp.with(this.context.applicationContext)
        .load(url)
        .error(error)
        .into(this)
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.isShow(): Boolean {
    return visibility == View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.isHide(): Boolean {
    return visibility == View.GONE
}

fun View.visibility(isVisible: Boolean) {
    if (isVisible) this.show() else this.hide()
}
