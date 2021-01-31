object ChatHistory: ChatHistoryObservable{
    var chatHistory = mutableListOf<ChatMessage>()
    var chatMessage = mutableListOf<ChatHistoryObserver>()

     fun printHistory(): String{
         var chatHistoryLastTen: String = ""
         chatHistoryLastTen = chatHistory.toString()
     return chatHistoryLastTen
    }

    override fun registerObserver(observer: ChatHistoryObserver) {
        chatMessage.add(observer)
    }
    override fun notifyObserver(message: ChatMessage) {
        for (observer in chatMessage){
            observer.newMessage(message)
        }
    }
    override fun removeObserver(observer: ChatHistoryObserver) {
        chatMessage.remove(observer)
    }
    fun insert(message: ChatMessage){
        chatHistory.add(message)
        notifyObserver(message)

        if (chatHistory.size > 10){
            chatHistory.removeAt(0)
        }
    }
}