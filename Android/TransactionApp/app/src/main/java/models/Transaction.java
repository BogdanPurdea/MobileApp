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
    @ColumnInfo(name = "categoryid")
    public Integer categoryid;
    @ColumnInfo(name = "categoryName")
    public String categoryName;
    @ColumnInfo(name = "type")
    public String type;
    @ColumnInfo(name = "value")
    public Integer value;
    @ColumnInfo(name = "userid")
    public Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryid;
    }

    public void setCategoryId(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    @Override
    public String toString(){
        return "Transaction "+getId()+"\nCategory: "+getCategoryName()+"\nType: "+getType()+"\nValue: "+getValue();
    }
}
