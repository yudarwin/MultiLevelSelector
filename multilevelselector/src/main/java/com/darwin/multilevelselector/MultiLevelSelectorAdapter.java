package com.darwin.multilevelselector;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MultiLevelSelectorAdapter extends BaseQuickAdapter<MultiSelectorBaseModel, BaseViewHolder> {
    Context context;
    public MultiLevelSelectorAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<MultiSelectorBaseModel> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, MultiSelectorBaseModel data) {
        viewHolder.setText(R.id.tv_multi_level_selector_list_item, data.getTextForDisplay())
                .setVisible(R.id.iv_multi_level_selector_list_item, data.isbIsSelected())
                .setTextColor(R.id.tv_multi_level_selector_list_item,data.isbIsSelected()?context.getResources().getColor(R.color.title_bar):context.getResources().getColor(R.color.black))
                .addOnClickListener(R.id.rl_multi_level_selector_item_main);
    }
}
