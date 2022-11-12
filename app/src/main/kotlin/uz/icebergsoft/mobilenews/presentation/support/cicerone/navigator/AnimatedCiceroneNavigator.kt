package uz.icebergsoft.mobilenews.presentation.support.cicerone.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import ru.terrakok.cicerone.commands.Command
import uz.icebergsoft.mobilenews.R

class AnimatedCiceroneNavigator(
    activity: FragmentActivity,
    containerId: Int
) : BaseCiceroneNavigator(activity, containerId) {

    override fun setupFragmentTransaction(
        command: Command,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction
    ) {

        fragmentTransaction.setCustomAnimations(
            R.anim.fade_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.fade_out
        )
    }
}