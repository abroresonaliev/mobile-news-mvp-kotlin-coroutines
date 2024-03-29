package uz.icebergsoft.mobilenews.presentation.global

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import dagger.Lazy
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import uz.icebergsoft.mobilenews.databinding.ActivityGlobalBinding
import uz.icebergsoft.mobilenews.presentation.application.Application
import uz.icebergsoft.mobilenews.presentation.application.manager.daynight.DayNightModeManager
import uz.icebergsoft.mobilenews.presentation.global.di.GlobalDaggerComponent
import uz.icebergsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebergsoft.mobilenews.presentation.support.cicerone.navigator.BaseCiceroneNavigator
import javax.inject.Inject

internal class GlobalActivity : MvpAppCompatActivity(), GlobalView {

    @Inject
    lateinit var lazyPresenter: Lazy<GlobalPresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    @Inject
    lateinit var cicerone: Cicerone<GlobalRouter>
    private val navigatorHolder: NavigatorHolder by lazy { cicerone.navigatorHolder }
    private val navigator by lazy { BaseCiceroneNavigator(this, binding.frameLayout.id) }

    @Inject
    lateinit var dayNightModeManager: DayNightModeManager

    lateinit var globalDaggerComponent: GlobalDaggerComponent
        private set

    private val binding by lazy { ActivityGlobalBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as Application).applicationDaggerComponent
            .globalDaggerComponent
            .create()
            .also { globalDaggerComponent = it }
            .inject(this)

        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(dayNightModeManager.getDayNightMode())
        setContentView(binding.root)
    }

    fun updateNightMode (dayNightMode: Int){
        dayNightModeManager.setDayNightMode(dayNightMode)
        AppCompatDelegate.setDefaultNightMode(dayNightModeManager.getDayNightMode())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}