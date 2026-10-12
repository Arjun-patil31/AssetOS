package AssetOS.controller;

import AssetOS.service.SampleDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final SampleDataService dataService;

    public DashboardController(SampleDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("stats", dataService.getDashboardStats());
        model.addAttribute("categoryCounts", dataService.getCategoryCounts());
        model.addAttribute("categoryValues", dataService.getCategoryValues());
        model.addAttribute("deptCounts", dataService.getDepartmentCounts());
        model.addAttribute("conditionCounts", dataService.getConditionCounts());
        model.addAttribute("locations", dataService.getLocationStats());
        model.addAttribute("maintenanceAlerts", dataService.getMaintenanceAlerts());
        model.addAttribute("recentActivities", dataService.getRecentActivities());
        model.addAttribute("recentAddedAssets", dataService.getRecentlyAddedAssets(6));
        model.addAttribute("recentAssignments", dataService.getRecentAssignments(5));
        model.addAttribute("recentReturns", dataService.getRecentReturns(5));
        model.addAttribute("timelineActivities", dataService.getTimelineActivities());
        model.addAttribute("allAssets", dataService.getAllAssets());
        model.addAttribute("allEmployees", dataService.getAllEmployees());
        model.addAttribute("expenses", dataService.getAllExpenses());
        model.addAttribute("currentDate", dataService.getCurrentFormattedDate());
        model.addAttribute("activePage", "dashboard");
        return "dashboard";
    }
}
