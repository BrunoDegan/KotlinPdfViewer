package androidtutorial.brunodegan.pdfviewer.Adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidtutorial.brunodegan.pdfviewer.PdfActivity
import androidtutorial.brunodegan.pdfviewer.R
import androidtutorial.brunodegan.pdfviewer.domain.PdfDoc

/**
 * Created by brunodegan on 7/21/17.
 */
class DocAdapter(private val context : Context,
                 private val pdfDocList:List<PdfDoc>) :
		RecyclerView.Adapter<DocAdapter.ViewHolder>() {
	
	override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
		holder?.setData(pdfDocList.get(position))
	}
	
	override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
		val v = LayoutInflater.from(context).inflate(R.layout.item_doc,parent,false)
		return ViewHolder(v)
	}
	
	override fun getItemCount(): Int {
		return pdfDocList.size
	}
	
	inner class ViewHolder(itemView: View) :
			RecyclerView.ViewHolder(itemView),
			View.OnClickListener{
		
		var ivCover : ImageView
		var tvLanguage : TextView
		var tvTotalPages : TextView
		var tvPageStopped : TextView
		
		init {
			itemView.setOnClickListener(this)
			ivCover = itemView.findViewById(R.id.iv_cover)
			tvLanguage = itemView.findViewById(R.id.tv_language)
			tvTotalPages = itemView.findViewById(R.id.tv_total_pages)
			tvPageStopped = itemView.findViewById(R.id.tv_page_stopped)
		}
		
		fun setData(pdf : PdfDoc) {
			ivCover.setImageResource(pdf.imageRes)
			tvLanguage.text = pdf.language
			tvTotalPages.text = "${pdf.pageNumber} pages"
			
			if( pdf.getActualPage(context) > 0 ){
				tvPageStopped.text = "Parou na página ${pdf.getActualPage(context, 1)}"
				tvPageStopped.visibility = View.VISIBLE
			}
			else{
				tvPageStopped.visibility = View.GONE
			}
		}
		
		override fun onClick(p0: View?) {
			val intent = Intent(context,PdfActivity::class.java)
//			Log.d("TESTE",  "languageDoc language: " + pdfDocList.get(adapterPosition)?.language)
//			Log.d("TESTE",  "languageDoc image res: " + pdfDocList.get(adapterPosition)?.imageRes)
//			Log.d("TESTE",  "languageDoc page number: " + pdfDocList.get(adapterPosition)?.pageNumber)
//			Log.d("TESTE",  "languageDoc path: " + pdfDocList.get(adapterPosition)?.path)
//
			
			val bundle = Bundle()
			bundle.putParcelable(PdfDoc.DOC_KEY, pdfDocList.get(adapterPosition))
			intent.putExtras(bundle)
			
			context.startActivity(intent,bundle)
		}
	}
	
}