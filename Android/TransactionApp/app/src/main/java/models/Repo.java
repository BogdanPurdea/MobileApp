package models;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by Bogdan on 1/1/2018.
 */

public class Repo {

    static AppDatabase db;
    Context context;

    public Repo(Context context){
        this.context = context;
    }

    public void CreateDb(){
        db = Room.databaseBuilder(this.context.getApplicationContext(),
                AppDatabase.class, "database_1").allowMainThreadQueries().build();
    }
    public AppDatabase getDb(){
        return db;
    }
}
