package com.example.voaenglish.base

import android.widget.Toast
import com.example.voaenglish.model.CloudResponseBody
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public abstract class CallbackResApiLogout(val baseActivity: BaseActivity) : Callback<CloudResponseBody> {

    override fun onResponse(call: Call<CloudResponseBody>, response: Response<CloudResponseBody>) {
        if (response.code() == 403 || response.code() == 401) {
            baseActivity?.runOnUiThread {
                Toast.makeText(baseActivity, "Token expired", Toast.LENGTH_LONG).show()
                onFailure(response.code())
            }
        } else {
            if (response.body()!!.isResult && response.body() != null) {
                onReceivedResponse(response.body()!!.data)
            } else {
                baseActivity.runOnUiThread {
                    Toast.makeText(baseActivity, response.body()!!.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onFailure(call: Call<CloudResponseBody>, t: Throwable) {
        baseActivity.runOnUiThread {
            Toast.makeText(baseActivity, "not internet", Toast.LENGTH_LONG).show()
        }
    }

    protected abstract fun onReceivedResponse(jsonElement: JsonElement)
    public abstract fun onFailure(code: Int)
}