package com.example.doctruyen.view.fragment;

import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.doctruyen.R;
import com.example.doctruyen.adapters.StoryViewPaperAdapter;
import com.example.doctruyen.viewmodel.HomeViewModel;

public class StoryDetailFragment extends BaseFragment<HomeViewModel> {
    private int storyIndex;
    private String pageNum;


    @Override
    protected Class<HomeViewModel> getViewModelClass() {
        return HomeViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cauchuyen_fragment;
    }

    @Override
    protected void initViews() {
        ViewPager viewPager = findViewById(R.id.view_paper);
        viewPager.setAdapter(new StoryViewPaperAdapter(mViewModel.getStoryListLiveData().getValue(), getActivity()));
        viewPager.setCurrentItem(storyIndex);
        TextView cateName = findViewById(R.id.cate_name);
        TextView storyNumber = findViewById(R.id.story_number);
        cateName.setText(mViewModel.getCurrentCategory().getValue());
        pageNum = Integer.toString(storyIndex + 1) + "/" + Integer.toString(mViewModel.getStoryListLiveData().getValue().size());
        storyNumber.setText(pageNum);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageNum = Integer.toString(position + 1) + "/" + Integer.toString(mViewModel.getStoryListLiveData().getValue().size() + 1);
                storyNumber.setText(pageNum);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setStoryIndex(int storyIndex) {
        this.storyIndex = storyIndex;
    }
}
