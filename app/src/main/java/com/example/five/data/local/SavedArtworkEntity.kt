package com.example.five.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.five.artworklist.domain.models.Artwork
import com.example.five.data.local.SavedArtworkEntity.Companion.TABLE_NAME
import java.util.ArrayList

@Entity(tableName = TABLE_NAME)
data class SavedArtworkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("artist_title")
    val artistTitle: String?,
    @ColumnInfo("title")
    val title: String?,
    @ColumnInfo("date_display")
    val dateDisplay: String?,
    @ColumnInfo("image_id")
    val imageId: String?,
    @ColumnInfo("artwork_type_title")
    val artworkTypeTitle: String?,
    @ColumnInfo("gallery_title")
    val galleryTittle: String?,
    @ColumnInfo("credit_line")
    val creditLine: String?,
    @ColumnInfo("dimensions")
    val dimensions: String?,
    @ColumnInfo("material_titles")
    val materialTitles: ArrayList<String>?,
    @ColumnInfo("is_bookmarked")
    var isBookmarked: Boolean
) {
    companion object {
        const val TABLE_NAME = "saved_artwork_list_entity"
    }
}

fun SavedArtworkEntity.toArtwork(): Artwork {
    return Artwork(
        id = this.id,
        artistTitle = this.artistTitle,
        title = this.title,
        dateDisplay = this.dateDisplay,
        imageId = this.imageId,
        artworkTypeTitle = this.artworkTypeTitle,
        galleryTittle = this.galleryTittle,
        creditLine = this.creditLine,
        dimensions = this.dimensions,
        materialTitles = this.materialTitles,
        isBookmarked = this.isBookmarked
    )
}