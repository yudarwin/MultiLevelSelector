package com.darwin.multilevelselector;

/**
 * Created by Darwin on 2018/4/2.
 */

public interface MultiLevelSelectorCallBack<T extends MultiSelectorBaseModel> {
    public void success(String res,T obj);
}