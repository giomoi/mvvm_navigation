package jp.co.ienter.bottomnavigation.viewmodels.notifications;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import jp.co.ienter.bottomnavigation.models.Word;
import jp.co.ienter.bottomnavigation.models.repository.WordRepository;

public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepository;
    private final MutableLiveData<Word> selected = new MutableLiveData<Word>();

    private LiveData<List<Word>> mAllWords;

    public WordViewModel(@NonNull Application application) {
        super(application);

        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() { return mAllWords; }

    public void insert(Word word) { mRepository.insert(word); }

    public void select(Word item) {
        selected.setValue(item);
    }

    public LiveData<Word> getSelected() {
        return selected;
    }

}
