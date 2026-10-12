package AssetOS.controller;

import AssetOS.model.CompanyProfile;
import AssetOS.model.User;
import AssetOS.service.SampleDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SettingsController {

    private final SampleDataService dataService;

    public SettingsController(SampleDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/settings")
    public String settings(Model model, jakarta.servlet.http.HttpSession session) {
        model.addAttribute("companyProfile", dataService.getCompanyProfile());
        model.addAttribute("systemUsers", dataService.getAllUsers());
        model.addAttribute("activePage", "settings");
        model.addAttribute("currentDate", dataService.getCurrentFormattedDate());
        Object user = session.getAttribute("currentUser");
        if (user != null) model.addAttribute("currentUser", user);
        return "settings";
    }

    @PostMapping("/settings/company")
    public String updateCompanyProfile(@ModelAttribute CompanyProfile companyProfile) {
        dataService.updateCompanyProfile(companyProfile);
        return "redirect:/settings?saved=true";
    }

    @PostMapping("/settings/users/save")
    public String addUser(@ModelAttribute User user) {
        if (user.getAvatarInitials() == null || user.getAvatarInitials().isBlank()) {
            user.setAvatarInitials("U");
        }
        dataService.addUser(user);
        return "redirect:/settings?userSaved=true";
    }

    @PostMapping("/settings/users/delete")
    public String deleteUser(@RequestParam("username") String username) {
        dataService.deleteUser(username);
        return "redirect:/settings?userDeleted=true";
    }
}
