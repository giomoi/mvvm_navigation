package jp.co.ienter.bottomnavigation.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import jp.co.ienter.bottomnavigation.R;
import jp.co.ienter.bottomnavigation.models.Word;
import jp.co.ienter.bottomnavigation.viewmodels.notifications.WordViewModel;

public class NewWordFragment extends Fragment {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    private WordViewModel wordViewModel;
    private EditText mEditWordView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_new_word, container, false);

        mEditWordView = root.findViewById(R.id.edit_word);

        root.findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordViewModel= ViewModelProviders.of(getActivity()).get(WordViewModel.class);
                Word word = new Word(mEditWordView.getText().toString());
                wordViewModel.insert(word);
                Toast.makeText(getContext(),"Ok",Toast.LENGTH_SHORT).show();
                Navigation.findNavController(v).navigate(R.id.action_navigation_new_word_to_navigation_notifications);
//                NavHostFragment.findNavController(NewWordFragment.this).navigate(R.id.navigation_finish);
            }
        });


        return root;
    }
}
