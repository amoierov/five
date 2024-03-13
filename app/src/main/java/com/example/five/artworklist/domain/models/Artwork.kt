package com.example.five.artworklist.domain.models
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.ArrayList


data class Artwork(
    @SerializedName("artist_title")
    val artistTitle: String?,
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
    val materialTitles: ArrayList<String>?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(artistTitle)
        parcel.writeString(title)
        parcel.writeString(dateDisplay)
        parcel.writeString(imageId)
        parcel.writeString(artworkTypeTitle)
        parcel.writeString(galleryTittle)
        parcel.writeString(creditLine)
        parcel.writeString(dimensions)
        parcel.writeStringList(materialTitles)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Artwork> {
        override fun createFromParcel(parcel: Parcel): Artwork {
            return Artwork(parcel)
        }

        override fun newArray(size: Int): Array<Artwork?> {
            return arrayOfNulls(size)
        }
    }
}