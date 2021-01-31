import com.google.gson.Gson
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.time.LocalTime
import java.util.*

@Serializable
class ChatMessage(val message: String, val username: String, var time: Long = Date().time){
    override fun toString(): String {
            return "[$time] $username: $message"
    }
}