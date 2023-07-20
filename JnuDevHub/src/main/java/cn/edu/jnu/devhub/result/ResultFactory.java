package cn.edu.jnu.devhub.result;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "ResultFactory", description = "Constructs a factory class that returns the corresponding result class")
public class ResultFactory {
    public static <T> cn.edu.jnu.devhub.result.Result<T> buildResult (int code, String msg, List<T> data) {
        cn.edu.jnu.devhub.result.Result<T> result = new cn.edu.jnu.devhub.result.Result<T>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
