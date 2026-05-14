package com.quoraApp.quroa.util;

import java.time.LocalDateTime;

public class CursorUtil {
    
    public static boolean isValidCursor(String cursor){
        if(cursor == null || cursor.isEmpty() ){
            return false;
        }

        try {
            LocalDateTime.parse(cursor);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    public static LocalDateTime parseCursor(String cursor){
        if(isValidCursor(cursor)){
            return LocalDateTime.parse(cursor);
        }

        throw new IllegalArgumentException();
    }

}

