//package com.example.voaenglish.database
//
//import android.content.Context
//import android.util.Log
//import com.example.voaenglish.model.Dog
//import io.realm.Realm
//
//class DatabaseManager {
//
//    companion object {
//        val TAG = DatabaseManager::class.java
//        val instance = DatabaseManager()
//    }
//
//    fun saveDog(context: Context, dog: Dog?) {
//        val realm = Realm.getDefaultInstance()
//        realm.beginTransaction()
//        if (dog != null) realm?.copyToRealmOrUpdate(dog)
//        realm.commitTransaction()
//    }
//
//    fun getDog(context: Context, age: Int): Dog? {
//        var realm = Realm.getDefaultInstance()
//        Log.d(TAG.toString(), "getDog: " + realm.where(Dog::class.java).equalTo("age", age).findFirst())
//        return realm.where(Dog::class.java).equalTo("age", age).findFirst()
//    }
//
//
//}