package uz.itech.onlineshoping

import android.app.Application
import com.orhanobut.hawk.Hawk
import uz.itech.onlineshop.db.AppDatabase

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
        AppDatabase.initDatabase(this)
    }
}