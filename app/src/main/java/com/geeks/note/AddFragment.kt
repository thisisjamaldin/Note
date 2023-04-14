package com.geeks.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class AddFragment : Fragment() {

    private lateinit var editText: EditText
    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editText = view.findViewById(R.id.edit_title)
        button = view.findViewById(R.id.add_button)

        if (arguments != null){
            val note = arguments?.getSerializable("edit") as NoteModel
            editText.setText(note.title)
            button.setOnClickListener {
                val newNote = NoteModel("${editText.text}")
                (requireActivity() as MainActivity).list.add(newNote)
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MainFragment())
                    .commit()
            }
        }
    }
}