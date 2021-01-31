import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import java.text.SimpleDateFormat
import java.util.*

class ChatConnector(var inputStream: InputStream, var output: OutputStream): Runnable, ChatHistoryObserver {

   private val scanner: Scanner = Scanner(inputStream)
   private val printer: PrintStream = PrintStream(output, true)
   open val input = scanner.nextLine()
   open var username: String = " "
   var exit = false

   override fun newMessage(message: ChatMessage) {
      val messageAsJson = Json.stringify(ChatMessage.serializer(), message)

      printer.println(messageAsJson)
   }
   fun quit() {
      scanner.close()
      Users.removeUser(username)
      exit = true
   }

   override fun run() {
   //   printer.println("Hello!")
     // printer.println("Enter username:")
      while (username == " ") {
         username = scanner.nextLine()
         if (Users.users.contains(username)) {
           // printer.println("Username already exists try again")
            username = " "
         } else {
            if (username == "") {
             //  printer.println("Try again")
               username = " "
            }
            else {
               Users.addUser(username)
               ChatHistory.registerObserver(this)
            }
         }
      }
      while (!exit) {
         var command = ""

         command = scanner.nextLine()
         val newMessage = Json.parse(ChatMessage.serializer(), command)


         if (newMessage.message.startsWith("/")) {
            when (newMessage.message.split(' ')[0]) {
               "/users" -> newMessage(ChatMessage(message = Users.getUserList().toString(), username = ""))
                "/quit" -> quit()
               "/history" -> newMessage(ChatMessage(message = ChatHistory.printHistory(), username = ""))
               "/top" -> newMessage(ChatMessage(message = Users.topChatter().toString(), username = ""))

               else -> {
                  //printer.println("Wrong command")
               }
            }
         } else {

            Users.userAddValue(username)
            ChatHistory.insert(newMessage)
         }
      }


   }
}