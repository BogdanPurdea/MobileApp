package models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Bogdan on 12/20/2017.
 */

@Dao
public interface TransactionDao {
    @Query("SELECT * FROM transactionTable")
    List<Transaction> getAll();

    @Query("SELECT * FROM transactionTable WHERE userid LIKE :userId")
    List<Transaction> getAllForUser(String userId);

    @Query("SELECT * FROM transactionTable WHERE id IN (:transactionIds)")
    List<Transaction> loadAllByIds(Integer[] transactionIds);

    @Query("SELECT * FROM transactionTable WHERE id LIKE :id LIMIT 1")
    Transaction findById(Integer id);

    @Query("SELECT id FROM transactionTable ORDER BY id DESC LIMIT 1")
    Integer getLastUsedId();

    @Query("UPDATE transactionTable SET categoryid = :categoryid, categoryName = :categoryName, type = :type, value = :value WHERE id = :id")
    void updateTransaction(Integer id, Integer categoryid, String categoryName, String type, Integer value);

    @Query("UPDATE transactionTable SET userid = :userid WHERE id = :id")
    void updateUserId(Integer id, String userid);

    @Insert
    void insertOne(Transaction transaction);

    @Insert
    void insertAll(List<Transaction> transactions);

    @Update
    void updateTransactions(List<Transaction> transactions);

    @Delete
    void delete(Transaction transaction);

    @Query("DELETE FROM transactionTable")
    void clearTable();
}