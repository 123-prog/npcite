package com.web.npcite.service;

import com.web.npcite.beans.log;

import java.sql.Timestamp;
import java.util.List;

public interface LogService {
    public List<log> selectLogByUserId(int id);
    public int insertLog(int id, String msg, Timestamp time);
}
