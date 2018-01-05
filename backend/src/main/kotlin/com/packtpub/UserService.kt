package com.packtpub

interface UserService {
    fun getUser(id:Long): PacktUser?
    fun getUserByUsername(name:String): PacktUser?
}

internal class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun getUser(id: Long): PacktUser? = userRepository.findById(id).orElse(null)

    override fun getUserByUsername(name: String): PacktUser? {
        val name = userRepository.findOneByUsername(name)
        return name.orElse(null)
    }

}