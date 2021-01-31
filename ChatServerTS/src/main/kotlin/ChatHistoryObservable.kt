interface ChatHistoryObservable {
    fun registerObserver(observer: ChatHistoryObserver)
    fun removeObserver(observer: ChatHistoryObserver)
    fun notifyObserver(message: ChatMessage)
}