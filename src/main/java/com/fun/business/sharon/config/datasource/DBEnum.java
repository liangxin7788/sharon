package com.fun.business.sharon.config.datasource;

public enum DBEnum {

	SHARONSOURCE("sharonSource"), OTHER("otherSource");

    private String value;

    DBEnum(String db) {
        this.value = db;
    }

    public String getValue() {
        return value;
    }
	
}
