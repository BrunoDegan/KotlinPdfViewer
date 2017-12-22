package androidtutorial.brunodegan.pdfviewer.data

import android.content.Context
import androidtutorial.brunodegan.pdfviewer.R
import androidtutorial.brunodegan.pdfviewer.domain.PdfDoc

/**
 * Created by brunodegan on 7/20/17.
 */
class DocDatabase {
	companion object {
		fun getDocs() = listOf(
				PdfDoc("kotlin-docs.pdf", R.drawable.kotlin_bg, "Kotlin", 194),
				PdfDoc("java-docs.pdf", R.drawable.java_bg, "Java", 670),
				PdfDoc("python-docs.pdf", R.drawable.python_bg, "Python", 1538),
				PdfDoc("haskell-docs.pdf", R.drawable.haskell_bg, "Haskell", 503),
				PdfDoc("scala-docs.pdf", R.drawable.scala_bg, "Scala", 547))
		
		
		fun saveActualPageInSharedPreference(context: Context, key: String, pageNum: Int) {
			context?.getSharedPreferences(PdfDoc.DOC_KEY, Context.MODE_PRIVATE).
					edit().putInt("$key-page", pageNum).apply()
			
		}
		
		fun getActualPageInSharedPreference(context: Context, key: String): Int {
			return context.getSharedPreferences(PdfDoc.DOC_KEY, Context.MODE_PRIVATE)
					.getInt("$key-page", 0)
		}
	}
	
}
