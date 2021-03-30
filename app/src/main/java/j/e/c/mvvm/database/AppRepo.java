package j.e.c.mvvm.database;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import j.e.c.mvvm.models.Note;
import j.e.c.mvvm.utilis.SampleDataProvider;

public class AppRepo {


 static AppRepo ourInstance;
static AppDataBase appDataBase;
    public LiveData<List<Note>> mnotes ;

    Executor mExceutor = Executors.newSingleThreadExecutor();
    public  static  AppRepo getInstance(Context context){
        return ourInstance = new AppRepo(context);
    }

    private AppRepo(Context context){

        appDataBase = AppDataBase.getInstance(context);
        mnotes  = appDataBase.noteDao().getAllNotes();

    }


    public static Note addNotes(int id) {

        return  appDataBase.noteDao().getNoteById(id);
    }

    public void aadsampleData() {
mExceutor.execute(() -> appDataBase.noteDao().insertAllNotes(SampleDataProvider.getSampleData()));
    }


    public void DeleteData() {
        mExceutor.execute(new Runnable() {
            @Override
            public void run() {

                appDataBase.noteDao().deleteAllNotes();
            }
        });


    }

    public LiveData<List<Note>> getNotes() {
        return mnotes;
    }

    public void updateNotes(Note note) {
        mExceutor.execute(new Runnable() {
            @Override
            public void run() {

                appDataBase.noteDao().insertNote(note);
            }

        });


    }

    public void DeleteOneNote(Note note) {

        mExceutor.execute(new Runnable() {
            @Override
            public void run() {

       appDataBase.noteDao().deleteNote(note);

            }
        });
    }
}
