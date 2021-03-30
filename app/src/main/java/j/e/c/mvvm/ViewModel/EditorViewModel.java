package j.e.c.mvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import j.e.c.mvvm.database.AppRepo;
import j.e.c.mvvm.models.Note;

public class EditorViewModel extends AndroidViewModel {
    public   MutableLiveData<Note> mliveNotes = new MutableLiveData<>();
Executor executor  = Executors.newSingleThreadExecutor();



    AppRepo myrepo;
    public EditorViewModel(@NonNull Application application) {
        super(application);

        myrepo = AppRepo.getInstance(application.getApplicationContext());
    }

    public void EditNotest(final int id) {
executor.execute(new Runnable() {
    @Override
    public void run() {

        Note note = AppRepo.addNotes(id);
        mliveNotes.postValue(note);
    }
});

    }

    public void UpdateNotes(String updateNotes) {

        Note note = mliveNotes.getValue();

        if (note == null){

            note = new Note(new Date(), updateNotes);

        }else {
            note.setText(updateNotes);
            note.setDate(new Date());

        }
        myrepo.updateNotes(note);

    }
}
