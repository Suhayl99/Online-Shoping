package uz.itech.onlineshoping.screen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.orhanobut.hawk.Hawk
import uz.itech.onlineshoping.R
import uz.itech.onlineshoping.databinding.ActivityMainBinding
import uz.itech.onlineshoping.screen.cart.CartFragment
import uz.itech.onlineshoping.screen.changelanguage.ChangeLanguageFragment
import uz.itech.onlineshoping.screen.favorite.FavoriteFragment
import uz.itech.onlineshoping.screen.home.HomeFragment
import uz.itech.onlineshoping.screen.profil.ProfilFragment
import uz.itech.onlineshoping.utils.LocaleManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val homeFragment=HomeFragment()
        val favoriteFragment=FavoriteFragment()
        val cartFragment=CartFragment()
        val profilFragment=ProfilFragment()
        setCurrentFragment(homeFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.actionHome ->setCurrentFragment(homeFragment)
                R.id.actionFavorite ->setCurrentFragment(favoriteFragment)
                R.id.actionCart ->setCurrentFragment(cartFragment)
                R.id.actionUser ->setCurrentFragment(profilFragment)
            }
            true
        }

        binding.btnMenyu.setOnClickListener {
            val fragment= ChangeLanguageFragment.newInstance()
            fragment.show(supportFragmentManager, fragment.tag)
        }

    }

    private fun setCurrentFragment(fragment:Fragment )=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flContener,fragment)
            commit()
    }

    override fun attachBaseContext(newBase: Context?){
        super.attachBaseContext(LocaleManager.setLocale(newBase))
    }
}