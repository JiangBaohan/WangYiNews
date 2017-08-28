package com.example.administrator.toutiao.Bean;

import java.util.ArrayList;

/**
 * data:2017/8/17
 * author:汉堡(Administrator)
 * function:
 */

public class XFBean {
    public ArrayList<WS> ws;

    public class WS {
        public ArrayList<CW> cw;
    }

    public class CW {
        public String w;
    }
}

