package com.zyq.controller.utils;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className R
 * @date 2023-01-17 15:01
 */
public class R {
    private boolean flag;
    private Object data;

    public R(boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public R(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "R{" +
                "flag=" + flag +
                ", data=" + data +
                '}';
    }

    public R() {
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
