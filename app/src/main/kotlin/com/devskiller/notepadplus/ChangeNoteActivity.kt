package com.devskiller.notepadplus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devskiller.notepadplus.databinding.ActivityChangeNoteBinding
import java.util.UUID

class ChangeNoteActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_NOTE_ID = "com.devskiller.intent.note_id"

        fun newIntent(
            context: Context,
            uuid: UUID
        ): Intent = Intent(context, ChangeNoteActivity::class.java)
                .putExtra(EXTRA_NOTE_ID, uuid)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding = ActivityChangeNoteBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        initListeners(viewBinding)

        val currentNote = NoteLab.getNote(intent.extras?.get(EXTRA_NOTE_ID) as UUID)
        if (currentNote != null) {
            viewBinding.etTitle.setText(currentNote.title)
            viewBinding.etDescription.setText(currentNote.description)
        }

        // START YOUR CHANGE
        // END YOUR CHANGE
    }

    private fun initListeners(viewBinding: ActivityChangeNoteBinding) {
        viewBinding.bSave.setOnClickListener {
            if (viewBinding.etTitle.text.isNullOrEmpty()) {
                viewBinding.etTitle.error = resources.getString(R.string.field_not_be_empty_error)
            } else {
                NoteLab.addNote(
                    Note(
                        viewBinding.etDescription.text.toString(),
                        intent.extras?.get(EXTRA_NOTE_ID) as UUID,
                        viewBinding.etTitle.text.toString()
                    )
                )
                this.finish()
            }
        }
    }
}
