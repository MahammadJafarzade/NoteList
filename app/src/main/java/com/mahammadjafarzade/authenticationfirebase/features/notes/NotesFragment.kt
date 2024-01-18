package com.mahammadjafarzade.authenticationfirebase.features.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.mahammadjafarzade.authenticationfirebase.R
import com.mahammadjafarzade.authenticationfirebase.databinding.FragmentNotesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {

    lateinit var noteListAdapter : NoteListAdapter
    lateinit var noteTagAdapter: NoteTagAdapter

    lateinit var binding : FragmentNotesBinding
    val viewModel : NotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(inflater)

        binding.btnSave.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToAddNoteFragment()
            findNavController().navigate(action)
        }

        prepareRecyclerView()
        observeLiveData()

        return binding.root
    }

    private fun observeLiveData() {
        viewModel.getAllData().observe(viewLifecycleOwner) {
            noteListAdapter.setList(it)
        }
    }

    private fun prepareRecyclerView() {
        context?.let {
            val layoutManager = FlexboxLayoutManager(context)
            layoutManager.flexWrap = FlexWrap.WRAP
            binding.recylerView.layoutManager = layoutManager
        }
        noteListAdapter = NoteListAdapter()
        binding.recylerView.adapter = noteListAdapter

        noteTagAdapter = NoteTagAdapter()
        binding.recylerViewTags.adapter = noteTagAdapter
    }
}