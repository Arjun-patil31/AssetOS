package AssetOS.controller;

import AssetOS.model.Asset;
import AssetOS.model.AssetHistoryEntry;
import AssetOS.service.SampleDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AssetsController {

    private final SampleDataService dataService;

    public AssetsController(SampleDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping({"/assets", "/assets/entry"})
    public String assets(@RequestParam(value = "view", defaultValue = "grid") String viewMode,
                         @RequestParam(value = "status", required = false) String selectedStatus,
                         Model model) {
        List<Asset> assets = dataService.getAllAssets();
        long available = assets.stream().filter(a -> "Available".equalsIgnoreCase(a.getStatus())).count();
        long assigned  = assets.stream().filter(a -> "Assigned".equalsIgnoreCase(a.getStatus())).count();
        long maintenance = assets.stream().filter(a -> "Under Maintenance".equalsIgnoreCase(a.getStatus())).count();

        model.addAttribute("assets", assets);
        model.addAttribute("availableCount", available);
        model.addAttribute("assignedCount", assigned);
        model.addAttribute("maintenanceCount", maintenance);
        model.addAttribute("viewMode", viewMode);
        model.addAttribute("selectedStatus", selectedStatus != null ? selectedStatus : "");
        model.addAttribute("activePage", "assets");
        model.addAttribute("activeSubmenu", "entry");
        model.addAttribute("currentDate", dataService.getCurrentFormattedDate());
        model.addAttribute("employees", dataService.getAllEmployees());
        return "assets";
    }

    @PostMapping("/assets/save")
    public String saveAsset(@ModelAttribute Asset asset) {
        dataService.saveOrUpdateAsset(asset);
        return "redirect:/assets";
    }

    @PostMapping("/assets/delete")
    public String deleteAsset(@RequestParam("assetTag") String assetTag) {
        dataService.deleteAsset(assetTag);
        return "redirect:/assets";
    }

    @PostMapping("/assets/transfer")
    public String transferAsset(@RequestParam("assetTag") String assetTag,
                                @RequestParam("newHolder") String newHolder,
                                @RequestParam(value = "newDepartment", required = false) String newDepartment,
                                @RequestParam(value = "remarks", required = false) String remarks) {
        dataService.transferAsset(assetTag, newHolder, newDepartment, remarks);
        return "redirect:/assets";
    }

    @GetMapping("/assets/history/{tag}")
    @ResponseBody
    public ResponseEntity<List<AssetHistoryEntry>> getAssetHistory(@PathVariable("tag") String tag) {
        return ResponseEntity.ok(dataService.getHistoryForAsset(tag));
    }
}
