package AssetOS.controller;

import AssetOS.model.Employee;
import AssetOS.service.SampleDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeesController {

    private final SampleDataService dataService;

    public EmployeesController(SampleDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/employees")
    public String employees(Model model, HttpSession session) {
        model.addAttribute("employees", dataService.getAllEmployees());
        model.addAttribute("nextId", dataService.nextEmployeeId());
        model.addAttribute("currentDate", dataService.getCurrentFormattedDate());
        model.addAttribute("activePage", "employees");
        Object user = session.getAttribute("currentUser");
        if (user != null) model.addAttribute("currentUser", user);
        return "employees";
    }

    @PostMapping("/employees/save")
    public String saveEmployee(@ModelAttribute Employee emp) {
        dataService.saveEmployee(emp);
        return "redirect:/employees";
    }

    @PostMapping("/employees/delete")
    public String deleteEmployee(@RequestParam("employeeId") String employeeId) {
        dataService.deleteEmployee(employeeId);
        return "redirect:/employees";
    }
}
