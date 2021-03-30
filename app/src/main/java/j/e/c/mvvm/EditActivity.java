package j.e.c.mvvm;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import j.e.c.mvvm.ViewModel.EditorViewModel;
import j.e.c.mvvm.models.Note;

public class EditActivity extends AppCompatActivity {

    EditorViewModel editorViewModel;
    @BindView(R.id.edit1)
    EditText edit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
        initViewModel();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
       // toolBarLayout.setTitle(getTitle());


    }

    private void initViewModel() {

      /*  Observer<Note> myotes = new Observer<Note>() {
            @Override
            public void onChanged(Note note) {
                if (note != null) {
                    edit1.setText(note.getText().toString());

                }
            }
        };*/
        editorViewModel = ViewModelProviders.of(EditActivity.this).get(EditorViewModel.class);
        editorViewModel.mliveNotes.observe(EditActivity.this, note -> {
            if (note != null) {
                edit1.setText(note.getText().toString());

            }
        });

        Bundle bundle = getIntent().getExtras();

        if (bundle == null){
            setTitle("Edit Notes");

        }else{
            setTitle("EditNotes");

            int id = bundle.getInt("Note_Id");
            editorViewModel.EditNotest(id);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        editorViewModel.UpdateNotes(edit1.getText().toString());
    }
}