package j.e.c.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import j.e.c.mvvm.ViewModel.MainViewModel;
import j.e.c.mvvm.adapters.MainAdapter;
import j.e.c.mvvm.models.Note;
import j.e.c.mvvm.utilis.SampleDataProvider;

public class MainActivity extends AppCompatActivity {
    public MainViewModel mainViewModel;
private List<Note>  mNoteLisit = new ArrayList<>();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
        ButterKnife.bind(this);

        initRecyelerview();



        //showData();
        toolbar.setTitle("rehan");
    }


    private void initViewModel() {

        Observer<List<Note>> notesObserver = notes -> {
            mNoteLisit.clear();
            mNoteLisit.addAll(notes);

            if (mainAdapter == null){
                mainAdapter = new MainAdapter(mNoteLisit, MainActivity.this);
                recyclerview.setAdapter(mainAdapter);
            }else {
                mainAdapter.notifyDataSetChanged();
            }
        };

        mainViewModel = ViewModelProviders.of(MainActivity.this).get(MainViewModel.class);
        mainViewModel.mnotes.observe(this, notesObserver);
    }



    private void initRecyelerview() {

       recyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        //  Toast.makeText(this,"ok",Toast.LENGTH_LONG).show();
        startActivity(new Intent(MainActivity.this, EditActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
          mainViewModel.addSampleData();


            return true;

        }
        if (id == R.id.action_delete) {
            mainViewModel.deleteAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}