package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.DB.DataBaseHelper;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

/**
 * Created by smtt on 11/18/16.
 */

public class TransactionDAOImpl implements TransactionDAO {


    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {

    }

    @Override
    public List<Transaction> getAllTransactionLogs() {
        return null;
    }

    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {
        return null;
    }

    protected SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context mContext;
    private Log log;
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "dd-yyyy-MM", Locale.ENGLISH);

    public TransactionDAOImpl(Context context) {
        this.mContext = context;
        dbHelper = DataBaseHelper.getHelper(mContext);
        open();

    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DataBaseHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }


    public void addTransaction(Transaction transaction) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.ACNO_COLUMN, transaction.getAccountNo());
        values.put(DataBaseHelper.EXPENSETYPE_COLUMN, transaction.getExpenseType());
        values.put(DataBaseHelper.DATE_COLUMN,formatter.format(transaction.getDate()));
        values.put(DataBaseHelper.AMOUNT_COLUMN, transaction.getAmount());
        database.insert(DataBaseHelper.TRANSACTION_TABLE, null, values);
        Log.d("thuva","hhshcks");
    }


    public ArrayList<Transaction> getTransaction() throws ParseException {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        Cursor cursor = database.query(DataBaseHelper.TRANSACTION_TABLE,
                new String[] { DataBaseHelper.DATE_COLUMN,
                        DataBaseHelper.ACNO_COLUMN,
                        DataBaseHelper.EXPENSETYPE_COLUMN,
                        DataBaseHelper.AMOUNT_COLUMN }, null, null, null,
                null, null);

        while (cursor.moveToNext()) {

            Transaction transaction = new Transaction(formatter.parse(cursor.getString(0)),cursor.getString(1),ExpenseType.valueOf(cursor.getString(2).toUpperCase()),cursor.getDouble(3));

            transactions.add(transaction);
        }
        return transactions;
    }







}

