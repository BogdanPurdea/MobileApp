package models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Bogdan on 12/31/2017.
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM userTable")
    List<User> getAll();

    @Query("SELECT * FROM userTable WHERE id IN (:userIds)")
    List<User> loadAllByIds(Integer[] userIds);

    @Query("SELECT * FROM userTable WHERE id LIKE :id LIMIT 1")
    User findById(Integer id);

    @Query("SELECT * FROM userTable WHERE email LIKE :email LIMIT 1")
    User findByEmail(String email);

    @Query("SELECT id FROM userTable ORDER BY id DESC LIMIT 1")
    Integer getLastUsedId();

    @Query("UPDATE userTable SET username = :username, password = :password, email = :email WHERE id = :id")
    void updateUser(Integer id, String username, String password, String email);

    @Query("UPDATE userTable SET role = :role WHERE id = :id")
    void updateRole(Integer id, String role);

    @Insert
    void insertOne(User user);

    @Insert
    void insertAll(List<User> users);

    @Update
    void updateUsers(List<User> users);

    @Delete
    void delete(User user);

    @Query("DELETE FROM userTable")
    void clearTable();
}
