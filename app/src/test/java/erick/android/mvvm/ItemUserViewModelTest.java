package erick.android.mvvm;

import android.databinding.Observable;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import erick.android.mvvm.data.model.Name;
import erick.android.mvvm.data.model.Picture;
import erick.android.mvvm.data.model.User;
import erick.android.mvvm.ui.user.ItemUserViewModel;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by erick on 9/5/17.
 */

@RunWith(RobolectricGradleTestRunner.class) @Config(constants = BuildConfig.class, sdk = 21)
public class ItemUserViewModelTest {

    private static final String USER_MAIL_TEST = "kazim.kuijper@example.com";
    private static final String USER_PICTURE_TEST =
            "https://randomuser.me/api/portraits/med/men/41.jpg";
    private static final String USER_NAME_TEST = "kazim";

    private MVVMApplication peopleApplication;

    @Before
    public void setUpItemUserModelTest() {
        peopleApplication = (MVVMApplication) RuntimeEnvironment.application;
    }

    @Test public void shouldGetUserMail() throws Exception {
        User user = new User();
        user.setEmail(USER_MAIL_TEST);
        ItemUserViewModel itemUserViewModel = new ItemUserViewModel(user, peopleApplication);
        assertEquals(user.getEmail(), itemUserViewModel.getMail());
    }

    @Ignore
    public void shouldGetUserPicture() throws Exception {
        User user = new User();
        Picture picture = new Picture();
        picture.setMedium(USER_PICTURE_TEST);
        user.setPicture(picture);
        ItemUserViewModel itemUserViewModel = new ItemUserViewModel(user, peopleApplication);
        assertEquals(user.getPicture().getMedium(), itemUserViewModel.getPictureProfile());
    }

    @Test public void shouldGetUserName() throws Exception {
        User user = new User();
        Name name = new Name();
        name.setFirst(USER_NAME_TEST);
        user.setName(name);
        ItemUserViewModel itemUserViewModel = new ItemUserViewModel(user, peopleApplication);
        assertEquals(user.getName().getFirst(), itemUserViewModel.getName());
    }

    @Test public void shouldNotifyPropertyChangeWhenSetUser() throws Exception {
        User user = new User();
        ItemUserViewModel itemUserViewModel = new ItemUserViewModel(user, peopleApplication);
        Observable.OnPropertyChangedCallback mockCallback =
                mock(Observable.OnPropertyChangedCallback.class);
        itemUserViewModel.addOnPropertyChangedCallback(mockCallback);
        itemUserViewModel.setUser(user);
        verify(mockCallback).onPropertyChanged(any(Observable.class), anyInt());
    }
}

