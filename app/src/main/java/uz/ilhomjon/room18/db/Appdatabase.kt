package uz.ilhomjon.room18.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.ilhomjon.room18.models.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun myDao():MyDao

    companion object{
        var instance:AppDatabase? = null
        fun getInstance(context: Context):AppDatabase{
            if (instance==null){
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "db_room")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}