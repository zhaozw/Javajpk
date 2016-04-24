package cn.edu.hebiace.javajpk.core;

/**
 * Created by duke on 15-12-22.
 */
public interface ActionCallBackListener<T> {
    /**
     * 成功时调用
     *
     * @param data 成功后返回的数据
     */
    void onSuccess(T data);

    /**
     * 失败时调用
     * @param failure 错误信息
     */
    void onFailure(String failure);
}
