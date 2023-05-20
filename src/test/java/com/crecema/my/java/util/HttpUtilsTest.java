package com.crecema.my.java.util;

import org.junit.jupiter.api.Test;

public class HttpUtilsTest {

    @Test
    public void testGet() throws Exception {
        String url = "https://www.baidu.com";
        String result = HttpUtils.get(url, null);
        System.out.println(result);
    }

}
