import java.io.PrintStream
import java.security.Key
import java.util.*
import kotlin.coroutines.ContinuationInterceptor

object Users {
    open val users = mutableMapOf<String, Int>()
    fun addUser(username: String){
        if(users.contains(username)){
            println("name is already taken")
        }
        else{
            users.put(username, 0)
            println("$users")
        }
    }
    fun removeUser(username: String){
        users.remove(username)
    }
    fun getUserList(): Any {
        return users.keys
    }
    fun userAddValue(username: String){
        users.merge(username,1, Int::plus)
    }
    fun topChatter(): Any{
       var value = Collections.max(users.values)
        var keys = users.filterValues { it == value }.keys
        return keys + value
}
}