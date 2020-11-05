package com.example.frugalfoodie.DB;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

/**
 * Class for creating user object
 */
@Entity(tableName = FFRoom.USER_TABLE)
public class User {

    @PrimaryKey
    @NonNull
    private String username;
    private String password;

    /**
     * Constructor
     * @param username - String for username
     * @param password - String for password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Username getter
     * @return String - for username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Username setter
     * @param username - String for username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Password getter
     * @return - String for password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Password setter
     * @param password - String for password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
