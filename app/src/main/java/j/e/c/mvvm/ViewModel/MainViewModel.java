package j.e.c.mvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import j.e.c.mvvm.database.AppRepo;
import j.e.c.mvvm.models.Note;

public class MainViewModel extends AndroidViewModel {

    public LiveData<List<Note>> mnotes ;
    private AppRepo myRepo;
    public MainViewModel(@NonNull Application application) {
        super(application);


        myRepo = AppRepo.getInstance(application.getApplicationContext());
        mnotes = myRepo.getNotes();
    }

    public void addSampleData() {

        myRepo.aadsampleData();

    }

    public void deleteAll() {

        myRepo.DeleteData();
    }

    public void deleteNote(Note note) {

        myRepo.DeleteOneNote(note);
    }
}
