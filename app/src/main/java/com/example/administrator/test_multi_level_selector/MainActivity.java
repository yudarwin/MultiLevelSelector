package com.example.administrator.test_multi_level_selector;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.darwin.multilevelselector.MultiLevelSelectorCallBack;
import com.darwin.multilevelselector.MultiSelectorBaseModel;
import com.darwin.multilevelselector.Multi_Level_Selector;
import com.darwin.multilevelselector.Multi_Level_Selector_Unlimited;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv_depart;
    private Multi_Level_Selector_Unlimited mls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initPopupWindow();
        tv_depart = (TextView)findViewById(R.id.tv_depart);
        tv_depart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mls.showAtLocation(tv_depart, Gravity.BOTTOM, 0, 0);
            }
        });

    }
    private List<MultiSelectorBaseModel> list = new ArrayList<>();
    public void initData()
    {
        {
            List<MultiSelectorBaseModel> listTemp2 = new ArrayList<>();
            {
                List<MultiSelectorBaseModel> listTemp = new ArrayList<>();
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("场长办公室");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("副场长办公室");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("总工办公室");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("工程部");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("物机部");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("实验室");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("安质部");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("综合办");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("财务部");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("劳务部");
                    listTemp.add(msbm);
                }
                MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                msbm.setbIsSelected(false);
                msbm.setTextForDisplay("XXXX1场");
                msbm.setmSubList(listTemp);
                listTemp2.add(msbm);
            }
            MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
            msbm.setbIsSelected(false);
            msbm.setTextForDisplay("111111局");
            msbm.setmSubList(listTemp2);
            list.add(msbm);
        }
        List<MultiSelectorBaseModel> listTemp2 = new ArrayList<>();
        {
            {
                List<MultiSelectorBaseModel> listTemp = new ArrayList<>();
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("场长办公室");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("副场长办公室");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("总工办公室");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("工程部");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("物机部");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("实验室");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("安质部");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("综合办");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("财务部");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("劳务部");
                    listTemp.add(msbm);
                }
                MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                msbm.setbIsSelected(false);
                msbm.setTextForDisplay("XX2场");
                msbm.setmSubList(listTemp);
                listTemp2.add(msbm);
            }
            {
                List<MultiSelectorBaseModel> listTemp = new ArrayList<>();
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("场长办公室");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("副场长办公室");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("总工办公室");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("工程部");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("物机部");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("实验室");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("安质部");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("综合办");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("财务部");
                    listTemp.add(msbm);
                }
                {
                    MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                    msbm.setbIsSelected(false);
                    msbm.setTextForDisplay("劳务部");
                    listTemp.add(msbm);
                }
                MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
                msbm.setbIsSelected(false);
                msbm.setTextForDisplay("113场");
                msbm.setmSubList(listTemp);
                listTemp2.add(msbm);
            }
        }
        MultiSelectorBaseModel msbm = new MultiSelectorBaseModel();
        msbm.setbIsSelected(false);
        msbm.setTextForDisplay("23456局");
        msbm.setmSubList(listTemp2);
        list.add(msbm);
    }

    private void initPopupWindow() {
        initData();
        mls = new Multi_Level_Selector_Unlimited(this, "请选择部门", list, new MultiLevelSelectorCallBack() {
            @Override
            public void success(String res, MultiSelectorBaseModel obj) {
                tv_depart.setText(res);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
