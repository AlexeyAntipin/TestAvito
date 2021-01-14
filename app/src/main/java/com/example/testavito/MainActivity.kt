package com.example.testavito

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testavito.Adapters.Adapter
import com.example.testavito.Data.Pull

class MainActivity : AppCompatActivity() {

    var list = mutableListOf<String>()
    lateinit var adapter: Adapter
    var j = 1
    var handler = Handler()
    lateinit var run: Runnable
    var number_of_columns: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var rv = findViewById<RecyclerView>(R.id.recycler_view)
        run = Runnable {
            addItem(Pull.getNumber())
            handler.postDelayed(run, 5000)
        }

        if (lastNonConfigurationInstance == null) {
            list = getStartList()
        } else {
            list = lastCustomNonConfigurationInstance as MutableList<String>
            stopTimer()
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            number_of_columns = 2
        } else {
            number_of_columns = 4
        }

        adapter = Adapter(list) {
            deleteItem(it)
        }
        rv.adapter = adapter
        rv.layoutManager = GridLayoutManager(this, number_of_columns)
        rv.itemAnimator = DefaultItemAnimator()
        StartTimer()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        stopTimer()
        return list
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        stopTimer()
    }

    private fun getStartList(): MutableList<String> {
        var list = mutableListOf<String>()
        for (i in 1..Pull.getSize()) {
            list.add(i.toString())
            j++
        }
        return list
    }

    private fun deleteItem(position: Int) {
        list.removeAt(position)
        adapter.notifyItemRangeChanged(position, list.size)
    }

    private fun addItem(title: String) {
        if (title != "") {
            Pull.deleteNumber(title)
            j++
            list.add(title)
            if (list.size % number_of_columns != 1)
                adapter.notifyItemChanged(list.size - 1)
            else adapter.notifyItemInserted(list.size - 1)
        } else {
            list.add(j.toString())
            j++
            if (list.size % number_of_columns != 1)
                adapter.notifyItemChanged(list.size - 1)
            else adapter.notifyItemInserted(list.size - 1)
        }
    }

    private fun StartTimer() {
        handler.postDelayed(run, 5000)
    }

    private fun stopTimer() {
        handler.removeCallbacks(run)
    }

    private fun haveEmty(): Boolean {
        for (item in list) {
            if (item == "") return true
        }
        return false
    }
}