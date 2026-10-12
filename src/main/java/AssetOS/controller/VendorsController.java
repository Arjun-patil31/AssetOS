package AssetOS.controller;

import AssetOS.model.Vendor;
import AssetOS.service.SampleDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VendorsController {

    private final SampleDataService dataService;

    public VendorsController(SampleDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/vendors")
    public String vendors(Model model) {
        model.addAttribute("vendors", dataService.getAllVendors());
        model.addAttribute("activePage", "vendors");
        model.addAttribute("currentDate", dataService.getCurrentFormattedDate());
        return "vendors";
    }

    @PostMapping("/vendors/save")
    public String saveVendor(@ModelAttribute Vendor vendor) {
        dataService.saveVendor(vendor);
        return "redirect:/vendors";
    }

    @PostMapping("/vendors/delete")
    public String deleteVendor(@RequestParam("vendorCode") String vendorCode) {
        dataService.deleteVendor(vendorCode);
        return "redirect:/vendors";
    }
}
