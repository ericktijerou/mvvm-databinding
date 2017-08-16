package erick.android.mvvm.ui.user;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import erick.android.mvvm.R;
import erick.android.mvvm.data.model.User;
import erick.android.mvvm.databinding.ItemListUserBinding;

/**
 * Created by erick on 8/16/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainAdapterViewHolder> {

    private List<User> userList;

    public MainAdapter() {
        this.userList = Collections.emptyList();
    }

    @Override public MainAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListUserBinding itemListUserBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_list_user,
                        parent, false);
        return new MainAdapterViewHolder(itemListUserBinding);
    }

    @Override public void onBindViewHolder(MainAdapterViewHolder holder, int position) {
        holder.bindUser(userList.get(position));
    }

    @Override public int getItemCount() {
        return userList.size();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public static class MainAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemListUserBinding itemListUserBinding;

        public MainAdapterViewHolder(ItemListUserBinding itemListUserBinding) {
            super(itemListUserBinding.itemUser);
            this.itemListUserBinding = itemListUserBinding;
        }

        void bindUser(User user) {
            if (itemListUserBinding.getUserViewModel() == null) {
                itemListUserBinding.setUserViewModel(
                        new ItemUserViewModel(user, itemView.getContext()));
            } else {
                itemListUserBinding.getUserViewModel().setUser(user);
            }
        }
    }
}
