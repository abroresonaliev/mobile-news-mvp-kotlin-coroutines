package uz.icerbersoft.mobilenews.presentation.support.cicerone.navigator.base

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

open class BaseCiceroneNavigator(
    activity: FragmentActivity,
    fragmentManager: FragmentManager,
    containerId: Int
) : SupportAppNavigator(activity, fragmentManager, containerId) {

    constructor(activity: FragmentActivity, containerId: Int) : this(
        activity,
        activity.supportFragmentManager,
        containerId
    )

    override fun fragmentForward(command: Forward) {
        val screen = command.screen as SupportAppScreen
        val fragment: Fragment = createFragment(screen) ?: throw NullPointerException()
        if (isDialogFragment(fragment)) return

        val fragmentTransaction = fragmentManager.beginTransaction()

        setupFragmentTransaction(
            command,
            fragmentManager.findFragmentById(containerId),
            fragment,
            fragmentTransaction
        )

        fragmentTransaction
            .replace(containerId, fragment)
            .addToBackStack(screen.screenKey)
            .commit()

        localStackCopy?.add(screen.screenKey)
    }

    override fun fragmentReplace(command: Replace) {
        val screen = command.screen as SupportAppScreen
        val fragment: Fragment = createFragment(screen) ?: throw NullPointerException()
        if (isDialogFragment(fragment)) return

        if (localStackCopy?.size ?: 0 > 0) {
            fragmentManager.popBackStack()
            localStackCopy?.removeLast()

            val fragmentTransaction = fragmentManager.beginTransaction()

            setupFragmentTransaction(
                command,
                fragmentManager.findFragmentById(containerId),
                fragment,
                fragmentTransaction
            )

            fragmentTransaction
                .replace(containerId, fragment)
                .addToBackStack(screen.screenKey)
                .commit()

            localStackCopy?.add(screen.screenKey)
        } else {
            val fragmentTransaction = fragmentManager.beginTransaction()

            setupFragmentTransaction(
                command,
                fragmentManager.findFragmentById(containerId),
                fragment,
                fragmentTransaction
            )

            fragmentTransaction
                .replace(containerId, fragment)
                .commit()
        }
    }

    open fun isDialogFragment(fragment: Fragment): Boolean {
        val tag = fragment.javaClass.name
        if (fragmentManager.findFragmentByTag(tag) != null) return true

        return if (fragment is DialogFragment) {
            fragment.show(fragmentManager, tag)
            true
        } else false
    }
}