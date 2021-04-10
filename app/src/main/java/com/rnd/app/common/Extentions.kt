package com.rnd.app.common

import android.content.Context
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
fun Context.getDisplayWidth() = this.resources.displayMetrics.widthPixels
fun Context.getDisplayHeight() = this.resources.displayMetrics.heightPixels
