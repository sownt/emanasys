package com.vosxvo.java.services.database;

import java.sql.Connection;

public interface DatabaseServices {
    Connection connectTo(String url, String username, String password);
}