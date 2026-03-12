package com.UserService.service

import com.UserService.model.User
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService {
    var users: MutableMap<String, User> = emptyMap<String, User>() as MutableMap<String, User>


    fun register(firstName: String, lastName: String, email: String, password: String){
        if (password.length < 6){
            throw IllegalArgumentException("Password is invalid")
        }
        val size = users.size
        val user: User = User(UUID.randomUUID(), firstName, lastName, email, password, true)
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