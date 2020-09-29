package com.example.voaenglish.ui.login

class Processor {
    interface ActionCallback {
        fun success(): String?
        fun failure(): String?
    }

    fun performEvent(decision: Boolean, callback: ActionCallback): String? {
        return if (decision) {
            callback.success()
        } else {
            callback.failure()
        }
    }
}