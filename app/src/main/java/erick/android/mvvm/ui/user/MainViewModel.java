package erick.android.mvvm.ui.user;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import erick.android.mvvm.MVVMApplication;
import erick.android.mvvm.R;
import erick.android.mvvm.data.ApiClientRetrofit;
import erick.android.mvvm.data.RetrofitServiceInterface;
import erick.android.mvvm.data.model.BaseResponse;
import erick.android.mvvm.data.model.User;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by erick on 8/16/17.
 */

public class MainViewModel extends Observable {

    public ObservableInt userProgress;
    public ObservableInt userRecycler;
    public ObservableInt tvMessage;
    public ObservableInt ivArrow;
    public ObservableField<String> textMessage;

    private List<User> userList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainViewModel(@NonNull Context context) {
        this.context = context;
        this.userList = new ArrayList<>();
        userProgress = new ObservableInt(View.GONE);
        userRecycler = new ObservableInt(View.GONE);
        tvMessage = new ObservableInt(View.VISIBLE);
        textMessage = new ObservableField<>(context.getString(R.string.default_loading));
        ivArrow = new ObservableInt(View.VISIBLE);
    }

    public void onClickFabLoad(View view) {
        initializeViews();
        fetchUserList();
    }

    //It is "public" to show an example of test
    public void initializeViews() {
        tvMessage.set(View.GONE);
        ivArrow.set(View.GONE);
        userRecycler.set(View.GONE);
        userProgress.set(View.VISIBLE);
    }

    private void fetchUserList() {

        MVVMApplication mvvmApplication = MVVMApplication.create(context);
        RetrofitServiceInterface retrofitServiceInterface = mvvmApplication.getRetrofitServiceInterface();

        Disposable disposable = retrofitServiceInterface.getUsers(ApiClientRetrofit.BASE_URL)
                .subscribeOn(mvvmApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<List<User>>>() {
                    @Override public void accept(BaseResponse<List<User>> userResponse) throws Exception {
                        changeUsersDataSet(userResponse.getResults());
                        userProgress.set(View.GONE);
                        tvMessage.set(View.GONE);
                        ivArrow.set(View.GONE);
                        userRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override public void accept(Throwable throwable) throws Exception {
                        textMessage.set(context.getString(R.string.error_loading));
                        userProgress.set(View.GONE);
                        tvMessage.set(View.VISIBLE);
                        ivArrow.set(View.VISIBLE);
                        userRecycler.set(View.GONE);
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void changeUsersDataSet(List<User> users) {
        userList.addAll(users);
        setChanged();
        notifyObservers();
    }

    public List<User> getUserList() {
        return userList;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
}
