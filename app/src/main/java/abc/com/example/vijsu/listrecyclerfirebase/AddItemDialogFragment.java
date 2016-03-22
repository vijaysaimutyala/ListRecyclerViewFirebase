package abc.com.example.vijsu.listrecyclerfirebase;


import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddItemDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddItemDialogFragment extends AppCompatDialogFragment implements TextView.OnEditorActionListener {


    EditText editText;

    public interface ItemAddedHandler {
        void onItemAdded(String item);
    }


    public AddItemDialogFragment() {
        // Required empty public constructor
    }

    public static AddItemDialogFragment newInstance() {
        AddItemDialogFragment fragment = new AddItemDialogFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_item_dialog,container,false);
        Button button = (Button)view.findViewById(R.id.ok);
        editText = (EditText)view.findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "clicked OK", Toast.LENGTH_SHORT).show();
                String s = editText.getText().toString();

            }
        });
        // Show soft keyboard automatically
        editText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        editText.setOnEditorActionListener(this);
        return view;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId){
            //Return text to activity
            Toast.makeText(this.getContext(), "Done Clicked.", Toast.LENGTH_SHORT).show();
            ItemAddedHandler activity = (ItemAddedHandler)getActivity();
            activity.onItemAdded(editText.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
    }
}
