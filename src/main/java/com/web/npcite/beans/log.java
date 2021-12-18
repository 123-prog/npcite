package com.web.npcite.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class log {
    private int id;
    private int user_id;
    private String msg;
    private Timestamp time;
}
