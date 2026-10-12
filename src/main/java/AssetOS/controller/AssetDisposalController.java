package AssetOS.controller;

import AssetOS.model.*;
import AssetOS.service.SampleDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class AssetDisposalController {

    private final SampleDataService dataService;

    public AssetDisposalController(SampleDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/asset-disposal")
    public String disposalPage(Model model, HttpSession session) {
        model.addAttribute("disposals", dataService.getAllDisposals());
        model.addAttribute("assets", dataService.getAllAssets());
        model.addAttribute("nextNo", dataService.nextDisposalNo());
        model.addAttribute("currentDate", dataService.getCurrentFormattedDate());
        model.addAttribute("activePage", "asset-disposal");
        Object user = session.getAttribute("currentUser");
        if (user != null) model.addAttribute("currentUser", user);
        return "asset-disposal";
    }

    @PostMapping("/asset-disposal/save")
    public String saveDisposal(@ModelAttribute AssetDisposal d) {
        // Mark asset as Retired/Disposed in the asset inventory
        if (d.getAssetTag() != null && !d.getAssetTag().isBlank()) {
            AssetOS.model.Asset asset = dataService.getAssetByTag(d.getAssetTag());
            if (asset != null) { asset.setStatus("Retired"); }
        }
        dataService.saveDisposal(d);
        return "redirect:/asset-disposal";
    }

    @PostMapping("/asset-disposal/delete")
    public String deleteDisposal(@RequestParam String disposalNo) {
        dataService.deleteDisposal(disposalNo);
        return "redirect:/asset-disposal";
    }
}
