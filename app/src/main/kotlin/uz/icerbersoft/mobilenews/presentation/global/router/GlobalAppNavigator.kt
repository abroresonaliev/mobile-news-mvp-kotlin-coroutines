package uz.icerbersoft.mobilenews.presentation.global.router

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import ru.terrakok.cicerone.commands.Command
import uz.icerbersoft.mobilenews.R
import uz.icerbersoft.mobilenews.presentation.support.cicerone.navigator.base.BaseCiceroneNavigator

class GlobalAppNavigator(
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