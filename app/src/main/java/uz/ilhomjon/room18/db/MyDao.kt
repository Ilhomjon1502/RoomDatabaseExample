package uz.ilhomjon.room18.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.ilhomjon.room18.models.User

@Dao
interface MyDao {

    @Insert
    fun addUser(user: User)

    @Query("select * from user")
    fun getAllUsers():List<User>

    @Delete
    fun deleteUser(user: User)

    @Update
    fun editUser(user: User)
}