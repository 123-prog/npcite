package com.web.npcite.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commodity {
    private int id;
    private String name;
    private String msg; //商品描述
    private int price; //商品价格
    private int reserve; //商品库存
    private int owner; //商品所有者
    private int sale_num; //商品已售出数目
    private int income; //商品带来的收入
}
