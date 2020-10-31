package com.example.voaenglish

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.voaenglish.base.BaseActivity
import com.example.voaenglish.databinding.ActivityFacebookAccountkitBinding
import com.facebook.accountkit.ui.AccountKitActivity
import com.facebook.accountkit.ui.AccountKitConfiguration
import com.facebook.accountkit.ui.LoginType

class FacebookLoginActivity : BaseActivity() {

    private lateinit var activityFacebookAccountkitBinding: ActivityFacebookAccountkitBinding

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_facebook_accountkit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFacebookAccountkitBinding = DataBindingUtil.setContentView(this@FacebookLoginActivity, R.layout.activity_facebook_accountkit)
        phoneLogin()
        activityFacebookAccountkitBinding?.executePendingBindings()
    }

    fun phoneLogin() {
        val intent = Intent(this@FacebookLoginActivity, FacebookLoginActivity::class.java)
        val configurationBuilder = AccountKitConfiguration.AccountKitConfigurationBuilder(
                LoginType.PHONE,
                AccountKitActivity.ResponseType.CODE
        )
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build())
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }


}