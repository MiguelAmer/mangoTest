package com.devskiller.notepadplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devskiller.notepadplus.databinding.ViewNoteListItemBinding
import java.util.UUID

class NoteAdapter(private val mNotes: List<Note>) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    inner class NoteHolder(
        private val mViewBinding: ViewNoteListItemBinding
    ) : RecyclerView.ViewHolder(mViewBinding.root), View.OnClickListener {

        private var mNote: Note? = null
        lateinit var currentNoteUuid: UUID

        val titleTextView = mViewBinding.tvNoteTitle

        init {
            mViewBinding.tvNoteTitle.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            view.context.startActivity(ChangeNoteActivity.newIntent(
                view.context,
                currentNoteUuid
            ))
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteHolder = NoteHolder(ViewNoteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(
        holder: NoteHolder,
        position: Int
    ) {
        holder.titleTextView.text = mNotes[position].title
        holder.currentNoteUuid = mNotes[position].id
    }

    override fun getItemCount(): Int {
        return mNotes.size
    }
}
