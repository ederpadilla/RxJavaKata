package com.example.rxjavasamples.retrofit.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.rxjavasamples.R
import kotlinx.android.synthetic.main.activity_retrofit_sample.*
import com.jakewharton.rxbinding2.view.RxView
import org.reactivestreams.Subscription
import io.reactivex.functions.Action
import android.R.attr.button
import android.widget.Button
import com.example.rxjavasamples.DebugUtils
import com.example.rxjavasamples.retrofit.api.RetrofitInstance
import com.example.rxjavasamples.retrofit.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitSampleActivity : AppCompatActivity() {

    val tag : String = RetrofitSampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.rxjavasamples.R.layout.activity_retrofit_sample)
        mBuSubmit.setOnClickListener {
            postData()
        }
    }

    private fun postData() {
        val user = User(mEtMail.text.toString(),mEtPassword.text.toString())
        DebugUtils.showLog(tag,"////// ${user.id}")
        val postService = RetrofitInstance.service
        val call = postService?.postSample(user)
        call?.enqueue(object : Callback<User>{
            override fun onFailure(call: Call<User>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                val userResponse = response.body()
                mTvResult.text = "id ${userResponse?.id}"
            }

        })
    }
}
