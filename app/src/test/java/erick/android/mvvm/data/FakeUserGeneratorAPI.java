package erick.android.mvvm.data;

import java.util.ArrayList;
import java.util.List;

import erick.android.mvvm.data.model.Name;
import erick.android.mvvm.data.model.Picture;
import erick.android.mvvm.data.model.User;

/**
 * Created by erick on 9/5/17.
 */

public class FakeUserGeneratorAPI {
    private static final String USER_MAIL_TEST = "kazim.kuijper@example.com";
    private static final String USER_PICTURE_TEST =
            "https://randomuser.me/api/portraits/med/men/41.jpg";
    private static final String USER_NAME_TEST = "kazim";

    public static List<User> getUserList() {
        List<User> peoples = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            peoples.add(getUser());
        }
        return peoples;
    }

    public static User getUser() {
        User user = new User();
        Name name = new Name();
        Picture picture = new Picture();
        name.setFirst(USER_NAME_TEST);
        picture.setMedium(USER_PICTURE_TEST);
        user.setEmail(USER_MAIL_TEST);
        user.setName(name);
        user.setPicture(picture);
        return user;
    }
}
