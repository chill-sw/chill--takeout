package com.chill.common;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private Object data; //数据

    private Map map = new HashMap(); //动态数据

    public static Result success(Object data) {
        Result r = new Result();
        r.data = data;
        r.code = 1;
        return r;
    }

    public static Result error(String msg) {
        Result r = new Result();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public Result add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
