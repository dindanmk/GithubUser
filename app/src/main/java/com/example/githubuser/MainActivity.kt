package com.example.githubuser

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityBinding:ActivityMainBinding
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        mainActivityBinding.rvUser.setHasFixedSize(true)
        list.addAll(listUser)
        showRecyclerList()

    }

    private val listUser: ArrayList<User>
        get() {
            val dataName = resources.getStringArray(R.array.name)
            val dataUsername = resources.getStringArray(R.array.username)
            val dataPhoto = resources.obtainTypedArray(R.array.avatar)

            val followers = resources.getStringArray(R.array.followers)
            val following = resources.getStringArray(R.array.following)
            val company = resources.getStringArray(R.array.company)
            val location = resources.getStringArray(R.array.location)
            val repo = resources.getStringArray(R.array.repository)

            val listUser = ArrayList<User>()
            for (i in dataName.indices) {
                val user = User(
                    name = dataName[i],
                    username = dataUsername[i],
                    avatar = dataPhoto.getResourceId(i, -1),
                    followers = followers[i],
                    following = following[i],
                    company = company[i],
                    location = location[i],
                    repository = repo[i])
                listUser.add(user)
            }
            dataPhoto.recycle()
            return listUser
        }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation==Configuration.ORIENTATION_LANDSCAPE){
            mainActivityBinding.rvUser.layoutManager=GridLayoutManager(this, 2)
        }else{
            mainActivityBinding.rvUser.layoutManager=LinearLayoutManager(this)
        }

        val listUserAdapter = ListUserAdapter(list)
        mainActivityBinding.rvUser.adapter=listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(user: User) {
                showSelectedUser(user)
            }
        })
    }
    private fun showSelectedUser(user: User) {
        val DetailUser=User(
            user.username,
            user.name,
            user.avatar,
            user.followers,
            user.following,
            user.company,
            user.location,
            user.repository
        )

        val moveIntent = Intent(this@MainActivity, com.example.githubuser.DetailUser::class.java)
        moveIntent.putExtra(com.example.githubuser.DetailUser.GitHubUser,DetailUser)
        startActivity(moveIntent)
    }
}