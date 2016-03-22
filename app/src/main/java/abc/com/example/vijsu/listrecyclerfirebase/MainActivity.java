package abc.com.example.vijsu.listrecyclerfirebase;

import android.content.ClipData;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;
import com.firebase.ui.FirebaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AddItemDialogFragment.ItemAddedHandler {
    Firebase rootRef;
//    ListView listView;
    RecyclerView recyclerView;
    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    Firebase itemsRef;
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

             /*   Bundle b = new Bundle();
                b.getString("item");*/

            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        listView = (ListView)findViewById(R.id.listView);
        rootRef = new Firebase("https://listrecycler.firebaseio.com");
    }

    @Override
    protected void onStart() {
        super.onStart();
        //// TODO: 22-03-2016 uncomment to generate list without firebase
/*        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, items);
        listView.setAdapter(arrayAdapter);*/
        //-------//
        itemsRef = rootRef.child("items");
        FirebaseRecyclerAdapter<String,ItemViewHolder> recyclerAdapter = new FirebaseRecyclerAdapter<String, ItemViewHolder>(String.class,android.R.layout.two_line_list_item,ItemViewHolder.class,itemsRef) {
            @Override
            protected void populateViewHolder(ItemViewHolder itemViewHolder, String s, int i) {
                itemViewHolder.textView.setText(s);
            }
        };recyclerView.setAdapter(recyclerAdapter);

/*        FirebaseListAdapter<String> adapter = new FirebaseListAdapter<String>(this,String.class,android.R.layout.simple_list_item_1,itemsRef) {
            @Override
            protected void populateView(View view, String s, int i) {
                TextView textView = (TextView)view.findViewById(android.R.id.text1);
                textView.setText(s);
            }
        };
        listView.setAdapter(adapter);*/
    }
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(android.R.id.text1);
        }
    }

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
/*        items.add(item);
        arrayAdapter.notifyDataSetChanged();*/
        itemsRef.push().setValue(item);
        Toast.makeText(MainActivity.this, "Input Text is "+item, Toast.LENGTH_SHORT).show();
    }
}
