package abc.com.example.vijsu.listrecyclerfirebase;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddItemDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddItemDialogFragment extends AppCompatDialogFragment {


    EditText editText;


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
        return view;
    }

}
