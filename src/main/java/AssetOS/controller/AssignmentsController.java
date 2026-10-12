package AssetOS.controller;

import AssetOS.model.*;
import AssetOS.service.SampleDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class AssignmentsController {

    private final SampleDataService dataService;

    public AssignmentsController(SampleDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/assignments")
    public String assignments(Model model, HttpSession session) {
        model.addAttribute("assignments", dataService.getAllAssignments());
        model.addAttribute("employees", dataService.getAllEmployees());
        model.addAttribute("assets", dataService.getAllAssets());
        model.addAttribute("nextId", dataService.nextAssignmentId());
        model.addAttribute("currentDate", dataService.getCurrentFormattedDate());
        model.addAttribute("activePage", "assignments");
        Object user = session.getAttribute("currentUser");
        if (user != null) model.addAttribute("currentUser", user);
        return "assignments";
    }

    @PostMapping("/assignments/save")
    public String saveAssignment(@ModelAttribute Assignment a, HttpSession session) {
        dataService.saveAssignment(a);
        return "redirect:/assignments";
    }

    @PostMapping("/assignments/delete")
    public String deleteAssignment(@RequestParam String assignmentId) {
        dataService.deleteAssignment(assignmentId);
        return "redirect:/assignments";
    }

    @PostMapping("/assignments/return")
    public String returnAssignment(@RequestParam String assignmentId) {
        dataService.returnAssignment(assignmentId);
        return "redirect:/assignments";
    }
}
