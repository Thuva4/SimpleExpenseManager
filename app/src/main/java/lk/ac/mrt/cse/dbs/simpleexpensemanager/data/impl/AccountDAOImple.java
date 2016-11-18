package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DB.DataBaseHelper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;

/**
 * Created by smtt on 11/18/16.
 */

public class AccountDAOImple implements AccountDAO {
    @Override
    public List<String> getAccountNumbersList() {
        return null;
    }

    @Override
    public List<Account> getAccountsList() {
        return null;
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
        return null;
    }



    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {

    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {

    }
    protected SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context mContext;
    private Log log;

    public AccountDAOImple(Context context) {
        this.mContext = context;
        dbHelper = DataBaseHelper.getHelper(mContext);
        open();

    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DataBaseHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public void addAccount(Account account) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.ACNO_COLUMN, account.getAccountNo());
        values.put(DataBaseHelper.BANKNAME_COLUMN, account.getBankName());
        values.put(DataBaseHelper.ACCOUNTHOLDERNAME_COLUMN,account.getAccountHolderName());
        values.put(DataBaseHelper.BALANCE_COLUM, account.getBalance());

        database.insert(DataBaseHelper.ACCOUNT_TABLE, null, values);
        Log.d("thuva","hhshcks");
    }

    public List<String> getAllID() {
        List<String> accountID = new ArrayList<>();
//        SQLiteDatabase sqLiteDatabase = null;
        String query = "select "+DataBaseHelper.ACNO_COLUMN+" from "+DataBaseHelper.ACCOUNT_TABLE;
        Cursor cursor = database.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                accountID.add(cursor.getString(cursor.getColumnIndex(DataBaseHelper.ACNO_COLUMN)));
            }
        }catch(Exception ex){
            Log.e("Erro in geting friends ",ex.toString());
        }

        return accountID;
    }


}
