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

//AccountDAO implementation

public class AccountDAOImple implements AccountDAO {
    protected SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context mContext;



    @Override
    public List<String> getAccountNumbersList() {
        List<String> accountID = new ArrayList<>();
        String query = "select "+DataBaseHelper.ACNO_COLUMN+" from "+DataBaseHelper.ACCOUNT_TABLE;
        Cursor cursor = database.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                accountID.add(cursor.getString(cursor.getColumnIndex(DataBaseHelper.ACNO_COLUMN)));
            }
        }catch(Exception ex){

        }

        return accountID;
    }

    @Override
    public List<Account> getAccountsList() {
        List<Account> account = new ArrayList<>();
        String query = "select "+" * "+ "from "+DataBaseHelper.ACCOUNT_TABLE;
        Cursor cursor = database.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                String acNO = cursor.getString(cursor.getColumnIndex(DataBaseHelper.ACNO_COLUMN));
                String bankname = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BANKNAME_COLUMN));
                String acHoldername = cursor.getString(cursor.getColumnIndex(DataBaseHelper.ACCOUNTHOLDERNAME_COLUMN));
                Double balance = Double.parseDouble(cursor.getString(cursor.getColumnIndex(DataBaseHelper.BALANCE_COLUM)));


                Account account1 = new Account(acNO,bankname,acHoldername,balance);
                account.add(account1);
            }
        }catch(Exception ex){

        }
        return account;
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
        Account account1=null;
        String query = "select "+"*"+" from "+DataBaseHelper.ACCOUNT_TABLE + " WHERE accountNo = '" + accountNo + "'";
        Cursor cursor = database.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                String acNO = cursor.getString(cursor.getColumnIndex(DataBaseHelper.ACNO_COLUMN));
                String bankname = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BANKNAME_COLUMN));
                String acHoldername = cursor.getString(cursor.getColumnIndex(DataBaseHelper.ACCOUNTHOLDERNAME_COLUMN));
                Double balance = Double.parseDouble(cursor.getString(cursor.getColumnIndex(DataBaseHelper.BALANCE_COLUM)));

                account1 = new Account(acNO,bankname,acHoldername,balance);
            }
        }catch(Exception ex){

        }

        return account1;
    }



    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {
        String query = "DELETE "+" FROM "+DataBaseHelper.ACCOUNT_TABLE + " WHERE accountNo =" + accountNo;
        database.rawQuery(query, null);


    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {
        Double balance =null;

        String query = "select "+"*"+" from "+DataBaseHelper.ACCOUNT_TABLE + " WHERE accountNo = "  + accountNo;
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            balance = Double.parseDouble(cursor.getString(cursor.getColumnIndex(DataBaseHelper.BALANCE_COLUM)));
        }
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.ACNO_COLUMN, accountNo);
        if(expenseType == ExpenseType.EXPENSE){
            values.put(DataBaseHelper.BALANCE_COLUM, balance-amount);
        }
        else{
            values.put(DataBaseHelper.BALANCE_COLUM, balance-amount);
        }
        String condition = "accountNo = " + accountNo;
        database.update(DataBaseHelper.ACCOUNT_TABLE,values,condition,null);
    }


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
    }



}
