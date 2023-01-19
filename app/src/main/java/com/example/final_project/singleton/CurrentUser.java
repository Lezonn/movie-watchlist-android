package com.example.final_project.singleton;

import com.example.final_project.model.Movie;
import com.example.final_project.model.User;

import java.util.List;

public class CurrentUser {
    private static CurrentUser instance;

    private User currentUser;
    private List<Movie> userWatchlist;

    private CurrentUser(){}

    public static CurrentUser getInstance() {
        if(instance == null) {
            synchronized (CurrentUser.class) {
                if(instance == null) {
                    instance = new CurrentUser();
                }
            }
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public List<Movie> getUserWatchlist() {
        return userWatchlist;
    }

    public void setUserWatchlist(List<Movie> movieWatchlist) {
        userWatchlist = movieWatchlist;
    }

}
