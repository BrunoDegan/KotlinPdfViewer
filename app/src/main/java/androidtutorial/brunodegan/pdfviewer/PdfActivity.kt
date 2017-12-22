package androidtutorial.brunodegan.pdfviewer

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import androidtutorial.brunodegan.pdfviewer.domain.PdfDoc
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnRenderListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import kotlinx.android.synthetic.main.activity_pdf.*

/**
 * Created by brunodegan on 7/24/17.
 */
class PdfActivity : AppCompatActivity(), OnRenderListener, OnPageChangeListener {
	var languageDoc: PdfDoc?= null
	
	var toolbar : Toolbar?= null
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_pdf)
		
		toolbar = findViewById<Toolbar>(R.id.toolbar)
		
//		toolbar?.setDisplayHomeAsUpEnabled(true)
//		toolbar?.setDisplayShowHomeEnabled(true)toolbar
		
		languageDoc = intent.getParcelableExtra(PdfDoc.DOC_KEY)
		
		Log.d("TESTE",  "languageDoc language: " + languageDoc!!.language)
		Log.d("TESTE",  "languageDoc image res: " + languageDoc!!.imageRes)
		Log.d("TESTE",  "languageDoc page number: " + languageDoc!!.pageNumber)
		Log.d("TESTE",  "languageDoc path: " + languageDoc!!.path)
		
		pdfView.fromAsset( languageDoc?.path )
		
		/*
		 * Permite a definição da página que será carregada inicialmente.
		 * A contagem inicia em 0.
		 * */
		.defaultPage( languageDoc?.getActualPage(this) ?: 0 )
		
		/*
		 * Caso um ScrollHandle seja definido, a numeração da página estará
		 * presente na tela para que o usuário saiba em qual página está,
		 * isso sem necessidade de dar o zoom nela. É possível implementar
		 * o seu próprio ScrollHandle, mas a API também já fornece uma
		 * implementação que tem como parâmetro um objeto de contexto,
		 * DefaultScrollHandle.
		 * */
		.scrollHandle( DefaultScrollHandle(this) )
		
		/*
		 * Se definido como false, o usuário não conseguirá mudar de página.
		 * */
		.enableSwipe(true)
		
		/*
		 * Por padrão o swipe é vertical, ou seja, as próximas páginas estão
		 * abaixo no scroll. Com swipeHorizontal() recebendo true o swipe
		 * passa a ser horizontal, onde a próxima página é a que está a direita.
		 * */
		.swipeHorizontal(true)
		
		/*
		 * Útil para PDFs que necessitam de senha para serem visualizados.
		 * */
		.password(null)
		
		/*
		 * Caso true, permite que os níveis de zoom (min, middle, max) também
		 * seja acionados caso o usuário dê touchs na tela do device.
		 * */
		.enableDoubletap(true)
		
		/*
		 * Caso true, permite que anotações e comentários, extra PDF original,
		 * sejam apresentados.
		 * */
		.enableAnnotationRendering(true)
		
		/*
		 * Caso true, permite que haja otimização de renderização em telas
		 * menores.
		 * */
		.enableAntialiasing(true)
		.onPageChange(this)
		.onRender(this)
		.load()
		
		pdfView.minZoom = 1F
		pdfView.maxZoom = 4F
		pdfView.midZoom = 1.75F
	}
	
	override fun onResume() {
		super.onResume()
		toolbar?.title = languageDoc?.language
	}
	
	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		val id = item?.getItemId()
		
		if (id == android.R.id.home) {
			finish()
			return true
		}
		
		return super.onOptionsItemSelected(item)
	}
	
	override fun onPageChanged(page: Int, pageCount: Int) {
		languageDoc?.saveActualPage(context = this,page = page)
	}
	
	override fun onInitiallyRendered(nbPages: Int, pageWidth: Float, pageHeight: Float) {
		pdfView.fitToWidth(languageDoc?.getActualPage(this) ?: 0)
	}
}
