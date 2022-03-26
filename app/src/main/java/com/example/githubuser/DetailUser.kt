package com.example.githubuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubuser.databinding.UserDetailBinding

class DetailUser : AppCompatActivity() {
    lateinit var bindingDetailUser: UserDetailBinding
    companion object{
        const val GitHubUser="Detail User"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_detail)

        bindingDetailUser= UserDetailBinding.inflate(layoutInflater)
        setContentView(bindingDetailUser.root)

        val user = intent.getParcelableExtra<User>(GitHubUser)

        bindingDetailUser.imgItemPhoto.setImageResource(user?.avatar!!)
        bindingDetailUser.tvItemName.text=user?.name
        bindingDetailUser.tvItemUsername.text=user?.username
        bindingDetailUser.tvFollowers.text=user?.followers
        bindingDetailUser.tvFollowing.text=user?.following
        bindingDetailUser.tvCompany.text=user?.company
        bindingDetailUser.tvLocation.text=user?.location
        bindingDetailUser.tvRepository.text=user?.repository
    }

}