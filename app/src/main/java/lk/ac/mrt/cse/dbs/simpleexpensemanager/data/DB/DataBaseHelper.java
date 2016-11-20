package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by smtt on 11/18/16.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "expenseManagerDB_140631X";
    private static final int DATABASE_VERSION = 1;

    public static final String ACCOUNT_TABLE = "account";


    public static final String ACNO_COLUMN = "accountNo";
    public static final String BANKNAME_COLUMN = "bankName";
    public static final String ACCOUNTHOLDERNAME_COLUMN = "accountHolderName";
    public  static final String BALANCE_COLUM = "balance";


    public static final String TRANSACTION_TABLE = "transactiontable";
    public static final String DATE_COLUMN = "date";
    public static final String EXPENSETYPE_COLUMN = "expenseType";
    public  static final String AMOUNT_COLUMN = "amount";



    private static final String CREATE_ACCOUNT_TABLE = "CREATE TABLE "
            + ACCOUNT_TABLE + "(" + ACNO_COLUMN + " TEXT PRIMARY KEY, "
            + BANKNAME_COLUMN + " TEXT, " +ACCOUNTHOLDERNAME_COLUMN + " TEXT, "+ BALANCE_COLUM+ " DOUBLE "
            + ")";


    private static final String CREATE_TRANSACTION_TABLE = "CREATE TABLE "
            + TRANSACTION_TABLE +"(" +"ID" + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + DATE_COLUMN + " DATE, " + ACNO_COLUMN + " TEXT, "
            + EXPENSETYPE_COLUMN + " TEXT, "
            + AMOUNT_COLUMN   + " DOUBLE " +")";

    private static DataBaseHelper instance;
    public static synchronized DataBaseHelper getHelper(Context context) {
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
        db.execSQL(CREATE_TRANSACTION_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
