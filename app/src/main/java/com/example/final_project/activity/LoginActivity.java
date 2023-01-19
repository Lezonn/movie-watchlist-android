package com.example.final_project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_project.R;
import com.example.final_project.db.UserFunctionDB;
import com.example.final_project.singleton.CurrentUser;
import com.example.final_project.model.User;

public class LoginActivity extends AppCompatActivity {

    EditText editUsername;
    EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editUsername = findViewById(R.id.editUsername);
        editPassword = (EditText)findViewById(R.id.editPassword);
    }

    public void login(View view) {
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        if(username.isEmpty() || password.isEmpty()) {
            String info = "Username and password can't be empty";
            Toast.makeText(getBaseContext(), info, Toast.LENGTH_SHORT).show();
            return;
        }

        UserFunctionDB uDB = new UserFunctionDB(getBaseContext());
        User user = uDB.getUser(username, password);

        if(user == null) {
            String info = "Credetials doesn't match";
            Toast.makeText(getBaseContext(), info, Toast.LENGTH_SHORT).show();
            return;
        }

        CurrentUser currentUser = CurrentUser.getInstance();
        currentUser.setCurrentUser(user);
        openHomeActivity();
    }

    public void register(View view) {
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        UserFunctionDB uDB = new UserFunctionDB(getBaseContext());

        if(uDB.checkUsername(username)) {
            String info = "Username already exists!";
            Toast.makeText(getBaseContext(), info, Toast.LENGTH_SHORT).show();
            return;
        }

        uDB.register(username, password);
        User user = uDB.getUser(username, password);

        CurrentUser currentUser = CurrentUser.getInstance();
        currentUser.setCurrentUser(user);
        openHomeActivity();
    }

    public void openHomeActivity() {
        Intent homeActivity = new Intent(getBaseContext(), HomeActivity.class);
        startActivity(homeActivity);
        finish();
    }
}