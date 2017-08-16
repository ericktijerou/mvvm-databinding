package erick.android.mvvm.ui.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Observable;
import java.util.Observer;

import erick.android.mvvm.R;
import erick.android.mvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements Observer {

    private ActivityMainBinding mainActivityBinding;
    private MainViewModel mainViewModel;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setSupportActionBar(mainActivityBinding.toolbar);
        setupListUserView(mainActivityBinding.rvUsers);
        setupObserver(mainViewModel);
    }

    private void initDataBinding() {
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        mainActivityBinding.setMainViewModel(mainViewModel);
    }

    private void setupListUserView(RecyclerView listUsers) {
        MainAdapter adapter = new MainAdapter();
        listUsers.setAdapter(adapter);
        listUsers.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        mainViewModel.reset();
    }

    @Override public void update(Observable observable, Object data) {
        if (observable instanceof MainViewModel) {
            MainAdapter mainAdapter = (MainAdapter) mainActivityBinding.rvUsers.getAdapter();
            MainViewModel mainViewModel = (MainViewModel) observable;
            mainAdapter.setUserList(mainViewModel.getUserList());
        }
    }
}
