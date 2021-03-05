package com.mydemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mydemo.util.common.Hump2Underline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author kongxy
 * @Date 2019/1/26 11:20
 * @DESC
 */
public final class Result {
  private static Logger logger = LoggerFactory.getLogger(Result.class);
  private static Boolean isDebug = true;
  private int code;
  private String message;
  private Object data;

  private Result() {}

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public Object getData() {
    return data;
  }

  public static Result success() {
    return success(null);
  }

  public static Result success(Object data) {
    Result result = new Result(0, null, data);
    if (isDebug) {
      String jsonString = JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
      jsonString = Hump2Underline.humpToUnderline(jsonString);
      logger.info("出参body:" + jsonString);
    }
    return result;
  }

  public static Result failure(int code) {
    return failure(code, null);
  }

  public static Result failure(int code, String message) {
    return failure(code, message, null);
  }

  public static Result failure(int code, String message, Object data) {
    Result result = new Result(code, message, data);
    if (isDebug) {
      String jsonString = JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
      logger.error("出参body:" + jsonString);
    }
    return result;
  }

  private Result(int code, String message, Object data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }
}

