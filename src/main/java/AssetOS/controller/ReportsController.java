package AssetOS.controller;

import AssetOS.service.SampleDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReportsController {

    private final SampleDataService dataService;

    public ReportsController(SampleDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/reports")
    public String reports(Model model, HttpSession session) {
        model.addAttribute("allAssets", dataService.getAllAssets());
        model.addAttribute("stats", dataService.getDashboardStats());
        model.addAttribute("depreciationRecords", dataService.getDepreciationRecords());
        model.addAttribute("assignments", dataService.getAllAssignments());
        model.addAttribute("transfers", dataService.getAllTransfers());
        model.addAttribute("returns", dataService.getAllReturns());
        model.addAttribute("disposals", dataService.getAllDisposals());
        model.addAttribute("maintenanceRecords", dataService.getAllMaintenanceRecords());
        model.addAttribute("vendors", dataService.getAllVendors());
        model.addAttribute("employees", dataService.getAllEmployees());
        model.addAttribute("expenses", dataService.getAllExpenses());
        model.addAttribute("currentDate", dataService.getCurrentFormattedDate());
        model.addAttribute("activePage", "reports");
        Object user = session.getAttribute("currentUser");
        if (user != null) model.addAttribute("currentUser", user);
        return "reports";
    }
}
