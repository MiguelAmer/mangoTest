package com.devskiller.notepadplus

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.devskiller.notepadplus.databinding.ActivityMainBinding
import java.util.UUID

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).root)

        if (NoteLab.notes.isNotEmpty()) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fl_fragment_container, NoteListFragment.newInstance())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        } else {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fl_fragment_container, WelcomeFragment.newInstance())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onResume() {
        super.onResume()
        if (NoteLab.notes.isNotEmpty()) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fl_fragment_container, NoteListFragment.newInstance())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        } else {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fl_fragment_container, WelcomeFragment.newInstance())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.create_note -> {
            val intent = ChangeNoteActivity.newIntent(this, UUID.randomUUID())
            startActivity(intent)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
    }
}
