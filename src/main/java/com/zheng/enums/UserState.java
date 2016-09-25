package com.zheng.enums;

/**
 * Created by zhenglian on 2016/9/25.
 */
public enum UserState {
    STATE_UNACTIVE(0), STATE_OK(1), STATE_LOCKED(2);

    private Integer state;

    UserState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
