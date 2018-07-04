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

public class Multi_Level_Selector<T extends MultiSelectorBaseModel> extends PopupWindow implements View.OnClickListener{

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
    private TextView tv_multi_level_selector_title, tv_level_1, tv_level_2, tv_level_3, tv_level_4, tv_level_5;
    private RelativeLayout rl_level_1, rl_level_2, rl_level_3, rl_level_4, rl_level_5;
    private View v_level_1, v_level_2, v_level_3, v_level_4, v_level_5;
    private RecyclerView recycleview_multi_level_selector;
    private int CurrentLevel = 0;
//    private List<MultiSelectorBaseModel> CurrentShowList = new ArrayList<>();
    private List<TextView> mTextViewList = new ArrayList<>();
    private List<View> mViewList = new ArrayList<>();
    private List<RelativeLayout> mRelativeLayoutList = new ArrayList<>();
    private MultiLevelSelectorAdapter[] mAdapterList = new MultiLevelSelectorAdapter[MAX_INDEX_TABS];
    private MultiLevelSelectorAdapter mAdapter;
    private MultiLevelSelectorAdapter mAdapter1;
    private MultiLevelSelectorAdapter mAdapter2;
    private MultiLevelSelectorAdapter mAdapter3;
    private MultiLevelSelectorAdapter mAdapter4;

    public Multi_Level_Selector(Context context, String title, List<T> list, MultiLevelSelectorCallBack callBack) {
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
        mContentView = LayoutInflater.from(context).inflate(R.layout.multi_level_selector, null);
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
        mAdapterList[0]=mAdapter;
        mAdapterList[1]=mAdapter1;
        mAdapterList[2]=mAdapter2;
        mAdapterList[3]=mAdapter3;
        mAdapterList[4]=mAdapter4;
    }

    public void initView(View view) {
        tv_multi_level_selector_title = (TextView) view.findViewById(R.id.tv_multi_level_selector_title);
        tv_multi_level_selector_title.setText(title);

        view.findViewById(R.id.iv_multi_level_selector_ok).setOnClickListener(this);

        tv_level_1 = (TextView) view.findViewById(R.id.tv_level_1);
        tv_level_2 = (TextView) view.findViewById(R.id.tv_level_2);
        tv_level_3 = (TextView) view.findViewById(R.id.tv_level_3);
        tv_level_4 = (TextView) view.findViewById(R.id.tv_level_4);
        tv_level_5 = (TextView) view.findViewById(R.id.tv_level_5);
        mTextViewList.add(tv_level_1);
        mTextViewList.add(tv_level_2);
        mTextViewList.add(tv_level_3);
        mTextViewList.add(tv_level_4);
        mTextViewList.add(tv_level_5);

        rl_level_1 = (RelativeLayout) view.findViewById(R.id.rl_level_1);
        rl_level_2 = (RelativeLayout) view.findViewById(R.id.rl_level_2);
        rl_level_3 = (RelativeLayout) view.findViewById(R.id.rl_level_3);
        rl_level_4 = (RelativeLayout) view.findViewById(R.id.rl_level_4);
        rl_level_5 = (RelativeLayout) view.findViewById(R.id.rl_level_5);
        mRelativeLayoutList.add(rl_level_1);
        mRelativeLayoutList.add(rl_level_2);
        mRelativeLayoutList.add(rl_level_3);
        mRelativeLayoutList.add(rl_level_4);
        mRelativeLayoutList.add(rl_level_5);

        rl_level_1.setOnClickListener(this);
        rl_level_2.setOnClickListener(this);
        rl_level_3.setOnClickListener(this);
        rl_level_4.setOnClickListener(this);
        rl_level_5.setOnClickListener(this);

        v_level_1 = (View) view.findViewById(R.id.v_level_1);
        v_level_2 = (View) view.findViewById(R.id.v_level_2);
        v_level_3 = (View) view.findViewById(R.id.v_level_3);
        v_level_4 = (View) view.findViewById(R.id.v_level_4);
        v_level_5 = (View) view.findViewById(R.id.v_level_5);
        mViewList.add(v_level_1);
        mViewList.add(v_level_2);
        mViewList.add(v_level_3);
        mViewList.add(v_level_4);
        mViewList.add(v_level_5);

        recycleview_multi_level_selector = (RecyclerView) view.findViewById(R.id.recycleview_multi_level_selector);
        recycleview_multi_level_selector.setLayoutManager(new LinearLayoutManager(context));
    }

    public void initData() {
        CurrentLevel = 0;
        tv_level_1.setText("请选择");
        list_multi_for_display.add(list);
//        CurrentShowList = list;
        setClickAble( 0);
        showLevelView(0);
    }

    public void showLevelBottomView(int level) {
        for (int i = 0;i<MAX_INDEX_TABS;i++) {
            if(i == level) {
                mViewList.get(i).setVisibility(View.VISIBLE);
                mTextViewList.get(i).setTextColor(context.getResources().getColor(R.color.title_bar));
            }
            else {
                mViewList.get(i).setVisibility(View.GONE);
                mTextViewList.get(i).setTextColor(context.getResources().getColor(R.color.black));
            }
        }
    }

    public void setClickAble(int index) {
        for(int i =0;i<mRelativeLayoutList.size();i++) {
            if(i<=index) {
                mRelativeLayoutList.get(i).setClickable(true);
            } else {
                mRelativeLayoutList.get(i).setClickable(false);
            }
        }
    }

    public void ClearSubViews(int index) {
        for(int i = MAX_INDEX_TABS-1 ;i>=0;i--) {
            if(i == index+1) {
                break;
            }
            mTextViewList.get(i).setText("");
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

    public void showLevelView(final int index) {
        CurrentLevel = index;
        showLevelBottomView(index);
        mAdapterList[index] = new MultiLevelSelectorAdapter(context,R.layout.multi_level_selector_list_item, list_multi_for_display.get(index));
        recycleview_multi_level_selector.setAdapter(mAdapterList[index]);
        mAdapterList[index].setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
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
                mAdapterList[index].notifyDataSetChanged();
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
                        mTextViewList.get(index).setText(list_multi_for_display.get(index).get(list_multi_for_display_index.get(index)).getTextForDisplay());
                        mTextViewList.get(index + 1).setText("请选择");
                        showLevelView(index + 1);
                        setClickAble( index+1);
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
        if(id == R.id.rl_level_1) {
            showLevelView(0);
        } else if (id == R.id.rl_level_2){
            showLevelView(1);
        } else if (id == R.id.rl_level_3){
            showLevelView(2);
        } else if (id == R.id.rl_level_4){
            showLevelView(3);
        } else if (id == R.id.rl_level_5){
            showLevelView(4);
        } else if (id == R.id.iv_multi_level_selector_ok){
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
