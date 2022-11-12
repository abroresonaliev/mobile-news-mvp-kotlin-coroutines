package uz.icebergsoft.mobilenews.presentation.application.manager.resource

import android.content.Context
import androidx.annotation.StringRes

class ResourceManager(
    private val context: Context
) {

    fun getText(@StringRes resId: Int): CharSequence {
        return context.getText(resId)
    }

    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    fun getString(@StringRes resId: Int, vararg formatArgs: Any?): String {
        return context.getString(resId, *formatArgs)
    }
}