package com.projects.Expense.Tracker.CONTROLLER;

import com.projects.Expense.Tracker.ENTITY.Expenses;
import com.projects.Expense.Tracker.SERVICE.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExpController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/")
    public String viewExpenses(Model model) {
        model.addAttribute("expenses", expenseService.getAllExpense());
        return "index";  // Loads index.html
    }

    @GetMapping("/add-expense")
    public String addExpenseForm(Model model) {
        model.addAttribute("expense", new Expenses());
        return "add-expense";  // Loads add-expense.html
    }

    @PostMapping("/save-expense")
    public String saveExpense(@ModelAttribute("expense") Expenses expense) {
        expenseService.addExpense(expense);
        return "redirect:/";  // Redirects to the main page
    }
}
