package com.kadircetin.smartshopping.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kadircetin.smartshopping.model.Item

@Dao
interface ItemDao {

    @Query(value = "SELECT name,id FROM item")
    fun getItemWithNameAndId(): List<Item>

    @Query(value = "SELECT * FROM item WHERE id=:id")
    fun getItemById(id: Int): Item?

    @Insert
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)
}