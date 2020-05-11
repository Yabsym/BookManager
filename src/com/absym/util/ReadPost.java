package com.absym.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ReadPost {
    public Map<String, Object> getPostContext(HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, Object> retValue = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
        StringBuffer buf = new StringBuffer();
        String line = null;
        int len = 0;
        while ((line = reader.readLine()) != null) {
            buf.append(line);
        }
        if (buf.toString().isEmpty()) {
            return retValue;
        }
        char toCharArray[] = buf.toString().toCharArray();
        buf = new StringBuffer();

        for (char c : toCharArray) {
            if (c == '&') {
                String key = buf.substring(0, buf.indexOf("=")).toString();
                String value = buf.substring(buf.indexOf("=") + 1, buf.length()).toString();
                retValue.put(key, value);
                buf = new StringBuffer();
            } else {
                buf.append(c);
            }
        }
        String key = buf.substring(0, buf.indexOf("=")).toString();
        String value = buf.substring(buf.indexOf("=") + 1, buf.length()).toString();
        retValue.put(key, value);
        return retValue;
    }
}
