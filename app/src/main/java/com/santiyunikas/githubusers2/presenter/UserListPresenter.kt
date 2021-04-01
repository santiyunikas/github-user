package com.santiyunikas.githubusers2.presenter

import com.santiyunikas.githubusers2.model.Objects
import com.santiyunikas.githubusers2.network.NetworkConfig
import com.santiyunikas.githubusers2.view.SearchUserActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class UserListPresenter(context: SearchUserActivity) {
    private var view = context

    fun searchUName(uName: String){
        NetworkConfig.serviceConnection()
                .search(uName)
                .enqueue(object: Callback<Objects>{
                    override fun onResponse(call: Call<Objects>, response: Response<Objects>) {
                        if (response.body()?.total_count?.toInt()!! > 0){
                            response.body()?.items?.forEach {
                                view.passingData(it)
                            }
                            view.onSuccess("Username Berhasil Ditemukan")
                        }else{
                            view.onError("Username Tidak Ditemukan")
                        }
                    }

                    override fun onFailure(call: Call<Objects>, t: Throwable) {
                        view.onError(t.localizedMessage)
                    }

                })
    }
}