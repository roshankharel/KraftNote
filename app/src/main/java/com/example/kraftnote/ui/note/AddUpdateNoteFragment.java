package com.example.kraftnote.ui.note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.kraftnote.R;
import com.example.kraftnote.persistence.entities.Category;
import com.example.kraftnote.persistence.viewmodels.CategoryViewModel;
import com.example.kraftnote.ui.category.CategoryTabLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AddUpdateNoteFragment extends Fragment {
    private NavController navController;
    private CategoryViewModel categoryViewModel;
    private List<Category> categories = new ArrayList<>();

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_update_note, container, false);
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeProperties();
        findViews(view);
        listenEvents();
    }

    private void initializeProperties() {
        navController = NavHostFragment.findNavController(this);
    }

    private void findViews(@NonNull final View view) {

    }

    private void listenEvents() {
        categoryViewModel.getAll().observe(getViewLifecycleOwner(), this::categoriesMutated);
    }

    private void categoriesMutated(List<Category> categories) {
        this.categories = categories;
    }

    private void gotoNoteFragment() {
        // TODO confirm before going back
        navController.navigate(R.id.action_AddUpdateNoteFragment_to_NoteFragment);
    }
}
