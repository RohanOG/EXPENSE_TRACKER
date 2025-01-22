package com.projects.Expense.Tracker.SERVICE;

import com.projects.Expense.Tracker.ENTITY.Expenses;
import com.projects.Expense.Tracker.REPO.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepo expenseRepo;

    public List<Expenses> getAllExpense(){
        return expenseRepo.findAll();
    }

    public Expenses addExpense(Expenses expenses){
     return expenseRepo.save(expenses);
    }

    public void deleteExpense(long id){
        expenseRepo.deleteById(id);
    }


}
