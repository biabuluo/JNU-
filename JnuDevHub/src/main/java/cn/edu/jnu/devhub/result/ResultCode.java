package cn.edu.jnu.devhub.result;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ResultCode", description = "Response code")
public class ResultCode {
    public static int SUCCESS = 200;
    public static int FAILURE = 400;
}
