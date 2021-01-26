package com.bintang_18104030.rentalinaja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bintang_18104030.rentalinaja.fragments.onboardingFragments


class onboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)


        val mFragmentManager = supportFragmentManager
        val mFirstFragment = onboardingFragments()
        val fragment = mFragmentManager.findFragmentByTag(onboardingFragments::class.java.simpleName)
        if (fragment !is onboardingFragments) {
            mFragmentManager
                .beginTransaction()
                .add(R.id.frame_container, mFirstFragment, onboardingFragments::class.java.simpleName)
                .commit()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@onboardingActivity, loginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}