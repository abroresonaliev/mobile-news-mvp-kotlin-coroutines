package uz.icerbersoft.mobilenews.presentation.application

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import uz.icerbersoft.mobilenews.presentation.application.di.ApplicationDaggerComponent
import kotlin.properties.Delegates

internal class Application : Application(),
    IHasComponent<ApplicationDaggerComponent> {

    private var pureContext: Context by Delegates.notNull()
    lateinit var applicationDaggerComponent: ApplicationDaggerComponent
        private set

    override fun getComponent(): ApplicationDaggerComponent =
        ApplicationDaggerComponent.create(pureContext).also { applicationDaggerComponent = it }

    override fun attachBaseContext(base: Context) {
        pureContext = base
        XInjectionManager.let { it.init(this); it.bindComponent(this).inject(this) }
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}