package com.example.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.phonesapp1212.domain.model.Cat

@Entity(
    tableName = "cat_table",
    indices = [Index(value = ["title"], unique = true)]
)
data class CatEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "image link")
    val image: String
) {

//    fun toCat(catEntity: CatEntity): Cat {
//        return Cat(
//            title = title,
//            description = description,
//            image = image
//        )
//    }

    companion object {
        fun fromCatToCatEntity(cat: Cat): CatEntity {

            return CatEntity(
                0,
                title = cat.title,
                description = cat.description,
                image = cat.image
            )
        }
    }

}


