package uz.icerbersoft.mobilenews.app.support.cicerone.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import java.io.Serializable

inline fun <reified V> Fragment.getNullableBundleArgument(key: String): V? =
    arguments?.get(key).let {
        return@let when (it) {
            is V -> it
            is Serializable -> it as? V
            else -> null
        }
    }

@Suppress("SpellCheckingInspection")
inline fun <reified V : Any> Fragment.getNonNullBundleArgument(key: String): V =
    checkNotNull(getNullableBundleArgument<V>(key))

inline fun <T : Fragment> T.withArguments(bundle: Bundle. () -> Unit = {}): T =
    apply { arguments = Bundle().apply(bundle) }