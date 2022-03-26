package uz.icerbersoft.mobilenews.app.presentation.home

import android.os.Bundle
import android.view.View
import dagger.Lazy
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import uz.icerbersoft.mobilenews.app.R
import uz.icerbersoft.mobilenews.app.databinding.FragmentHomeBinding
import uz.icerbersoft.mobilenews.app.presentation.home.di.HomeDaggerComponent
import uz.icerbersoft.mobilenews.app.presentation.home.router.HomeRouter
import javax.inject.Inject

internal class HomeFragment : MvpAppCompatFragment(R.layout.fragment_home),
    IHasComponent<HomeDaggerComponent>, HomeView {

    @Inject
    lateinit var lazyPresenter: Lazy<HomePresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private var supportAppNavigator: SupportAppNavigator? = null

    private lateinit var binding: FragmentHomeBinding

    override fun getComponent(): HomeDaggerComponent =
        HomeDaggerComponent.create(XInjectionManager.findComponent())

    override fun onCreate(savedInstanceState: Bundle?) {
        XInjectionManager.bindComponent(this).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        supportAppNavigator =
            SupportAppNavigator(requireActivity(), childFragmentManager, binding.frameLayout.id)

        binding.apply {
            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home_dashboard -> presenter.openDashboardTab().let { true }
                    R.id.home_recommended_news -> presenter.openRecommendedTab().let { true }
                    R.id.home_read_later_news -> presenter.openReadLaterTab().let { true }
                    else -> false
                }
            }
            bottomNavigationView.setOnItemReselectedListener {
                when (it.itemId) {
                    R.id.home_dashboard -> presenter.openDashboardTab()
                    R.id.home_recommended_news -> presenter.openRecommendedTab()
                    R.id.home_read_later_news -> presenter.openReadLaterTab()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.removeNavigator()
        supportAppNavigator?.let { navigatorHolder.setNavigator(it) }
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroyView() {
        supportAppNavigator = null
        super.onDestroyView()
    }

    override fun onTabChanged(tab: HomeRouter.HomeTab) {
        binding.bottomNavigationView.selectedItemId = when (tab) {
            HomeRouter.HomeTab.DashboardTab -> R.id.home_dashboard
            HomeRouter.HomeTab.RecommendedTab -> R.id.home_recommended_news
            HomeRouter.HomeTab.ReadLaterTab -> R.id.home_read_later_news
        }
    }

    companion object {

        fun newInstance() = HomeFragment()
    }
}