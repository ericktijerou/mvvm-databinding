package erick.android.mvvm;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import erick.android.mvvm.data.FakeUserGeneratorAPI;
import erick.android.mvvm.data.RetrofitServiceInterface;
import erick.android.mvvm.data.model.User;
import erick.android.mvvm.databinding.ActivityMainBinding;
import erick.android.mvvm.ui.user.MainViewModel;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

/**
 * Created by erick on 9/5/17.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = Config.NONE)
public class UserViewModelTest {

    private static final String URL_TEST = "http://api.randomuser.me/?results=5";

    @Mock
    private RetrofitServiceInterface retrofitServiceInterface;

    private MainViewModel mainViewModel;

    @Mock private ActivityMainBinding mainActivityBinding;

    @Before
    public void setUpMainViewModelTest() {
        // inject the mocks
        MockitoAnnotations.initMocks(this);

        // Mock the PeopleService so we don't call the Random User Generator API (we are simulating only a call to the api)
        // and all observables will now run on the same thread
        MVVMApplication mvvmApplication = (MVVMApplication) RuntimeEnvironment.application;
        mvvmApplication.setRetrofitServiceInterface(retrofitServiceInterface);
        mvvmApplication.setScheduler(Schedulers.trampoline());

        mainViewModel = new MainViewModel(mvvmApplication);
    }

    @Test
    public void simulateGivenTheUserCallListFromApi() throws Exception {
        List<User> peoples = FakeUserGeneratorAPI.getUserList();
        doReturn(Observable.just(peoples)).when(retrofitServiceInterface).getUsers(URL_TEST);
    }

    @Test public void ensureTheViewsAreInitializedCorrectly() throws Exception {
        mainViewModel.initializeViews();
        assertEquals(View.GONE, mainViewModel.tvMessage.get());
        assertEquals(View.GONE, mainViewModel.userRecycler.get());
        assertEquals(View.VISIBLE, mainViewModel.userProgress.get());
    }
}

