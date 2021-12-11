package com.example.doctruyen.view.fragment;

import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doctruyen.Constants;
import com.example.doctruyen.R;
import com.example.doctruyen.adapters.StoryAdapter;
import com.example.doctruyen.listeners.RecycleViewListener;
import com.example.doctruyen.model.Category;
import com.example.doctruyen.model.Story;
import com.example.doctruyen.viewmodel.HomeViewModel;


public class HomeFragment extends BaseFragment<HomeViewModel>{

    private RecyclerView recyclerView;
    private StoryAdapter storyAdapter;

    @Override
    protected Class<HomeViewModel> getViewModelClass() {
        return HomeViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.manhinhchinh_fragment;
    }

    @Override
    protected void initViews() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        findViewById(R.id.bt_toggle, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        LinearLayout categoryList = findViewById(R.id.list_category);
        categoryList.removeAllViews();
        mViewModel.setAssetManager(getActivity().getAssets());
        mViewModel.initCategory();
        initRecycleView();
        for (Category category : mViewModel.getCategoryList()){
            View v = initCategoryItem(category);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewModel.initStory(category.getName() + ".txt");
                    mViewModel.setCurrentCategory(category.getName());
                    storyAdapter.notifyDataSetChanged();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            drawerLayout.closeDrawers();
                        }
                    }, 150);
                }
            });
            categoryList.addView(v);
        }
    }

    private View initCategoryItem(Category item) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.nav_item, null, false);
        ImageView categoryImg = view.findViewById(R.id.category_img);
        TextView categoryText = view.findViewById(R.id.category_text);
        categoryText.setText(item.getName());
        String iconPath = "file:///android_asset/" + item.getIcon();
        Glide.with(this).load(Uri.parse(iconPath)).into(categoryImg);
        return view;
    }

    private void initRecycleView(){
        recyclerView = findViewById(R.id.recycle_view_story);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        storyAdapter = new StoryAdapter(mViewModel.getStories());
        storyAdapter.setRecycleViewListener(new RecycleViewListener() {
            @Override
            public void onClick(Story story) {
                gotoStoryDetail(mViewModel.getStories().indexOf(story));
            }
        });
        recyclerView.setAdapter(storyAdapter);
    }

    private void gotoStoryDetail(int storyIdx){
        callBack.callBack(Constants.KEY_SHOW_STORY_DETAIL, storyIdx);
    }

}
