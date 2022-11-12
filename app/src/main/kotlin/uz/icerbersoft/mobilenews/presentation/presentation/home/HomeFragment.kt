package uz.icerbersoft.mobilenews.presentation.presentation.home

import android.os.Bundle
import android.view.View
import dagger.Lazy
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import uz.icerbersoft.mobilenews.R
import uz.icerbersoft.mobilenews.databinding.FragmentHomeBinding
import uz.icerbersoft.mobilenews.presentation.global.GlobalActivity
import uz.icerbersoft.mobilenews.presentation.presentation.home.router.HomeRouter
import uz.icerbersoft.mobilenews.presentation.support.cicerone.navigator.MultiBackstackNavigator
import javax.inject.Inject

internal class HomeFragment : MvpAppCompatFragment(R.layout.fragment_home), HomeView {

    @Inject
    lateinit var lazyPresenter: Lazy<HomePresenter>
    private val presenter by moxyPresenter { lazyPresenter.get() }

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private var multiBackstackNavigator: MultiBackstackNavigator? = null

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity() as GlobalActivity)
            .globalDaggerComponent
            .homeDaggerComponent
            .create()
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        multiBackstackNavigator =
            MultiBackstackNavigator(requireActivity(), childFragmentManager, binding.frameLayout.id)

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
        multiBackstackNavigator?.let { navigatorHolder.setNavigator(it) }
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroyView() {
        multiBackstackNavigator = null
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