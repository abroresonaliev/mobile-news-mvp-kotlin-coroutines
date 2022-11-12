package uz.icebergsoft.mobilenews.presentation.support.cicerone.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Forward

class MultiBackstackNavigator(
    activity: FragmentActivity,
    fragmentManager: FragmentManager,
    containerId: Int
) : BaseCiceroneNavigator(activity, fragmentManager, containerId) {

    constructor(activity: FragmentActivity, containerId: Int) : this(
        activity,
        activity.supportFragmentManager,
        containerId
    )

    override fun fragmentForward(command: Forward) {
        val screen = command.screen as SupportAppScreen
        val fragment: Fragment = createFragment(screen) ?: throw NullPointerException()

        if (isDialogFragment(fragment)) return

        if (localStackCopy.contains(screen.screenKey)) {
            applyCommand(BackTo(command.screen))
        } else super.fragmentForward(command)
    }
}