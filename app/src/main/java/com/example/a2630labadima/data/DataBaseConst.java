package com.example.a2630labadima.data;

public class DataBaseConst {
    public static final String DATA_BASE_NAME = "finalapp.db";
    public static final int DATA_BASE_VERSION = 1;
    public static final String VAKANSIYA_TABLE_NAME = "Vakansiyas";
    public static final String VAKANSIYA_ID = "id";
    public static final String VAKANSIYA_NAME = "name";
    public static final String VAKANSIYA_ADDRESS = "address";
    public static final String VAKANSIYA_SALARY = "salary";
    public static final String VAKANSIYA_NUM_TEL = "numTel";

    public static final String CREATE_TABLE_VAKANSIYA = "CREATE TABLE IF NOT EXISTS " +
            "" + VAKANSIYA_TABLE_NAME + " ( " + VAKANSIYA_ID + " INTEGER PRIMARY KEY, " + VAKANSIYA_NAME +
            " TEXT, " + VAKANSIYA_ADDRESS + " TEXT, " + VAKANSIYA_SALARY + " TEXT, " +
            VAKANSIYA_NUM_TEL + " TEXT);";
    public static final String DELETE_TABLE_VAKANSIYA = "DROP TABLE IF EXISTS " + VAKANSIYA_TABLE_NAME;
}
