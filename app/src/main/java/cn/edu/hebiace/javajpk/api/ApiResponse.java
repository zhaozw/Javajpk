package cn.edu.hebiace.javajpk.api;

/**
 * Created by duke on 15-12-22.
 */
public class ApiResponse<T> {
    private boolean result;
    private T obj;

    public ApiResponse(boolean result) {
        this.result = result;
    }

    public ApiResponse(boolean result, T obj) {
        this.obj = obj;
        this.result = result;
    }

    public void setSuccess(boolean result){
        this.result = result;
    }
    public boolean isSuccess() {
        return result;
    }

    public T getObj() {
        return obj;
    }
}
