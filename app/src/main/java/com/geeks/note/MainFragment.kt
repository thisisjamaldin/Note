package com.geeks.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class MainFragment : Fragment(), NoteAdapter.IOnItem {

    private lateinit var adapter: NoteAdapter
    private lateinit var sortButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sortButton = view.findViewById(R.id.sort_button)
        adapter = NoteAdapter(this)
        adapter.setList((requireActivity() as MainActivity).list)
    }

    override fun delete(pos: Int) {
//        ((MainActivity) requireActivity()).list
        (requireActivity() as MainActivity).list.removeAt(pos)
        adapter.delete(pos)
    }

    override fun share(pos: Int) {
    }

    override fun edit(pos: Int, note: NoteModel) {
        val addFragment = AddFragment()
        val bundle = Bundle()
        bundle.putSerializable("edit", note)
        addFragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, addFragment)
            .commit()
    }
}