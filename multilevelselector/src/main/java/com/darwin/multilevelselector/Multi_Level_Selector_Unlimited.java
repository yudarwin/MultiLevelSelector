package com.darwin.multilevelselector;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class Multi_Level_Selector_Unlimited<T extends MultiSelectorBaseModel> extends PopupWindow implements View.OnClickListener{

    private static final int MAX_INDEX_TABS = 5;

    private final Context context;
    private final String title;
    private final MultiLevelSelectorCallBack callBack;
    private List<T> list = new ArrayList<>();
    private List<List<T>> list_multi_for_display = new ArrayList<>();
    private List<Integer> list_multi_for_display_index = new ArrayList<>();
    private int mWidth;
    private int mHeight;
    private View mContentView;
    private TextView tv_multi_level_selector_title;
    private RecyclerView recycleview_multi_level_selector;
    private int CurrentLevel = 0;
    private List<UnlimitedTitleData> mTitleData = new ArrayList<>();
    private MultiLevelSelectorAdapter mAdapter;
    private MultiLevelSelectorAdapter mAdapter1;
    private MultiLevelSelectorAdapter mAdapter2;
    private MultiLevelSelectorAdapter mAdapter3;
    private MultiLevelSelectorAdapter mAdapter4;
    private RecyclerView recycleview_multi_level_selector_title;
    private MultiLevelSelectorUnlimitedTitleAdapter mTitleAdapter;

    public Multi_Level_Selector_Unlimited(Context context, String title, List<T> list, MultiLevelSelectorCallBack callBack) {
        super(context);
        this.context = context;
        this.list = list;
        this.title = title;
        this.callBack = callBack;
        init();
        initData();
    }

    public void init() {
        calWidthAndHeight(context);
        setWidth(mWidth);
        setHeight(mHeight);
        mContentView = LayoutInflater.from(context).inflate(R.layout.multi_level_selector_unlimited, null);
        //设置布局与相关属性
        setContentView(mContentView);
        setBackgroundDrawable(null);
        setFocusable(true);
        setTouchable(true);
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //点击PopupWindow以外区域时PopupWindow消失
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    dismiss();
                }
                return false;
            }
        });
        initView(mContentView);
    }

    public void initView(View view) {
        tv_multi_level_selector_title = (TextView) view.findViewById(R.id.tv_multi_level_selector_title);
        tv_multi_level_selector_title.setText(title);

        view.findViewById(R.id.iv_multi_level_selector_ok).setOnClickListener(this);

        recycleview_multi_level_selector_title = (RecyclerView) view.findViewById(R.id.recycleview_multi_level_selector_title);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview_multi_level_selector_title.setLayoutManager(llm);

        recycleview_multi_level_selector = (RecyclerView) view.findViewById(R.id.recycleview_multi_level_selector);
        recycleview_multi_level_selector.setLayoutManager(new LinearLayoutManager(context));
    }

    public void initData() {
        CurrentLevel = 0;
        UnlimitedTitleData utd = new UnlimitedTitleData();
        utd.setbIsselected(true);
        utd.setText("请选择");
        mTitleData.add(utd);
        list_multi_for_display.add(list);

        showLevelView(0);
    }

    public void showLevelBottomView(int level) {
        for (int i = 0;i<mTitleData.size();i++) {
            if(i == level) {
                mTitleData.get(i).setbIsselected(true);
            }
            else {
                mTitleData.get(i).setbIsselected(false);
            }
        }
    }

    public void ClearSubViews(int index) {
        for(int i = MAX_INDEX_TABS-1 ;i>=0;i--) {
            if(i == index+1) {
                break;
            }
//            mTextViewList.get(i).setText("");
        }

        while (list_multi_for_display.size()-1 > index) {
            list_multi_for_display.remove(list_multi_for_display.size()-1);
        }
        while (list_multi_for_display_index.size()-1 > index) {
            list_multi_for_display_index.remove(list_multi_for_display_index.size()-1);
        }
    }

    public void setSelect(List<T> list,int position) {
        for (int i = 0; i <list.size();i++) {
            if(i == position) {
                list.get(i).setbIsSelected(true);
            } else {
                list.get(i).setbIsSelected(false);
            }
        }
    }

    public void showTitleView()
    {
        mTitleAdapter = new MultiLevelSelectorUnlimitedTitleAdapter(context,R.layout.multi_level_selector_unlimited_title_list_item, mTitleData);
        recycleview_multi_level_selector_title.setAdapter(mTitleAdapter);
        mTitleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showLevelView(position);
            }
        });
    }

    public void showLevelView(final int index) {
        CurrentLevel = index;
        showLevelBottomView(index);
        showTitleView();
        mAdapter = new MultiLevelSelectorAdapter(context,R.layout.multi_level_selector_list_item, list_multi_for_display.get(index));
        recycleview_multi_level_selector.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if(list_multi_for_display_index.size() <= index) {
                    list_multi_for_display_index.add(position);
                } else {
//                    if(list_multi_for_display_index.get(index) != position) {
                        ClearSubViews(index);
//                    }
                    if(list_multi_for_display_index.size() <= index) {
                        list_multi_for_display_index.add(position);
                    } else {
                        list_multi_for_display_index.set(index, position);
                    }
                }
                setSelect(list_multi_for_display.get(index),position);
                mAdapter.notifyDataSetChanged();
                if(list_multi_for_display.get(index).get(position).getmSubList().size()<=index) {
                    String StrRes = "";
                    for(int i = 0;i<=index;i++) {
                        if(i != 0) {
                            StrRes+=" ";
                        }
                        StrRes +=list_multi_for_display.get(i).get(list_multi_for_display_index.get(i)).getTextForDisplay();
                    }
                    callBack.success(StrRes,list_multi_for_display.get(index).get(list_multi_for_display_index.get(index)));
                    dismiss();
                } else {
                    if(index == MAX_INDEX_TABS-1) {

                    } else {
                        list_multi_for_display.add(list_multi_for_display.get(index).get(position).getmSubListOriginal());
                        UnlimitedTitleData utd = new UnlimitedTitleData();
                        utd.setbIsselected(true);
                        utd.setText(list_multi_for_display.get(index).get(list_multi_for_display_index.get(index)).getTextForDisplay());
                        mTitleData.add(mTitleData.size()-1,utd);
//                        mTextViewList.get(index).setText(list_multi_for_display.get(index).get(list_multi_for_display_index.get(index)).getTextForDisplay());
//                        mTextViewList.get(index + 1).setText("请选择");
                        showLevelView(index + 1);
//                        setClickAble( index+1);
                    }
                }
            }
        });
    }

    /**
     * 设置PopupWindow的大小
     * @param context
     */
    private void calWidthAndHeight(Context context) {
        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics= new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        mWidth=metrics.widthPixels;
        //设置高度为全屏高度的70%
        mHeight= (int) (metrics.heightPixels*0.7);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        lighton();
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        lightoff();
        super.showAtLocation(parent, gravity, x, y);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        lightoff();
        super.showAsDropDown(anchor,xoff,yoff,gravity);
    }

    private void lightoff() {
        WindowManager.LayoutParams lp=((Activity)context).getWindow().getAttributes();
        lp.alpha=0.5f;
        ((Activity)context).getWindow().setAttributes(lp);
    }

    private void lighton() {
        WindowManager.LayoutParams lp=((Activity)context).getWindow().getAttributes();
        lp.alpha=1.0f;
        ((Activity)context).getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        if(list.size() <= 0)
        {
            return;
        }
        int id = v.getId();
//        if(id == R.id.rl_level_1) {
//            showLevelView(0);
//        } else if (id == R.id.rl_level_2){
//            showLevelView(1);
//        } else if (id == R.id.rl_level_3){
//            showLevelView(2);
//        } else if (id == R.id.rl_level_4){
//            showLevelView(3);
//        } else if (id == R.id.rl_level_5){
//            showLevelView(4);
//        } else
            if (id == R.id.iv_multi_level_selector_ok){
            String StrRes = "";
            for(int i = 0;i<=CurrentLevel;i++) {
                if(i != 0) {
                    StrRes+=" ";
                }
                try {
                    StrRes += list_multi_for_display.get(i).get(list_multi_for_display_index.get(i)).getTextForDisplay();
                }catch (Exception e) {
                }
            }
            try {
                callBack.success(StrRes,list_multi_for_display.get(CurrentLevel).get(list_multi_for_display_index.get(CurrentLevel)));
            }
            catch (Exception e)
            {
                //以防出现当前CurrentLevel没有选择的情况返回上一个节点数据
                callBack.success(StrRes,list_multi_for_display.get(CurrentLevel-1).get(list_multi_for_display_index.get(CurrentLevel-1)));
            }

            dismiss();
        }
    }
}
