package com.example.frugalfoodie.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

@Dao
public interface UserDAO {

    @Query("select * from userTable")
    List<User> getAllUsers();

    @Query("select * from userTable where username =:username and password=:password ")
    User loginUser(String username, String password);

    @Insert
    void addUser(User user);


}
