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
public interface CategoryDao {
    @Query("SELECT * FROM categoryTable")
    List<Category> getAll();

    @Query("SELECT * FROM categoryTable WHERE id IN (:categoryIds)")
    List<Category> loadAllByIds(Integer[] categoryIds);

    @Query("SELECT * FROM categoryTable WHERE id LIKE :id LIMIT 1")
    Category findById(Integer id);

    @Query("SELECT * FROM categoryTable WHERE name LIKE :name LIMIT 1")
    Category findByName(String name);

    @Query("SELECT id FROM categoryTable ORDER BY id DESC LIMIT 1")
    Integer getLastUsedId();

    @Query("UPDATE categoryTable SET name = :name WHERE id = :id")
    void updateCategory(Integer id, String name);

    @Insert
    void insertOne(Category category);

    @Insert
    void insertAll(List<Category> categories);

    @Update
    void updateCategorys(List<Category> categories);

    @Delete
    void delete(Category category);

    @Query("DELETE FROM categoryTable")
    void clearTable();

}
