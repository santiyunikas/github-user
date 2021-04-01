package com.santiyunikas.githubusers2.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiyunikas.githubusers2.R
import com.santiyunikas.githubusers2.model.UserGithub
import com.santiyunikas.githubusers2.presenter.UserListPresenter
import kotlinx.android.synthetic.main.activity_searchuser.*


//884db205037ef807b86b640cb1d52bf3b89eebc4
class SearchUserActivity : AppCompatActivity(), SearchView.OnQueryTextListener{
    private lateinit var presenter: UserListPresenter
    private lateinit var userList: ArrayList<UserGithub>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchuser)
        actionBar?.title = "Github User's Search"
        presenter = UserListPresenter(this)
        userList = ArrayList()

        search_bar.setOnQueryTextListener(this)
    }

    private fun showRecyclerList(){
        rv_user.layoutManager = LinearLayoutManager(this)
        val daftarUserAdapter = SearchUserAdapter(userList)
        rv_user.adapter = daftarUserAdapter

        daftarUserAdapter.setOnItemClickCallback(object :
            SearchUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserGithub, position: Int) {
                moveDetailUser(data, position)
            }

        })
    }

    private fun moveDetailUser(user: UserGithub, position: Int) {
        val browserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.github.com/" + user.login)
        )
        startActivity(browserIntent)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isConnected(context: Context?): Boolean {
        val state: Boolean
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetwork
        state = activeNetwork != null
        return state
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun onError(msg: String){
        if (!isConnected(this)){
            Toast.makeText(this, "Aktifkan Koneksi Internet Kamu", Toast.LENGTH_SHORT)
                .show()
        }else{
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

    }

    fun onSuccess(msg: String){
        Toast.makeText(this, "jumlah user ${userList.size}", Toast.LENGTH_SHORT).show()
        showRecyclerList()
    }

    fun passingData(obj: UserGithub){
        userList.add(obj)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        userList.clear()
        if (query != null) {
            presenter.searchUName(query)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        userList.clear()
        return false
    }

}
