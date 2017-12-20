package models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Bogdan on 12/20/2017.
 */
@Entity(tableName = "transactionTable")
public class Transaction {
    @PrimaryKey
    public Integer id;
    @ColumnInfo(name = "category")
    public String category;
    @ColumnInfo(name = "type")
    public String type;
    @ColumnInfo(name = "value")
    public Integer value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return "Transaction "+getId()+"\nCategory: "+getCategory()+"\nType: "+getType()+"\nValue: "+getValue();
    }
}
