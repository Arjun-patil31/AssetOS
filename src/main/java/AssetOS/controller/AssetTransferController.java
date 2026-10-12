package AssetOS.controller;

import AssetOS.model.*;
import AssetOS.service.SampleDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class AssetTransferController {

    private final SampleDataService dataService;

    public AssetTransferController(SampleDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/asset-transfer")
    public String transfer(Model model, HttpSession session) {
        model.addAttribute("transfers", dataService.getAllTransfers());
        model.addAttribute("employees", dataService.getAllEmployees());
        model.addAttribute("assets", dataService.getAllAssets());
        model.addAttribute("nextNo", dataService.nextTransferNo());
        model.addAttribute("currentDate", dataService.getCurrentFormattedDate());
        model.addAttribute("activePage", "asset-transfer");
        Object user = session.getAttribute("currentUser");
        if (user != null) model.addAttribute("currentUser", user);
        return "asset-transfer";
    }

    @PostMapping("/asset-transfer/save")
    public String saveTransfer(@ModelAttribute AssetTransferRecord t) {
        // Also perform the actual asset transfer in asset inventory
        if (t.getAssetTag() != null && !t.getAssetTag().isBlank()) {
            dataService.transferAsset(t.getAssetTag(), t.getToEmployee(), t.getToDepartment(), t.getReason());
        }
        dataService.saveTransfer(t);
        return "redirect:/asset-transfer";
    }

    @PostMapping("/asset-transfer/delete")
    public String deleteTransfer(@RequestParam String transferNo) {
        dataService.deleteTransfer(transferNo);
        return "redirect:/asset-transfer";
    }
}
