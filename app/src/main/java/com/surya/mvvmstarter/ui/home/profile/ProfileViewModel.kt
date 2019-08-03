package com.surya.mvvmstarter.ui.home.profile

import androidx.lifecycle.ViewModel;
import com.surya.mvvmstarter.data.repository.UserRepository

class ProfileViewModel(
    repository : UserRepository
) : ViewModel() {

    val user = repository.getUser()

}
