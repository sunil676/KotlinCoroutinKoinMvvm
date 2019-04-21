package com.sunil.kotlincoroutneexample.extentions

import android.support.design.widget.Snackbar
import android.view.View

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.visible(visible: Boolean) {
    if (visible) {
        visible()
    } else {
        gone()
    }
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

fun View.isGone(): Boolean {
    return visibility == View.GONE
}

fun View.isInvisible(): Boolean {
    return visibility == View.INVISIBLE
}

fun View.snackbar(resId: Int, duration: Int = Snackbar.LENGTH_SHORT) {
    snackbar(this.resources.getString(resId), duration)
}

fun View.snackbar(msg: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, msg, duration).show()
}

fun View.longSnackbar(resId: Int) {
    snackbar(resId, Snackbar.LENGTH_LONG)
}
