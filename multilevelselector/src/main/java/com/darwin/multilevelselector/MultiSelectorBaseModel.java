package com.darwin.multilevelselector;

import java.util.ArrayList;
import java.util.List;

public class MultiSelectorBaseModel<T extends MultiSelectorBaseModel> {
    public String textForDisplay;
    public List<T> mSubList = new ArrayList<>();
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

    public List<T> getmSubList() {
        return mSubList;
    }

    public List<T> getmSubListOriginal() {
        List<T> mSubListRes = mSubList;
        for(MultiSelectorBaseModel msbm : mSubListRes)
        {
            msbm.setbIsSelected(false);
        }
        return mSubListRes;
    }

    public void setmSubList(List<T> mSubList) {
        this.mSubList = mSubList;
    }
}
