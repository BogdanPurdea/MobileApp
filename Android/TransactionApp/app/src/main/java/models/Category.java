package models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Bogdan on 12/31/2017.
 */
@Entity(tableName = "categoryTable")
public class Category {
    @PrimaryKey
    public Integer id;
    @ColumnInfo(name = "name")
    public String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return "Category  "+getId()+"\nName: "+getName();
    }
}
