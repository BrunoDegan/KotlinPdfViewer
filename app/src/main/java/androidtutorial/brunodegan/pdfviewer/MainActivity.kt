package androidtutorial.brunodegan.pdfviewer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager

import androidtutorial.brunodegan.pdfviewer.Adapters.DocAdapter
import androidtutorial.brunodegan.pdfviewer.data.DocDatabase

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		initRecycler()
	}
	
	private fun initRecycler() {
		rv_todo.setHasFixedSize(true)
		
		val mLayoutManager = LinearLayoutManager(this)
		rv_todo.layoutManager = mLayoutManager
		
		val mDivider = DividerItemDecoration(this, mLayoutManager.orientation)
		rv_todo.addItemDecoration(mDivider)
		
		val adapter = DocAdapter(this, DocDatabase.getDocs())
		rv_todo.adapter = adapter
	}
	
	override fun onResume() {
		super.onResume()
		initRecycler()
	}
}
