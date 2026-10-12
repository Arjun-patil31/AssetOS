package AssetOS.controller;

import AssetOS.model.Expense;
import AssetOS.service.SampleDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class ExpenseController {

    private final SampleDataService dataService;

    public ExpenseController(SampleDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/expenses")
    public String expenses(Model model, HttpSession session) {
        model.addAttribute("expenses", dataService.getAllExpenses());
        model.addAttribute("nextNo", dataService.nextExpenseNo());
        model.addAttribute("currentDate", dataService.getCurrentFormattedDate());
        model.addAttribute("activePage", "expenses");
        Object user = session.getAttribute("currentUser");
        if (user != null) model.addAttribute("currentUser", user);
        return "expenses";
    }

    @PostMapping("/expenses/save")
    public String saveExpense(@ModelAttribute Expense expense) {
        dataService.saveExpense(expense);
        return "redirect:/expenses";
    }

    @PostMapping("/expenses/delete")
    public String deleteExpense(@RequestParam("expenseNo") String expenseNo) {
        dataService.deleteExpense(expenseNo);
        return "redirect:/expenses";
    }
}
