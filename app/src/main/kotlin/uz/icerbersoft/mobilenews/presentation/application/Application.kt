package uz.icerbersoft.mobilenews.presentation.application

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco
import uz.icerbersoft.mobilenews.presentation.application.di.ApplicationDaggerComponent
import kotlin.properties.Delegates

internal class Application : Application() {

    private var pureContext: Context by Delegates.notNull()
    lateinit var applicationDaggerComponent: ApplicationDaggerComponent
        private set

    override fun attachBaseContext(base: Context) {
        pureContext = base

        ApplicationDaggerComponent
            .create(pureContext)
            .also { applicationDaggerComponent = it }
            .inject(this)

        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}