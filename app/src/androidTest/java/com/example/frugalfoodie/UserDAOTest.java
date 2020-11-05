package com.example.frugalfoodie;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.frugalfoodie.DB.FFRoom;
import com.example.frugalfoodie.DB.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDAOTest {
    private FFRoom db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, FFRoom.class).build();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void testInsertUser() throws Exception {
        // insert user into database
        User user = new User("username", "password123");
        db.userDAO().insertUser(user);

        // retrieve user from database
        User userInDatabase = db.userDAO().getUserWithUsername("username");

        // ensure the retrieved user's attributes are correct
        assertEquals(userInDatabase.getUsername(), "username");
        assertEquals(userInDatabase.getPassword(), "password123");
    }

    @Test
    public void testLoginUser() throws Exception {
        // insert user into database
        User user = new User("username", "password123");
        db.userDAO().insertUser(user);

        // retrieve user from database
        User userInDatabase = db.userDAO().loginUser("username", "password123");

        // ensure the user is correctly retrieved
        assertTrue(userInDatabase != null);
        assertEquals(userInDatabase.getUsername(), "username");
        assertEquals(userInDatabase.getPassword(), "password123");

        // ensure attempted retrieval of invalid user will not return anything
        User userNotInDatabase = db.userDAO().loginUser("username", "password1234");
        assertTrue(userNotInDatabase == null);

    }
}
