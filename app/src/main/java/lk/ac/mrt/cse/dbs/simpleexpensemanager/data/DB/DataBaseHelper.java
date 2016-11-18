package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by smtt on 11/18/16.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "expenseManagerDB";
    private static final int DATABASE_VERSION = 1;

    public static final String ACCOUNT_TABLE = "account";
    public static final String ACNO_COLUMN = "accountNo";
    public static final String BANKNAME_COLUMN = "bankName";
    public static final String ACCOUNTHOLDERNAME_COLUMN = "accountHolderName";
    public  static final String BALANCE_COLUM = "balance";

//    public static final String EMPLOYEE_DOB = "dob";
//    public static final String EMPLOYEE_SALARY = "salary";

    public static final String CREATE_ACCOUNT_TABLE = "CREATE TABLE "
            + ACCOUNT_TABLE + "(" + ACNO_COLUMN + " INTEGER PRIMARY KEY, "
            + BANKNAME_COLUMN + " TEXT, " +ACCOUNTHOLDERNAME_COLUMN + " TEXT, "+ BALANCE_COLUM+ " DOUBLE "
            + ")";

    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DataBaseHelper(context);
        return instance;
    }




    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACCOUNT_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
