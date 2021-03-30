package j.e.c.mvvm.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import j.e.c.mvvm.models.Note;


@Database(entities = {Note.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDataBase extends RoomDatabase {
    static final String DATABASE_NAME = "notesdatabase.db";
    static volatile AppDataBase instance;
    static final Object LOCK = new Object();

    //instance of all daos
    abstract NoteDao noteDao();

    public static AppDataBase getInstance(Context context) {
        if (instance == null){
            synchronized (LOCK){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class,
                            DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
