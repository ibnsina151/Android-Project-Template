package com.tutorials.hp.tabbedsqlite.mDB;

/**
 * Created by Oclemy on 9/27/2016 for ProgrammingWizards Channel and http://www.camposha.info.
 */
public class Constants {
    /*
  COLUMNS
   */
    static final String ROW_ID="id";
    static final String NAME="name";
    static final String CATEGORY="category";



    /*
    DB PROPERTIES
     */

    static final String DB_NAME="spg_DB";
    static final String TB_NAME="spg_TB";
    static final int DB_VERSION=1;



    /*
    TABLE CREATION STATEMENT
     */
    static final String CREATE_TB="CREATE TABLE spg_TB(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT NOT NULL,category TEXT NOT NULL);";


    /*
    TABLE DELETION STMT
     */
    static final String DROP_TB="DROP TABLE IF EXISTS "+TB_NAME;

}
