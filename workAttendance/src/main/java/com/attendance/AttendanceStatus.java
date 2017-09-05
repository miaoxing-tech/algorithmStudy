package com.attendance;

public enum AttendanceStatus {

    START_WORK(0, "上班刷卡"),
    START_NOON_BREAK(1, "午休开始"),
    END_NOON_BREAK(2, "午休结束"),
    END_WORK(3, "下班刷卡"),
    ;

    private int code;
    private String desc;

    AttendanceStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static AttendanceStatus codeOf(int code) {
        return EnumCodeOf.codeOf(code, AttendanceStatus.class);
    }

}
