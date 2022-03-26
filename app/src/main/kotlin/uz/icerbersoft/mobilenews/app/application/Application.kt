package uz.icerbersoft.mobilenews.app.application

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import uz.icerbersoft.mobilenews.app.application.di.ApplicationDaggerComponent
import kotlin.properties.Delegates

class Application : Application(),
    IHasComponent<ApplicationDaggerComponent> {

    private var pureContext: Context by Delegates.notNull()

    override fun getComponent(): ApplicationDaggerComponent =
        ApplicationDaggerComponent.create(pureContext)

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