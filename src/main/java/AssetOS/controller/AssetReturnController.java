package AssetOS.controller;

import AssetOS.model.*;
import AssetOS.service.SampleDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class AssetReturnController {

    private final SampleDataService dataService;

    public AssetReturnController(SampleDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/asset-return")
    public String returnPage(Model model, HttpSession session) {
        model.addAttribute("returns", dataService.getAllReturns());
        model.addAttribute("assignments", dataService.getAllAssignments());
        model.addAttribute("assets", dataService.getAllAssets());
        model.addAttribute("employees", dataService.getAllEmployees());
        model.addAttribute("nextNo", dataService.nextReturnNo());
        model.addAttribute("currentDate", dataService.getCurrentFormattedDate());
        model.addAttribute("activePage", "asset-return");
        Object user = session.getAttribute("currentUser");
        if (user != null) model.addAttribute("currentUser", user);
        return "asset-return";
    }

    @PostMapping("/asset-return/save")
    public String saveReturn(@ModelAttribute AssetReturn r) {
        dataService.saveReturn(r);
        return "redirect:/asset-return";
    }

    @PostMapping("/asset-return/delete")
    public String deleteReturn(@RequestParam String returnNo) {
        dataService.deleteReturn(returnNo);
        return "redirect:/asset-return";
    }

    @GetMapping("/api/asset-returns/{returnNo}")
    @ResponseBody
    public AssetReturn getReturnJson(@PathVariable String returnNo) {
        return dataService.getReturnByNo(returnNo);
    }
}
