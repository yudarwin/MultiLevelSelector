package com.darwin.multilevelselector;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MultiLevelSelectorUnlimitedTitleAdapter extends BaseQuickAdapter<UnlimitedTitleData, BaseViewHolder> {
    Context context;
    public MultiLevelSelectorUnlimitedTitleAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<UnlimitedTitleData> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, UnlimitedTitleData data) {
        viewHolder.setText(R.id.tv_level, data.getText())
                .setVisible(R.id.v_level, data.isbIsselected())
                .setTextColor(R.id.tv_level,data.isbIsselected()?context.getResources().getColor(R.color.title_bar):context.getResources().getColor(R.color.black))
                .addOnClickListener(R.id.rl_multi_level_selector_item_main);
    }
}
