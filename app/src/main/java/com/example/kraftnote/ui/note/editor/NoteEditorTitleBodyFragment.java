package com.example.kraftnote.ui.note.editor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kraftnote.R;
import com.example.kraftnote.databinding.FragmentNoteEditorTitleBodyBinding;
import com.example.kraftnote.persistence.entities.Note;
import com.example.kraftnote.ui.note.contracts.NoteEditorChildFragmentBase;

public class NoteEditorTitleBodyFragment extends NoteEditorChildFragmentBase {
    private static final String TAG = NoteEditorTitleBodyFragment.class.getSimpleName();

    private FragmentNoteEditorTitleBodyBinding binding;
    private Note note;

    // state
    private boolean isTitleEditTextFocused = false;
    private boolean isBodyEditTextFocused = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_editor_title_body, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentNoteEditorTitleBodyBinding.bind(view);

        initializeProperties();
        listenEvents();
    }

    private void initializeProperties() {
        note = getNote();
        binding.editorBodyText.setBody(note.getBody());
        binding.noteTitle.setTitle(note.getName());
    }

    private void listenEvents() {
        binding.noteTitle.getTextInputEditText()
                .setOnFocusChangeListener((v, hasFocus) -> {
                    isTitleEditTextFocused = hasFocus;
                    binding.getRoot().post(this::onEditTextFocusChanged);
                });

        binding.editorBodyText.setOnFocusChangeListener((v, hasFocus) -> {
            isBodyEditTextFocused = hasFocus;
            binding.getRoot().post(this::onEditTextFocusChanged);
        });
    }

    private void onEditTextFocusChanged() {
        updateViewPagerScrollBehaviour(
                !isTitleEditTextFocused
                        && !isBodyEditTextFocused
        );
    }

    public String getName() {
        return binding.noteTitle.getTitle();
    }

    public String getBody() {
        return binding.editorBodyText.getBody();
    }

}
