package com.UserService.service

import com.UserService.model.User

class UserService {
    var users: MutableMap<Int, User>

    constructor(){
        users = emptyMap<Int, User>() as MutableMap<Int, User>
    }

    fun register(first_name: String, last_name: String, email: String, password: String, cashBalance: Float){
        if (password.length < 6){
            throw IllegalArgumentException("Password is invalid")
        }
        val size = users.size
        val user: User = User(size + 1, first_name, last_name, email, password,  cashBalance, true)
        users.put(size + 1, user);
    }

    fun login(email: String, password: String){
        for ((_, user) in users){
            if (user.email == email && user.isLoggedIn){
                throw IllegalArgumentException("The user is already logged in")
            }
            else
                if(user.email == email && user.password != password){
                    throw IllegalArgumentException("Password mismatch")
            }
            else
                if(user.email == email){
                    user.isLoggedIn = true;
                }
        }
    }

    fun logout(email: String){
        for ((_, user) in users){
            if (user.email == email && !user.isLoggedIn){
                throw IllegalArgumentException("The user is already logged out")
            }
            else
                if(user.email == email){
                    user.isLoggedIn = false
                }
        }
    }
}