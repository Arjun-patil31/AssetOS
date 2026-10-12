package AssetOS.controller;

import AssetOS.model.MaintenanceRecord;
import AssetOS.service.SampleDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class MaintenanceController {

    private final SampleDataService dataService;

    public MaintenanceController(SampleDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/maintenance")
    public String maintenance(Model model, HttpSession session) {
        model.addAttribute("records", dataService.getAllMaintenanceRecords());
        model.addAttribute("assets", dataService.getAllAssets());
        model.addAttribute("nextId", dataService.nextMaintenanceId());
        model.addAttribute("currentDate", dataService.getCurrentFormattedDate());
        model.addAttribute("activePage", "maintenance");
        Object user = session.getAttribute("currentUser");
        if (user != null) model.addAttribute("currentUser", user);
        return "maintenance";
    }

    @PostMapping("/maintenance/save")
    public String saveMaintenance(@ModelAttribute MaintenanceRecord r) {
        dataService.saveMaintenanceRecord(r);
        return "redirect:/maintenance";
    }

    @PostMapping("/maintenance/delete")
    public String deleteMaintenance(@RequestParam String ticketId) {
        dataService.deleteMaintenanceRecord(ticketId);
        return "redirect:/maintenance";
    }
}
