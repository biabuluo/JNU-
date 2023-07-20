package cn.edu.jnu.devhub.result;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Result", description = "Returns the corresponding result class")
public class Result<T> {
    private int code = 0;
    private String msg = "";
    private List<T> data = null;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
