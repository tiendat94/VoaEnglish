package com.example.voaenglish;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.voaenglish.base.BaseActivity;
import com.example.voaenglish.fragment.ProjectFragment;
import com.example.voaenglish.fragment.ProjectListFragment;
import com.example.voaenglish.model.Note;
import com.example.voaenglish.model.Project;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import rx.Subscription;

public class MainActivity extends BaseActivity implements HasSupportFragmentInjector {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Disposable disposable;

    private Subscription subscription;

    @Inject
    DispatchingAndroidInjector dispatchingAndroidInjector;

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * @param savedInstanceState Simple Observable emitting multiple Notes
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Add project list fragment if this is first creation

        if (savedInstanceState == null) {
            ProjectListFragment fragment = new ProjectListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, ProjectListFragment.TAG).commit();
        }

        test();

        testObservable();

        Observable<Note> noteObservable = getNotesObservable();

        Observer<Note> noteObserver = getNotesObserver();

        noteObservable.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(noteObserver);

    }

    /**
     * @return shows the project detail fragment
     */

    public void show(Project project) {
        ProjectFragment projectFragment = ProjectFragment.forProject(project.name);

        getSupportFragmentManager().beginTransaction()
                .addToBackStack("project")
                .replace(R.id.fragment_container, projectFragment, null).commit();

    }

    private Observer<Note> getNotesObserver() {
        return new Observer<Note>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
                disposable = d;
            }

            @Override
            public void onNext(Note note) {
                Log.d(TAG, "onNext:" + note.getNote());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };
    }

    private Observable<Note> getNotesObservable() {
        final List<Note> notes = prepareNotes();

        return Observable.create(new ObservableOnSubscribe<Note>() {
            @Override
            public void subscribe(ObservableEmitter<Note> emitter) throws Exception {
                for (Note note : notes) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(note);
                    }
                }
                //all notes are emitted
                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
        disposable.dispose();
    }

    private List<Note> prepareNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1, "Buy tooth paste!"));
        notes.add(new Note(2, "Call brother!"));
        notes.add(new Note(3, "Watch Narcos tonight!"));
        notes.add(new Note(4, "Pay power bill!"));
        return notes;
    }

    private void testObservable() {

    }

    private void test() {
        Observable<Integer> observable = Observable.just(1, 2, 3);
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("Test", "In onNext()" + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Test", "In onError()");
            }

            @Override
            public void onComplete() {
                Log.d("Test", "In onComplete()");
            }
        });
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}