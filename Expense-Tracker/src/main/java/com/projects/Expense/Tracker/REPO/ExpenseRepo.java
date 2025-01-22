package com.projects.Expense.Tracker.REPO;

import com.projects.Expense.Tracker.ENTITY.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepo extends JpaRepository<Expenses , Long> {

}
