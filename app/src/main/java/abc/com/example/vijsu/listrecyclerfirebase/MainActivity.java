package abc.com.example.vijsu.listrecyclerfirebase;

import android.content.ClipData;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemAddedHandler{
    Firebase rootRef;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDialogFragment newFragment = new AddItemDialogFragment();
                newFragment.show(getSupportFragmentManager(),"Add Item");

            }
        });
        listView = (ListView)findViewById(R.id.listView);
        rootRef = new Firebase("https://listrecycler.firebaseio.com");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Firebase itemsRef = rootRef.child("items");
        FirebaseListAdapter<String> adapter = new FirebaseListAdapter<String>(this,String.class,android.R.layout.simple_list_item_1,itemsRef) {
            @Override
            protected void populateView(View view, String s, int i) {
                TextView textView = (TextView)view.findViewById(android.R.id.text1);
                textView.setText(s);
            }
        };
        listView.setAdapter(adapter);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemAdded(String item) {
        rootRef.push().setValue(item);
    }
}
