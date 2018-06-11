package com.darwin.multilevelselector;

import java.util.ArrayList;
import java.util.List;

public class MultiSelectorBaseModel {
    String textForDisplay;
    List<MultiSelectorBaseModel> mSubList = new ArrayList<>();
    boolean bIsSelected;

    public boolean isbIsSelected() {
        return bIsSelected;
    }

    public void setbIsSelected(boolean bIsSelected) {
        this.bIsSelected = bIsSelected;
    }

    public String getTextForDisplay() {
        return textForDisplay;
    }

    public void setTextForDisplay(String textForDisplay) {
        this.textForDisplay = textForDisplay;
    }

    public List<MultiSelectorBaseModel> getmSubList() {
        return mSubList;
    }

    public List<MultiSelectorBaseModel> getmSubListOriginal() {
        List<MultiSelectorBaseModel> mSubListRes = mSubList;
        for(MultiSelectorBaseModel msbm : mSubListRes)
        {
            msbm.setbIsSelected(false);
        }
        return mSubListRes;
    }

    public void setmSubList(List<MultiSelectorBaseModel> mSubList) {
        this.mSubList = mSubList;
    }
}
