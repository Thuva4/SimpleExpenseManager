package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.content.Context;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.ExpenseManagerException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.AccountDAOImple;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.TransactionDAOImpl;

/**
 * Created by smtt on 11/19/16.
 */

public class PersistentExpenseManager extends ExpenseManager {
    private Context context;


    public PersistentExpenseManager(Context context1) throws ExpenseManagerException {
        super();
        this.context = context1;
        setup();
    }

    @Override
    public void setup() throws ExpenseManagerException {
        transactionDAO = new TransactionDAOImpl(context);
        accountDAOImple = new AccountDAOImple(context);
    }
}
