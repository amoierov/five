package com.example.five.artworklist.domain.models
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.example.five.data.local.SavedArtworkEntity
import com.google.gson.annotations.SerializedName
import java.util.ArrayList


data class Artwork(
    @SerializedName("artist_title")
    val artistTitle: String?,
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("date_display")
    val dateDisplay: String?,
    @SerializedName("image_id")
    val imageId: String?,
    @SerializedName("artwork_type_title")
    val artworkTypeTitle: String?,
    @SerializedName("gallery_title")
    val galleryTittle: String?,
    @SerializedName("credit_line")
    val creditLine: String?,
    @SerializedName("dimensions")
    val dimensions: String?,
    @SerializedName("material_titles")
    val materialTitles: ArrayList<String>?,
    var isBookmarked: Boolean
): Parcelable {
    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readBoolean()
    ) {
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(artistTitle)
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(dateDisplay)
        parcel.writeString(imageId)
        parcel.writeString(artworkTypeTitle)
        parcel.writeString(galleryTittle)
        parcel.writeString(creditLine)
        parcel.writeString(dimensions)
        parcel.writeStringList(materialTitles)
        parcel.writeBoolean(isBookmarked)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Artwork> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Artwork {
            return Artwork(parcel)
        }

        override fun newArray(size: Int): Array<Artwork?> {
            return arrayOfNulls(size)
        }
    }
}

fun Artwork.toSavedArtworkEntity(): SavedArtworkEntity {
    return SavedArtworkEntity(
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