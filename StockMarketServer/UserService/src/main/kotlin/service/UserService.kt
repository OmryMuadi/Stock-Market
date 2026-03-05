package com.UserService.service

import com.UserService.model.User

class UserService {
    var users: MutableMap<String, User>

    constructor(){
        users = emptyMap<String, User>() as MutableMap<String, User>
    }

    fun register(first_name: String, last_name: String, email: String, password: String){
        if (password.length < 6){
            throw IllegalArgumentException("Password is invalid")
        }
        val size = users.size
        val user: User = User(size + 1, first_name, last_name, email, password, true)
        users.put(email, user);
    }

    fun login(email: String, password: String){
//        for ((_, user) in users){
//            if (user.email == email && user.isLoggedIn){
//                throw IllegalArgumentException("The user is already logged in")
//            }
//            else
//                if(user.email == email && user.password != password){
//                    throw IllegalArgumentException("Password mismatch")
//            }
//            else
//                if(user.email == email){
//                    user.isLoggedIn = true;
//                }
//        }
//        throw IllegalArgumentException("User with email $email not found")
        if (users[email] == null){
            throw IllegalArgumentException("User with email $email not found")
        }
        else
            if (users[email]!!.isLoggedIn){
                throw IllegalArgumentException("The user is already logged in")
            }
        if (users[email]!!.password != password){
            throw IllegalArgumentException("Password mismatch")
        }
        else{
            users[email]!!.isLoggedIn = true;
        }
    }

    fun logout(email: String){
//        for ((_, user) in users){
//            if (user.email == email && !user.isLoggedIn){
//                throw IllegalArgumentException("The user is already logged out")
//            }
//            else
//                if(user.email == email){
//                    user.isLoggedIn = false
//                }
//        }
        if (users[email] == null){
            throw IllegalArgumentException("User with email $email not found")
        }
        else
            if (!users[email]!!.isLoggedIn){
                throw IllegalArgumentException("The user is already logged out")
            }
        else
        {
            users[email]!!.isLoggedIn = false
        }
    }

    fun getUser(email: String): User{
//        for ((_, user) in users) {
//            if (user.email == email){
//                return user
//            }
//        }
//        throw IllegalArgumentException("User with email $email not found")
        if (users[email] == null){
            throw IllegalArgumentException("User with email $email not found")
        }
        return users[email]!!
    }
}