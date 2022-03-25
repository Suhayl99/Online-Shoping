package uz.itech.onlineshoping.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.LogInterceptor;

import java.util.Locale;

public class LocaleManager {
    public static Context setLocale(Context mContext){
        Log.d("prefs",getLanguagePref(mContext));
    return updateResources(mContext, getLanguagePref(mContext));
    }

    public static Context setNewLocale(Context mContext, String language){
        setLanguagePref(mContext, language);
        return updateResources(mContext,language);
    }

    private static String getLanguagePref(Context mContext) {
    return Hawk.get("pref_lang","en");
    }

    private static void setLanguagePref(Context mContext, String localeKey) {
        Hawk.put("pref_lang",localeKey);
    }

    private static Context updateResources(Context mContext, String languagePref) {
        Locale locale= new Locale(languagePref);
        Locale.setDefault(locale);

        Resources res = mContext.getResources();

        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT>=17){
            config.setLocale(locale);
            mContext=mContext.createConfigurationContext(config);
            res.updateConfiguration(config,res.getDisplayMetrics());
        }else {
            config.locale=locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return mContext;
    }

    public static Locale getLocale(Resources resources){
        Configuration config=resources.getConfiguration();
        return Build.VERSION.SDK_INT>=24 ? config.getLocales().get(0) :config.locale;
    }
}
