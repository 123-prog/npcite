package com.web.npcite.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    private int id;
    private int user_id;
    private int commodity_id;
    private String commodity_name;
    private int num;
}
