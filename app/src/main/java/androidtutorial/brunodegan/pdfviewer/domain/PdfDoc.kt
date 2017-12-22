package androidtutorial.brunodegan.pdfviewer.domain

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import androidtutorial.brunodegan.pdfviewer.data.DocDatabase

/**
 * Created by brunodegan on 7/20/17.
 */
class PdfDoc(val path: String,
             val imageRes: Int,
             val language: String,
             val pageNumber: Int) : Parcelable {
	
	companion object {
		@JvmField val DOC_KEY = "languageDoc"
		@JvmField val CREATOR: Parcelable.Creator<PdfDoc> = object : Parcelable.Creator<PdfDoc> {
			override fun createFromParcel(source: Parcel): PdfDoc = PdfDoc(source)
			override fun newArray(size: Int): Array<PdfDoc?> = arrayOfNulls(size)
		}
	}
	
	constructor(source: Parcel) : this(
		source.readString(),
		source.readInt(),
		source.readString(),
		source.readInt()
	)
	
	override fun describeContents() = 0
	
	override fun writeToParcel(dest: Parcel, flags: Int) {
		dest.writeString(path)
		dest.writeInt(imageRes)
		dest.writeString(language)
		dest.writeInt(pageNumber)
	}
	
	fun saveActualPage(context : Context, page :Int) {
		DocDatabase.saveActualPageInSharedPreference(context,path,pageNumber)
	}
	
	fun getActualPage(context : Context, plusPage: Int = 0): Int {
		return DocDatabase.getActualPageInSharedPreference(context, path) + plusPage
	}
	
}