package com.kadircetin.smartshopping.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Item(
    @ColumnInfo(name = "name")
    var itemName: String,

    @ColumnInfo(name = "storename")
    var storeName: String?,

    @ColumnInfo(name = "price")
    var price: String?,

    @ColumnInfo(name = "image")
    var image: ByteArray?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}