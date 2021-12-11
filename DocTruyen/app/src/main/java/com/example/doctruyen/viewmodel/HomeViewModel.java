package com.example.doctruyen.viewmodel;

import android.content.res.AssetManager;

import androidx.lifecycle.MutableLiveData;

import com.example.doctruyen.model.Category;
import com.example.doctruyen.model.Story;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeViewModel extends BaseViewModel{
    private List<Category> categoryList = new ArrayList<>();
    private List<Story> stories = new ArrayList<>();
    private AssetManager assetManager;
    private MutableLiveData<String> currentCategory = new MutableLiveData<>();
    private MutableLiveData<List<Story>> storyListLiveData = new MutableLiveData<>();


    public void initCategory(){
        categoryList.clear();
        try {
            String[] files = assetManager.list("data");
            for (String file : files){
                String name = file.replace(".txt", "");
                String imageName = "icon/" + name + ".png";
                categoryList.add(new Category(name, imageName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initStory(String filePath) {
        stories.clear();
        StringBuilder text = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(assetManager.open("data/" + filePath)));
            String mLine;
            while ((mLine = reader.readLine()) != null){
                text.append(mLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String[] storyText = text.toString().split("','0'\\);\n");
        for (String t : storyText){
            String[] splitText = t.split("\n");
            stories.add((new Story(splitText[0].trim(),
                    String.join("\n", Arrays.copyOfRange(splitText, 1, splitText.length)))));
            storyListLiveData.postValue(stories);
        }
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void setCurrentCategory(String current) {
       currentCategory.postValue(current);
    }

    public MutableLiveData<String> getCurrentCategory() {
        return currentCategory;
    }

    public MutableLiveData<List<Story>> getStoryListLiveData() {
        return storyListLiveData;
    }

}
