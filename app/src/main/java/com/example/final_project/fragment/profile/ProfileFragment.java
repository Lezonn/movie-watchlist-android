package com.example.final_project.fragment.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.final_project.activity.LoginActivity;
import com.example.final_project.databinding.FragmentProfileBinding;
import com.example.final_project.singleton.CurrentUser;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView textView = binding.textUsername;
        textView.setText(CurrentUser.getInstance().getCurrentUser().getUsername());

        setBtnListener();

        return root;
    }

    public void setBtnListener() {
        final Button btnLogout = binding.btnLogout;

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentUser.getInstance().setCurrentUser(null);
                Intent loginActivity = new Intent(getContext(), LoginActivity.class);
                startActivity(loginActivity);
                getActivity().finish();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}