package com.example.frugalfoodie.DB;

import androidx.room.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface UserDAO {


    @Query("select * from userTable where username =:username and password=:password ")
    User loginUser(String username, String password);

    /**
     * Insert an user into the database
     * @param user
     */
    @Insert
    void insertUser(User user);

    /**
     * Retrieve all users from the database
     * @return a list of all the users in the database
     */
    @Query("SELECT * FROM userTable")
    List<User> getAllUsers();

    /**
     * Retrieve the user with the specified username
     * @param search
     * @return the user with the specified username
     */
    @Query("SELECT * FROM userTable where username LIKE :search")
    User getUserWithUsername(String search);


}
