package uz.icerbersoft.mobilenews.presentation.global

import android.os.Bundle
import dagger.Lazy
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import uz.icerbersoft.mobilenews.databinding.ActivityGlobalBinding
import uz.icerbersoft.mobilenews.presentation.application.Application
import uz.icerbersoft.mobilenews.presentation.global.di.GlobalDaggerComponent
import uz.icerbersoft.mobilenews.presentation.global.router.GlobalRouter
import javax.inject.Inject

internal class GlobalActivity : MvpAppCompatActivity(), GlobalView {

    @Inject
    lateinit var lazyPresenter: Lazy<GlobalPresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    @Inject
    lateinit var cicerone: Cicerone<GlobalRouter>
    private val navigatorHolder: NavigatorHolder by lazy { cicerone.navigatorHolder }
    private val navigator by lazy { SupportAppNavigator(this, binding.frameLayout.id) }

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
        setContentView(binding.root)
        presenter.onActivityCreate()
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