package erick.android.mvvm.ui.user;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import erick.android.mvvm.data.model.User;

/**
 * Created by erick on 8/16/17.
 */

public class ItemUserViewModel extends BaseObservable {

    private User user;
    private Context context;

    public ItemUserViewModel(User user, Context context) {
        this.user = user;
        this.context = context;
    }

    public String getName() {
        return user.getName().getFirst();
    }

    public String getMail() {
        return user.getEmail();
    }

    public String getPictureProfile() {
        return user.getPicture().getMedium();
    }

    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void setUser(User user) {
        this.user = user;
        notifyChange();
    }

    public void onItemClick(View view) {

    }
}
