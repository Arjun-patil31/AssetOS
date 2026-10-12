package AssetOS.service;

import AssetOS.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class SampleDataService {

    private final List<Asset> assets = new ArrayList<>();
    private final List<Vendor> vendors = new ArrayList<>();
    private final List<User> systemUsers = new ArrayList<>();
    private final Map<String, List<AssetHistoryEntry>> assetHistories = new HashMap<>();
    private final List<Employee> employees = new ArrayList<>();
    private final List<Assignment> assignments = new ArrayList<>();
    private final List<AssetTransferRecord> transfers = new ArrayList<>();
    private final List<AssetReturn> returns = new ArrayList<>();
    private final List<AssetDisposal> disposals = new ArrayList<>();
    private final List<Expense> expenses = new ArrayList<>();
    private CompanyProfile companyProfile;

    public SampleDataService() {
        initCompanyProfile();
        initVendors();
        initSystemUsers();
        initEmployees();
        initAssets();
        initAssignments();
        initTransfers();
        initReturns();
        initDisposals();
        initExpenses();
    }

    // ─────────────────────────────────────────────────────────────
    //  REAL-TIME CURRENT DATE (fixes hardcoded day issue)
    // ─────────────────────────────────────────────────────────────
    public String getCurrentFormattedDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, d MMM yyyy"));
    }

    private void initCompanyProfile() {
        this.companyProfile = new CompanyProfile(
                "Your Company Pvt. Ltd.",
                "27AAACG0123M1Z2",
                "+91 98765 43210",
                "admin@company.com",
                "Tower B, Tech Park, Bangalore 560100",
                "2026-2027"
        );
    }

    private void initSystemUsers() {
        systemUsers.add(new User("USR001", "admin123", "Admin User", "Administrator", "admin@company.com", "AD"));
        systemUsers.add(new User("USR002", "manager123", "Sarah Jenkins", "Asset Manager", "s.jenkins@company.com", "SJ"));
        systemUsers.add(new User("USR003", "staff123", "Ravi Mehra", "IT Support", "r.mehra@company.com", "RM"));
        systemUsers.add(new User("USR004", "audit123", "Priya Sharma", "Auditor", "p.sharma@company.com", "PS"));
        systemUsers.add(new User("USR005", "it123", "Vikram Patel", "IT Support", "v.patel@company.com", "VP"));
    }

    private void initVendors() {
        Vendor v1 = new Vendor("VND001", "Dell Technologies India", "Rajesh Khanna", "+91 98111 22334", "sales@dell.co.in", "29AAAAA0000A1Z5", "Electronic City, Bangalore", "Primary IT hardware vendor");
        v1.setDepartment("IT");
        v1.setCategory("Hardware");
        Vendor v2 = new Vendor("VND002", "Apple Authorized Reseller (Imagine)", "Siddharth Roy", "+91 98222 33445", "corporate@imagineonline.store", "27BBBBB1111B2Z6", "Bandra Kurla Complex, Mumbai", "Apple Mac & iPad procurement");
        v2.setDepartment("IT");
        v2.setCategory("Hardware");
        Vendor v3 = new Vendor("VND003", "Steelcase Office Solutions", "Ananya Deshmukh", "+91 98333 44556", "support@steelcase.in", "29CCCCC2222C3Z7", "Whitefield, Bangalore", "Ergonomic workstations & seating");
        v3.setDepartment("Operations");
        v3.setCategory("Furniture");
        Vendor v4 = new Vendor("VND004", "Fortinet Enterprise Security", "Vikram Patel", "+91 98444 55667", "vikram@fortinet-partner.in", "24DDDDD3333D4Z8", "SG Highway, Ahmedabad", "Firewall & network infrastructure");
        v4.setDepartment("IT");
        v4.setCategory("Hardware");
        Vendor v5 = new Vendor("VND005", "Apex Talent & HR Workplace Solutions", "Sunita Nair", "+91 98555 66778", "contact@apexhr.in", "27EEEEE4444E5Z9", "Nariman Point, Mumbai", "HR hardware and corporate employee onboarding kits");
        v5.setDepartment("HR");
        v5.setCategory("Hardware");
        vendors.add(v1);
        vendors.add(v2);
        vendors.add(v3);
        vendors.add(v4);
        vendors.add(v5);
    }

    private void initAssets() {
        assets.add(new Asset("AST0020", "Dell PowerEdge T350 Server", "Desktop", "Dell", "PowerEdge T350", "SN-SRV-T350-20", "Under Maintenance", "Datacenter Rack 1", "HR", "01 Dec 2024", "01 Dec 2029", "Server Room", "New", "₹2,10,000", "₹1,39,349", "Fatima Begum", "/images/server.svg"));
        assets.add(new Asset("AST0019", "Epson EB-E01 XGA Projector", "Peripheral", "Epson", "EB-E01 XGA", "SN-PER-EPS-19", "Assigned", "Conference Room A", "Finance", "01 Nov 2024", "01 Nov 2027", "Floor 2", "Good", "₹34,500", "₹22,680", "Fatima Begum", "/images/projector.svg"));
        assets.add(new Asset("AST0018", "Toyota Innova Crysta (Company Car)", "Vehicle", "Toyota", "Innova Crysta 2.4", "SN-VEH-INNOVA-18", "Under Maintenance", "Logistics Pool", "Operations", "15 May 2023", "15 May 2028", "Warehouse", "Fair", "₹21,50,000", "₹7,62,310", "Logistics Team", "/images/car.svg"));
        assets.add(new Asset("AST0017", "Sony WH-1000XM5 Headset", "Peripheral", "Sony", "WH-1000XM5 ANC", "SN-PER-XM5-17", "Assigned", "Sarah Jenkins", "HR", "01 Nov 2024", "01 Nov 2026", "Floor 1", "New", "₹29,990", "₹25,752", "Karim Uddin", "/images/headset.svg"));
        assets.add(new Asset("AST0016", "Logitech MX Master 3S Mouse", "Peripheral", "Logitech", "MX Master 3S", "SN-PER-MX3S-16", "Assigned", "David Miller", "Operations", "15 Feb 2025", "15 Feb 2027", "Floor 1", "Good", "₹8,995", "₹5,289", "Anisur Rahman", "/images/mouse.svg"));
        assets.add(new Asset("AST0015", "APC 3KVA Online UPS", "Networking", "APC", "Smart-UPS RT 3000", "SN-NET-APC-15", "Available", "Unassigned", "IT", "10 Jun 2024", "10 Jun 2027", "Server Room", "Good", "₹48,000", "₹32,455", "Unassigned", "/images/ups.svg"));
        assets.add(new Asset("AST0014", "Fortinet FortiGate 60F Firewall", "Networking", "Fortinet", "FortiGate 60F", "SN-NET-FG60F-14", "Assigned", "Network Core", "IT", "01 Jul 2024", "01 Jul 2027", "Server Room", "Good", "₹89,000", "₹51,921", "Sadia Afrin", "/images/firewall.svg"));
        assets.add(new Asset("AST0013", "Cisco Catalyst 24-Port Switch", "Networking", "Cisco", "Catalyst 1000-24T", "SN-NET-SW24-13", "Available", "Unassigned", "IT", "15 Aug 2024", "15 Aug 2027", "Warehouse", "Good", "₹54,000", "₹36,503", "Unassigned", "/images/switch.svg"));
        assets.add(new Asset("AST0012", "Apple iPhone 15 Pro", "Mobile", "Apple", "iPhone 15 Pro 256GB", "SN-MOB-IP15P-12", "Available", "Unassigned", "IT", "20 Mar 2025", "20 Mar 2027", "Floor 1", "New", "₹1,34,900", "₹1,34,900", "Unassigned", "/images/phone.svg"));
        assets.add(new Asset("AST0011", "Samsung Galaxy S24 Ultra", "Mobile", "Samsung", "Galaxy S24 Ultra", "SN-MOB-S24U-11", "Available", "Unassigned", "IT", "01 Apr 2025", "01 Apr 2027", "Floor 1", "New", "₹1,29,999", "₹96,237", "Unassigned", "/images/phone.svg"));
        assets.add(new Asset("AST0010", "Dell Latitude 5440 Laptop", "Laptop", "Dell", "Latitude 5440 i7", "SN-LAP-LAT5440-10", "Under Maintenance", "IT Support", "IT", "10 Jan 2024", "10 Jan 2027", "Floor 2", "Fair", "₹85,000", "₹58,000", "IT Lab", "/images/laptop.svg"));
        assets.add(new Asset("AST0009", "MacBook Pro 16\" M3 Max", "Laptop", "Apple", "MacBook Pro M3 Max", "SN-LAP-MBP16-09", "Assigned", "Alex Rivera", "IT", "15 Jan 2024", "15 Jan 2027", "Floor 1", "New", "₹3,49,900", "₹2,85,000", "Alex Rivera", "/images/laptop.svg"));
        assets.add(new Asset("AST0008", "Lenovo ThinkPad T14s Gen 4", "Laptop", "Lenovo", "ThinkPad T14s G4", "SN-LAP-TP14-08", "Assigned", "Priya Sharma", "Sales", "01 Feb 2024", "01 Feb 2027", "Floor 1", "Good", "₹1,15,000", "₹82,000", "Priya Sharma", "/images/laptop.svg"));
        assets.add(new Asset("AST0007", "HP EliteBook 840 G10", "Laptop", "HP", "EliteBook 840 G10", "SN-LAP-EB840-07", "Available", "Unassigned", "Inventory Pool", "20 Feb 2024", "20 Feb 2027", "Warehouse", "New", "₹1,05,000", "₹76,000", "Unassigned", "/images/laptop.svg"));
        assets.add(new Asset("AST0006", "Modular Workstation Desk", "Furniture", "Steelcase", "Migration SE Height Adj", "SN-FUR-DSK-06", "Under Maintenance", "Operations Bay 3", "Sales", "05 Mar 2023", "05 Mar 2028", "Floor 1", "Good", "₹38,000", "₹26,000", "Operations Pool", "/images/desk.svg"));
        assets.add(new Asset("AST0005", "Ergonomic Mesh Chair", "Furniture", "Featherlite", "Aeron Chair Size B", "SN-FUR-CHR-05", "Assigned", "Elena Rostova", "Sales", "10 Mar 2023", "10 Mar 2035", "Floor 1", "Good", "₹14,500", "₹9,500", "Arifur Rahman", "/images/chair.svg"));
        assets.add(new Asset("AST0004", "Conference Room Table 10-Seater", "Furniture", "Godrej Interio", "Conference Table Pro", "SN-FUR-TBL-04", "Assigned", "Boardroom", "HR", "12 Mar 2023", "12 Mar 2033", "Floor 2", "Good", "₹65,000", "₹48,000", "HR Boardroom", "/images/table.svg"));
        assets.add(new Asset("AST0003", "HP LaserJet Pro M404dn", "Printer", "HP", "LaserJet Pro M404dn", "SN-PRN-M404-03", "Assigned", "Unassigned", "HR", "18 Apr 2023", "18 Apr 2026", "Floor 2", "Good", "₹28,500", "₹18,000", "Floor 2 Print Station", "/images/printer.svg"));
        assets.add(new Asset("AST0002", "Canon imageRUNNER ADVANCE", "Printer", "Canon", "iR-ADV DX C3826i", "SN-PRN-2625-02", "Assigned", "Central Copy Center", "Operations", "22 Apr 2023", "22 Apr 2028", "Floor 1", "Good", "₹1,65,000", "₹1,15,000", "Central Admin", "/images/printer.svg"));
        assets.add(new Asset("AST0001", "Dell UltraSharp 27\" Monitor", "Desktop", "Dell", "U2723QE 4K Hub", "SN-MON-2723-01", "Assigned", "Michael Chang", "IT", "05 May 2024", "05 May 2027", "Floor 1", "Good", "₹42,000", "₹36,503", "Michael Chang", "/images/monitor.svg"));

        // Initialize history for each asset
        for (Asset a : assets) {
            List<AssetHistoryEntry> history = new ArrayList<>();
            history.add(new AssetHistoryEntry(a.getPurchaseDate(), "Initial Procurement", "Purchased from authorized distributor for " + a.getPurchaseCost(), "Procurement Dept"));
            if ("Assigned".equalsIgnoreCase(a.getStatus())) {
                history.add(new AssetHistoryEntry("05 May 2024", "Asset Assigned", "Assigned to " + a.getHolder() + " (" + a.getDepartment() + ")", "IT Admin"));
            } else if ("Under Maintenance".equalsIgnoreCase(a.getStatus())) {
                history.add(new AssetHistoryEntry("07 Oct 2026", "Maintenance Logged", "Reported issue and scheduled diagnostics", "IT Support Lab"));
            }
            assetHistories.put(a.getAssetTag(), history);
        }
    }

    // ─────────────────────────────────────────────────────────────
    //  ASSET CRUD & OPERATIONS
    // ─────────────────────────────────────────────────────────────
    public synchronized List<Asset> getAllAssets() {
        return new ArrayList<>(assets);
    }

    public synchronized Asset getAssetByTag(String tag) {
        if (tag == null) return null;
        return assets.stream().filter(a -> a.getAssetTag().equalsIgnoreCase(tag.trim())).findFirst().orElse(null);
    }

    public synchronized void saveOrUpdateAsset(Asset asset) {
        if (asset == null || asset.getAssetTag() == null) return;
        Asset existing = getAssetByTag(asset.getAssetTag());
        if (existing != null) {
            existing.setName(asset.getName());
            existing.setCategory(asset.getCategory());
            existing.setBrand(asset.getBrand());
            existing.setModel(asset.getModel());
            existing.setSerialNumber(asset.getSerialNumber());
            existing.setStatus(asset.getStatus());
            existing.setAssignedTo(asset.getAssignedTo());
            existing.setHolder(asset.getHolder());
            existing.setDepartment(asset.getDepartment());
            existing.setLocation(asset.getLocation());
            existing.setCondition(asset.getCondition());
            existing.setPurchaseCost(asset.getPurchaseCost());
            existing.setBookValue(asset.getBookValue());
            if (asset.getImageUrl() != null && !asset.getImageUrl().isBlank()) {
                existing.setImageUrl(asset.getImageUrl());
            }

            // Log update history
            addHistoryEntry(asset.getAssetTag(), new AssetHistoryEntry(getCurrentFormattedDate(), "Asset Updated", "Asset parameters modified by user", "System Administrator"));
        } else {
            if (asset.getImageUrl() == null || asset.getImageUrl().isBlank()) {
                asset.setImageUrl(resolveCategoryIcon(asset.getCategory()));
            }
            assets.add(0, asset);
            addHistoryEntry(asset.getAssetTag(), new AssetHistoryEntry(getCurrentFormattedDate(), "Asset Registered", "Newly added to asset inventory", "System Administrator"));
        }
    }

    public synchronized boolean deleteAsset(String tag) {
        if (tag == null) return false;
        return assets.removeIf(a -> a.getAssetTag().equalsIgnoreCase(tag.trim()));
    }

    public synchronized boolean transferAsset(String tag, String newHolder, String newDepartment, String remarks) {
        Asset asset = getAssetByTag(tag);
        if (asset == null) return false;

        String oldHolder = asset.getHolder();
        asset.setHolder(newHolder);
        asset.setAssignedTo(newHolder);
        if (newDepartment != null && !newDepartment.isBlank()) {
            asset.setDepartment(newDepartment);
        }
        asset.setStatus("Assigned");

        String detail = "Transferred from " + oldHolder + " to " + newHolder + " (" + asset.getDepartment() + ")";
        if (remarks != null && !remarks.isBlank()) {
            detail += ". Note: " + remarks;
        }

        addHistoryEntry(tag, new AssetHistoryEntry(getCurrentFormattedDate(), "Asset Transfer", detail, "System Administrator"));
        return true;
    }

    public synchronized List<AssetHistoryEntry> getHistoryForAsset(String tag) {
        if (tag == null) return Collections.emptyList();
        return assetHistories.getOrDefault(tag.trim(), Collections.emptyList());
    }

    public synchronized void addHistoryEntry(String tag, AssetHistoryEntry entry) {
        if (tag == null || entry == null) return;
        assetHistories.computeIfAbsent(tag.trim(), k -> new ArrayList<>()).add(0, entry);
    }

    private String resolveCategoryIcon(String category) {
        if (category == null) return "/images/laptop.svg";
        switch (category.toLowerCase()) {
            case "laptop": return "/images/laptop.svg";
            case "desktop": return "/images/server.svg";
            case "mobile": return "/images/phone.svg";
            case "printer": return "/images/printer.svg";
            case "networking": return "/images/switch.svg";
            case "vehicle": return "/images/car.svg";
            case "furniture": return "/images/chair.svg";
            case "peripheral": return "/images/mouse.svg";
            default: return "/images/laptop.svg";
        }
    }

    // ─────────────────────────────────────────────────────────────
    //  VENDOR MANAGEMENT
    // ─────────────────────────────────────────────────────────────
    public synchronized List<Vendor> getAllVendors() {
        return new ArrayList<>(vendors);
    }

    public synchronized void saveVendor(Vendor v) {
        if (v == null) return;
        if (v.getVendorCode() == null || v.getVendorCode().isBlank()) {
            v.setVendorCode("VND00" + (vendors.size() + 1));
        }
        vendors.removeIf(existing -> existing.getVendorCode().equalsIgnoreCase(v.getVendorCode().trim()));
        vendors.add(0, v);
    }

    public synchronized boolean deleteVendor(String code) {
        if (code == null) return false;
        return vendors.removeIf(v -> v.getVendorCode().equalsIgnoreCase(code.trim()));
    }

    // ─────────────────────────────────────────────────────────────
    //  SETTINGS & COMPANY PROFILE
    // ─────────────────────────────────────────────────────────────
    public synchronized CompanyProfile getCompanyProfile() {
        return companyProfile;
    }

    public synchronized void updateCompanyProfile(CompanyProfile cp) {
        if (cp != null) {
            this.companyProfile = cp;
        }
    }

    public synchronized List<User> getAllUsers() {
        return new ArrayList<>(systemUsers);
    }

    public synchronized void addUser(User u) {
        if (u != null && u.getUsername() != null && !u.getUsername().isBlank()) {
            systemUsers.removeIf(existing -> existing.getUsername().equalsIgnoreCase(u.getUsername().trim()));
            systemUsers.add(u);
        }
    }

    public synchronized boolean deleteUser(String username) {
        if (username == null) return false;
        return systemUsers.removeIf(u -> u.getUsername().equalsIgnoreCase(username.trim()));
    }

    // ─────────────────────────────────────────────────────────────
    //  INIT: ASSIGNMENTS, TRANSFERS, RETURNS, DISPOSALS
    // ─────────────────────────────────────────────────────────────
    private void initAssignments() {
        assignments.add(new Assignment("ASN0021", "AST0009", "Apple MacBook Pro 16\"", "Laptop", "Rahim Ahmed", "EMP0012", "IT", "10 Aug 2026", "Permanent", "Active"));
        assignments.add(new Assignment("ASN0020", "AST0007", "HP EliteBook 840 G8", "Laptop", "Jamil Hossain", "EMP0012", "Operations", "10 Aug 2026", "Permanent", "Active"));
        assignments.add(new Assignment("ASN0019", "AST0020", "Dell PowerEdge T350 Server", "Desktop", "Karim Uddin", "EMP0018", "HR", "10 Aug 2026", "Permanent", "Active"));
        assignments.add(new Assignment("ASN0018", "AST0014", "Fortinet FortiGate 60F Firewall", "Networking", "Sadia Afrin", "EMP0016", "Marketing", "10 Aug 2026", "Permanent", "Active"));
        assignments.add(new Assignment("ASN0017", "AST0016", "Logitech MX Master 3S Mouse, Cisco Catalyst 24-Port Switch", "Peripheral", "Anisur Rahman", "EMP0018", "Operations", "10 Aug 2026", "Permanent", "Active"));
        assignments.add(new Assignment("ASN0016", "AST0016", "Logitech MX Master 3S Mouse", "Peripheral", "Fatima Begum", "EMP0019", "Finance", "09 Aug 2026", "Permanent", "Active"));
        assignments.add(new Assignment("ASN0015", "AST0017", "Sony WH-1000XM5 Headset", "Peripheral", "Karim Uddin", "EMP0018", "HR", "09 Aug 2026", "Permanent", "Active"));
        assignments.add(new Assignment("ASN0014", "AST0005", "Ergonomic Mesh Chair", "Furniture", "Arifur Rahman", "EMP0014", "IT", "09 Aug 2026", "Permanent", "Active"));
        assignments.add(new Assignment("ASN0013", "AST0003", "HP LaserJet Pro M404dn", "Printer", "Jamil Hossain", "EMP0012", "Operations", "29 Aug 2026", "Permanent", "Active"));
        assignments.add(new Assignment("ASN0012", "AST0009", "Apple MacBook Pro 16\"", "Laptop", "Abdul Alim", "EMP0020", "Operations", "15 Aug 2026", "Permanent", "Active"));
        assignments.add(new Assignment("ASN0011", "AST0008", "Lenovo ThinkPad T14s Gen 4", "Laptop", "Priya Sharma", "EMP0013", "Sales", "01 Aug 2026", "Permanent", "Active"));
        assignments.add(new Assignment("ASN0010", "AST0019", "Epson EB-E01 XGA Projector", "Peripheral", "Farhan Chowdhury", "EMP0016", "IT", "01 Aug 2026", "Permanent", "Active"));
    }

    private void initTransfers() {
        transfers.add(new AssetTransferRecord("TRF0004", "10 Aug 2026", "AST0004", "Apple MacBook Pro 16\" (AST0004)", "Laptop", "Operations", "IT", "Jamil Hossain", "Rahim Ahmed", "Department Transfer", "Admin", "Hardware reallocation"));
        transfers.add(new AssetTransferRecord("TRF0003", "10 Aug 2026", "AST0020", "Dell PowerEdge T350 Server (AST0020)", "Desktop", "Finance", "HR", "Nigar Sultana", "Karim Uddin", "Department Transfer", "Admin", "Server maintenance shift"));
        transfers.add(new AssetTransferRecord("TRF0002", "09 Aug 2026", "AST0017", "Sony WH-1000XM5 Headset (AST0017)", "Peripheral", "Operations", "HR", "Anisur Rahman", "Karim Uddin", "Employee Change", "Admin", "Device handover"));
        transfers.add(new AssetTransferRecord("TRF0001", "08 Aug 2026", "AST0019", "Epson EB-E01 XGA Projector (AST0019)", "Peripheral", "Operations", "Finance", "Anisur Rahman", "Fatima Begum", "Location Relocation", "Admin", "Conference setup"));
    }

    private void initReturns() {
        returns.add(new AssetReturn("RET0007", "10 Aug 2026", "AST0020", "Dell PowerEdge T350 Server", "Desktop", "Karim Uddin", "EMP0018", "HR", "Fair", "Minor Scratches", "Office department reshuffle", "Admin"));
        returns.add(new AssetReturn("RET0006", "09 Aug 2026", "AST0013", "Cisco Catalyst 24-Port Switch", "Networking", "Anisur Rahman", "EMP0018", "Operations", "Good", "No Damage", "Project handover completed", "Admin"));
        returns.add(new AssetReturn("RET0005", "08 Aug 2026", "AST0004", "Apple MacBook Pro 16\"", "Laptop", "Abdul Alim", "EMP0003", "IT", "Good", "No Damage", "Upgraded to newer model", "Admin"));
        returns.add(new AssetReturn("RET0004", "08 Aug 2026", "AST0016", "Logitech MX Master 3S Mouse", "Peripheral", "Fatima Begum", "EMP0019", "Finance", "Good", "No Damage", "Temporary loan returned", "Admin"));
        returns.add(new AssetReturn("RET0003", "07 Aug 2026", "AST0017", "Sony WH-1000XM5 Headset", "Peripheral", "Anisur Rahman", "EMP0018", "Operations", "Good", "No Damage", "Returned in original case", "Admin"));
    }

    private void initDisposals() {
        AssetDisposal d1 = new AssetDisposal("DIS0001", "20 Nov 2023", "AST-OLD-01", "HP ProBook 450 G5 (2018)", "Laptop", "Auction", "₹8,500", "Admin", "Management", "End of 5-year lifecycle; auctioned to staff");
        d1.setDepartment("IT");
        AssetDisposal d2 = new AssetDisposal("DIS0002", "15 Jan 2024", "AST-OLD-02", "Lenovo IdeaPad (2019)", "Laptop", "Scrap", "₹2,200", "Ravi Mehra", "Management", "Hard drive wiped; remaining scrapped");
        d2.setDepartment("HR");
        AssetDisposal d3 = new AssetDisposal("DIS0003", "01 Mar 2024", "AST-OLD-03", "Canon PIXMA MG3670 Printer", "Printer", "Donate", "₹0", "Admin", "Management", "Donated to NGO partner");
        d3.setDepartment("Operations");
        AssetDisposal d4 = new AssetDisposal("DIS0004", "10 Apr 2024", "AST-OLD-04", "Dell OptiPlex Desktop", "Desktop", "Scrap", "₹1,500", "Admin", "Management", "Hardware fault; unrepairable mother-board");
        d4.setDepartment("HR");
        disposals.add(d1);
        disposals.add(d2);
        disposals.add(d3);
        disposals.add(d4);
    }

    private void initEmployees() {
        employees.add(new Employee("EMP0020", "Tariqul Islam", "tariqul.i@example.com", "9330730629", "Sales", "Team Lead", "Sylhet", "Inactive"));
        employees.add(new Employee("EMP0019", "Nigar Sultana", "nigar.s@example.com", "9330730629", "Finance", "Billing Specialist", "Sylhet", "Active"));
        employees.add(new Employee("EMP0018", "Anisur Rahman", "anisur.r@example.com", "933073029", "Operations", "Logistics Lead", "Dhaka HQ", "Active"));
        employees.add(new Employee("EMP0017", "Laila Hasan", "laila.h@example.com", "01844556678", "HR", "Assistant", "Dhaka HQ", "Inactive"));
        employees.add(new Employee("EMP0016", "Farhan Chowdhury", "farhan.c@example.com", "01744556677", "IT", "Network Engineer", "Chittagong", "Active"));
        employees.add(new Employee("EMP0015", "Sumaiya Yasmin", "sumaiya.y@example.com", "01633445570", "Support", "Support Lead", "Chittagong", "Active"));
        employees.add(new Employee("EMP0014", "Arifur Rahman", "arifur.r@example.com", "01533445569", "IT", "QA Engineer", "Dhaka HQ", "Active"));
        employees.add(new Employee("EMP0013", "Mitu Chowdhury", "mitu.c@example.com", "01933445568", "Sales", "Executive", "Dhaka HQ", "Active"));
        employees.add(new Employee("EMP0012", "Jamil Hossain", "jamil.hossain@example.com", "01833445567", "Operations", "Associate", "Sylhet", "Active"));
        employees.add(new Employee("EMP0011", "Tania Sultana", "tania.sultana@example.com", "01733445566", "Marketing", "Manager", "Chittagong", "Active"));
        employees.add(new Employee("EMP0001", "Sarah Jenkins", "s.jenkins@assetos.local", "9876543210", "HR", "HR Director", "Floor 1", "Active"));
        employees.add(new Employee("EMP0003", "Alex Rivera", "a.rivera@assetos.local", "9765432100", "IT", "Chief Architect", "Floor 1", "Active"));
    }

    private void initExpenses() {
        expenses.add(new Expense("EXP0001", "08 Aug 2026", "Repair", "Dell India", "₹1,201", "Cash", "UPS battery servicing", "IT"));
        expenses.add(new Expense("EXP0002", "09 Aug 2026", "Repair", "Samsung Service", "₹1,800", "Cash", "Monitor backlight replacement", "HR"));
        expenses.add(new Expense("EXP0003", "10 Aug 2026", "Software License", "Microsoft Corp", "₹14,500", "Bank Transfer", "Office 365 Enterprise for HR team", "HR"));
        expenses.add(new Expense("EXP0004", "11 Aug 2026", "Maintenance", "Authorized Toyota Service", "₹8,400", "Credit Card", "Vehicle periodic fluid replacement", "Operations"));
        expenses.add(new Expense("EXP0005", "12 Aug 2026", "Hardware Upgrade", "Cisco Direct", "₹12,000", "Bank Transfer", "Switch optical transceiver module", "IT"));
    }

    // ─────────────────────────────────────────────────────────────
    //  EMPLOYEES, EXPENSES, ASSIGNMENTS, MAINTENANCE, DEPRECIATION
    // ─────────────────────────────────────────────────────────────
    public synchronized List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public synchronized void saveEmployee(Employee emp) {
        if (emp == null) return;
        if (emp.getEmployeeId() == null || emp.getEmployeeId().isBlank()) {
            emp.setEmployeeId("EMP" + String.format("%04d", employees.size() + 1));
        }
        employees.removeIf(e -> e.getEmployeeId().equalsIgnoreCase(emp.getEmployeeId().trim()));
        employees.add(0, emp);
    }

    public synchronized boolean deleteEmployee(String id) {
        if (id == null) return false;
        return employees.removeIf(e -> e.getEmployeeId().equalsIgnoreCase(id.trim()));
    }

    public synchronized List<Expense> getAllExpenses() {
        return new ArrayList<>(expenses);
    }

    public synchronized void saveExpense(Expense exp) {
        if (exp == null) return;
        if (exp.getExpenseNo() == null || exp.getExpenseNo().isBlank()) {
            exp.setExpenseNo("EXP" + String.format("%04d", expenses.size() + 1));
        }
        expenses.removeIf(e -> e.getExpenseNo().equalsIgnoreCase(exp.getExpenseNo().trim()));
        expenses.add(0, exp);
    }

    public synchronized boolean deleteExpense(String no) {
        if (no == null) return false;
        return expenses.removeIf(e -> e.getExpenseNo().equalsIgnoreCase(no.trim()));
    }

    public synchronized String nextExpenseNo() {
        return "EXP" + String.format("%04d", expenses.size() + 1);
    }

    public synchronized String nextEmployeeId() {
        return "EMP" + String.format("%04d", employees.size() + 1);
    }

    public synchronized List<Assignment> getAllAssignments() { return new ArrayList<>(assignments); }
    public synchronized void saveAssignment(Assignment a) {
        if (a == null) return;
        if (a.getAssignmentId() == null || a.getAssignmentId().isBlank()) {
            a.setAssignmentId("ASN" + String.format("%03d", assignments.size() + 1));
        }
        assignments.removeIf(x -> x.getAssignmentId().equalsIgnoreCase(a.getAssignmentId().trim()));
        assignments.add(0, a);
    }
    public synchronized boolean deleteAssignment(String id) {
        if (id == null) return false;
        return assignments.removeIf(a -> a.getAssignmentId().equalsIgnoreCase(id.trim()));
    }
    public synchronized boolean returnAssignment(String id) {
        if (id == null) return false;
        Assignment a = assignments.stream().filter(x -> x.getAssignmentId().equalsIgnoreCase(id.trim())).findFirst().orElse(null);
        if (a == null) return false;
        a.setStatus("Returned");
        return true;
    }
    public synchronized String nextAssignmentId() {
        return "ASN" + String.format("%03d", assignments.size() + 1);
    }

    public synchronized List<AssetTransferRecord> getAllTransfers() { return new ArrayList<>(transfers); }
    public synchronized void saveTransfer(AssetTransferRecord t) {
        if (t == null) return;
        if (t.getTransferNo() == null || t.getTransferNo().isBlank()) {
            t.setTransferNo("TRF" + String.format("%04d", transfers.size() + 1));
        }
        transfers.removeIf(existing -> existing.getTransferNo().equalsIgnoreCase(t.getTransferNo().trim()));
        transfers.add(0, t);
    }
    public synchronized boolean deleteTransfer(String no) {
        if (no == null) return false;
        return transfers.removeIf(t -> t.getTransferNo().equalsIgnoreCase(no.trim()));
    }
    public synchronized String nextTransferNo() {
        return "TRF" + String.format("%04d", transfers.size() + 1);
    }

    public synchronized List<AssetReturn> getAllReturns() { return new ArrayList<>(returns); }
    public synchronized AssetReturn getReturnByNo(String no) {
        if (no == null) return null;
        return returns.stream().filter(r -> r.getReturnNo().equalsIgnoreCase(no.trim())).findFirst().orElse(null);
    }
    public synchronized void saveReturn(AssetReturn r) {
        if (r == null) return;
        if (r.getReturnNo() == null || r.getReturnNo().isBlank() || r.getReturnNo().toLowerCase().contains("auto")) {
            r.setReturnNo(nextReturnNo());
        }
        if (r.getDate() == null || r.getDate().isBlank()) {
            r.setDate(getCurrentFormattedDate());
        }
        if (r.getDamageStatus() == null || r.getDamageStatus().isBlank()) {
            r.setDamageStatus("No Damage");
        }
        if (r.getProcessedBy() == null || r.getProcessedBy().isBlank()) {
            r.setProcessedBy("Admin");
        }
        returns.removeIf(existing -> existing.getReturnNo().equalsIgnoreCase(r.getReturnNo().trim()));
        returns.add(0, r);
    }
    public synchronized boolean deleteReturn(String no) {
        if (no == null) return false;
        return returns.removeIf(r -> r.getReturnNo().equalsIgnoreCase(no.trim()));
    }
    public synchronized String nextReturnNo() {
        return "RET" + String.format("%04d", returns.size() + 1);
    }

    public synchronized List<AssetDisposal> getAllDisposals() { return new ArrayList<>(disposals); }
    public synchronized void saveDisposal(AssetDisposal d) {
        if (d == null) return;
        if (d.getDisposalNo() == null || d.getDisposalNo().isBlank()) {
            d.setDisposalNo("DIS" + String.format("%04d", disposals.size() + 1));
        }
        disposals.removeIf(existing -> existing.getDisposalNo().equalsIgnoreCase(d.getDisposalNo().trim()));
        disposals.add(0, d);
    }
    public synchronized boolean deleteDisposal(String no) {
        if (no == null) return false;
        return disposals.removeIf(d -> d.getDisposalNo().equalsIgnoreCase(no.trim()));
    }
    public synchronized String nextDisposalNo() {
        return "DIS" + String.format("%04d", disposals.size() + 1);
    }

    private final List<MaintenanceRecord> maintenanceRecords = new ArrayList<>();
    {
        MaintenanceRecord m1 = new MaintenanceRecord("MNT001", "AST0006", "Modular Workstation Desk", "Furniture", "Height adjustment motor stuck on left pillar", "Facility Maintenance", "High", "Open", "08 Oct 2026", "12 Oct 2026", "");
        m1.setDepartment("Operations");
        MaintenanceRecord m2 = new MaintenanceRecord("MNT002", "AST0010", "Dell Latitude 5440 Laptop", "Laptop", "Display flickering; diagnostic requested", "Ravi Mehra", "High", "In Progress", "07 Oct 2026", "13 Oct 2026", "");
        m2.setDepartment("IT");
        MaintenanceRecord m3 = new MaintenanceRecord("MNT003", "AST0018", "Toyota Innova Crysta (Company Car)", "Vehicle", "Scheduled periodic service & brake inspection", "Authorized Toyota Service", "Medium", "In Progress", "06 Oct 2026", "11 Oct 2026", "");
        m3.setDepartment("Operations");
        MaintenanceRecord m4 = new MaintenanceRecord("MNT004", "AST0020", "Dell PowerEdge T350 Server", "Desktop", "Redundant power supply PSU 2 amber indicator", "Dell Enterprise Care", "Critical", "In Progress", "05 Oct 2026", "10 Oct 2026", "");
        m4.setDepartment("HR");
        MaintenanceRecord m5 = new MaintenanceRecord("MNT005", "AST0017", "Sony WH-1000XM5 Headset", "Peripheral", "Microphone audio distortion during calls", "AudioTech Solutions", "Medium", "Open", "09 Oct 2026", "14 Oct 2026", "");
        m5.setDepartment("HR");
        maintenanceRecords.add(m1);
        maintenanceRecords.add(m2);
        maintenanceRecords.add(m3);
        maintenanceRecords.add(m4);
        maintenanceRecords.add(m5);
    }
    public synchronized List<MaintenanceRecord> getAllMaintenanceRecords() { return new ArrayList<>(maintenanceRecords); }
    public synchronized void saveMaintenanceRecord(MaintenanceRecord r) {
        if (r == null) return;
        if (r.getTicketId() == null || r.getTicketId().isBlank()) {
            r.setTicketId("MNT" + String.format("%03d", maintenanceRecords.size() + 1));
        }
        maintenanceRecords.removeIf(x -> x.getTicketId().equalsIgnoreCase(r.getTicketId().trim()));
        maintenanceRecords.add(0, r);
    }
    public synchronized boolean deleteMaintenanceRecord(String id) {
        if (id == null) return false;
        return maintenanceRecords.removeIf(r -> r.getTicketId().equalsIgnoreCase(id.trim()));
    }
    public synchronized String nextMaintenanceId() {
        return "MNT" + String.format("%03d", maintenanceRecords.size() + 1);
    }

    public List<DepreciationRecord> getDepreciationRecords() {
        List<DepreciationRecord> list = new ArrayList<>();
        list.add(new DepreciationRecord("AST0020", "Dell PowerEdge T350 Server", "01 Dec 2024", "₹2,10,000", "SLM", "5 Yrs", "01 Dec 2024", "₹70,651", "₹1,39,349", "Under Maintenance", "HR", "Desktop"));
        list.add(new DepreciationRecord("AST0019", "Epson EB-E01 XGA Projector", "01 Nov 2024", "₹34,500", "SLM", "5 Yrs", "01 Nov 2024", "₹11,820", "₹22,680", "Assigned", "Finance", "Peripheral"));
        list.add(new DepreciationRecord("AST0018", "Toyota Innova Crysta (Company Car)", "15 May 2023", "₹21,50,000", "SLM", "5 Yrs", "15 May 2023", "₹13,87,690", "₹7,62,310", "Under Maintenance", "Operations", "Vehicle"));
        list.add(new DepreciationRecord("AST0017", "Sony WH-1000XM5 Headset", "01 Nov 2024", "₹29,990", "WDV", "12%", "02 Jun 2025", "₹4,238", "₹25,752", "Assigned", "HR", "Peripheral"));
        list.add(new DepreciationRecord("AST0016", "Logitech MX Master 3S Mouse", "15 Feb 2025", "₹8,995", "WDV", "30%", "15 Feb 2025", "₹3,706", "₹5,289", "Assigned", "Operations", "Peripheral"));
        list.add(new DepreciationRecord("AST0015", "APC 3KVA Online UPS", "10 Jun 2024", "₹48,000", "SLM", "6 Yrs", "10 Jun 2024", "₹15,545", "₹32,455", "Available", "IT", "Networking"));
        list.add(new DepreciationRecord("AST0014", "Fortinet FortiGate 60F Firewall", "01 Jul 2024", "₹89,000", "SLM", "5 Yrs", "01 Jul 2024", "₹37,079", "₹51,921", "Assigned", "IT", "Networking"));
        list.add(new DepreciationRecord("AST0013", "Cisco Catalyst 24-Port Switch", "15 Aug 2024", "₹54,000", "SLM", "6 Yrs", "15 Aug 2024", "₹17,497", "₹36,503", "Available", "IT", "Networking"));
        list.add(new DepreciationRecord("AST0012", "Apple iPhone 15 Pro", "20 Mar 2025", "₹1,34,900", "Not Applicable", "—", "20 Mar 2025", "₹0", "₹1,34,900", "Available", "Sales", "Mobile"));
        list.add(new DepreciationRecord("AST0011", "Samsung Galaxy S24 Ultra", "01 Apr 2025", "₹1,29,999", "SLM", "5 Yrs", "01 Apr 2025", "₹33,762", "₹96,237", "Available", "Sales", "Mobile"));
        return list;
    }

    public DashboardStats getDashboardStats() {
        long total = assets.size();
        long avail = assets.stream().filter(a -> "Available".equalsIgnoreCase(a.getStatus())).count();
        long assign = assets.stream().filter(a -> "Assigned".equalsIgnoreCase(a.getStatus())).count();
        long maint = Math.max(maintenanceRecords.size(), assets.stream().filter(a -> "Under Maintenance".equalsIgnoreCase(a.getStatus())).count());
        long disp = Math.max(disposals.size(), assets.stream().filter(a -> "Disposed".equalsIgnoreCase(a.getStatus()) || "Retired".equalsIgnoreCase(a.getStatus())).count());

        return new DashboardStats(
                total,
                avail,
                assign,
                maint,
                disp,
                "₹37,46,384",
                "₹21,20,563",
                2
        );
    }

    public Map<String, Integer> getCategoryCounts() {
        Map<String, Integer> counts = new LinkedHashMap<>();
        for (Asset a : assets) {
            String cat = a.getCategory() != null ? a.getCategory() : "Other";
            counts.put(cat, counts.getOrDefault(cat, 0) + 1);
        }
        return counts;
    }

    public Map<String, Long> getCategoryValues() {
        Map<String, Long> values = new LinkedHashMap<>();
        for (Asset a : assets) {
            String cat = a.getCategory() != null ? a.getCategory() : "Other";
            long cost = parseCurrencyAmount(a.getPurchaseCost());
            values.put(cat, values.getOrDefault(cat, 0L) + cost);
        }
        return values;
    }

    public Map<String, Integer> getDepartmentCounts() {
        Map<String, Integer> depts = new LinkedHashMap<>();
        for (Asset a : assets) {
            String d = a.getDepartment() != null && !a.getDepartment().isBlank() ? a.getDepartment() : "General";
            depts.put(d, depts.getOrDefault(d, 0) + 1);
        }
        return depts;
    }

    public Map<String, Integer> getConditionCounts() {
        Map<String, Integer> conds = new LinkedHashMap<>();
        conds.put("New", 0);
        conds.put("Good", 0);
        conds.put("Fair", 0);
        conds.put("Damaged", 0);
        for (Asset a : assets) {
            String c = a.getCondition() != null ? a.getCondition() : "Good";
            conds.put(c, conds.getOrDefault(c, 0) + 1);
        }
        return conds;
    }

    private long parseCurrencyAmount(String formatted) {
        if (formatted == null) return 0L;
        String clean = formatted.replaceAll("[^0-9]", "");
        if (clean.isBlank()) return 0L;
        try {
            return Long.parseLong(clean);
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    public List<LocationStat> getLocationStats() {
        Map<String, Integer> counts = new LinkedHashMap<>();
        Map<String, Long> sums = new LinkedHashMap<>();
        for (Asset a : assets) {
            String loc = a.getLocation() != null && !a.getLocation().isBlank() ? a.getLocation() : "Warehouse";
            counts.put(loc, counts.getOrDefault(loc, 0) + 1);
            sums.put(loc, sums.getOrDefault(loc, 0L) + parseCurrencyAmount(a.getPurchaseCost()));
        }
        List<LocationStat> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            long total = sums.getOrDefault(entry.getKey(), 0L);
            list.add(new LocationStat(entry.getKey(), entry.getValue(), "₹" + String.format("%,d", total)));
        }
        return list;
    }

    public List<MaintenanceAlert> getMaintenanceAlerts() {
        List<MaintenanceAlert> alerts = new ArrayList<>();
        for (MaintenanceRecord r : maintenanceRecords) {
            alerts.add(new MaintenanceAlert(r.getAssetName(), r.getPriority() + " Priority", r.getStatus(), "Est: " + r.getEstimatedCompletion()));
        }
        return alerts;
    }

    public List<Asset> getRecentlyAddedAssets(int limit) {
        int end = Math.min(limit, assets.size());
        return new ArrayList<>(assets.subList(0, end));
    }

    public List<Assignment> getRecentAssignments(int limit) {
        int end = Math.min(limit, assignments.size());
        return new ArrayList<>(assignments.subList(0, end));
    }

    public List<AssetReturn> getRecentReturns(int limit) {
        int end = Math.min(limit, returns.size());
        return new ArrayList<>(returns.subList(0, end));
    }

    public List<TimelineActivity> getTimelineActivities() {
        List<TimelineActivity> list = new ArrayList<>();

        // Real-time Assignments
        for (int i = 0; i < Math.min(4, assignments.size()); i++) {
            Assignment a = assignments.get(i);
            list.add(new TimelineActivity(
                    "ASSIGNMENT",
                    "👤",
                    "icon-assignment",
                    "Asset Assigned to " + a.getEmployeeName(),
                    "Assignment #" + a.getAssignmentId() + " • By " + (a.getAssignedBy() != null ? a.getAssignedBy() : "Admin"),
                    a.getAssignmentDate()
            ));
        }

        // Real-time Returns
        for (int i = 0; i < Math.min(3, returns.size()); i++) {
            AssetReturn r = returns.get(i);
            list.add(new TimelineActivity(
                    "RETURN",
                    "🔄",
                    "icon-return",
                    "Asset Returned by " + r.getReturnedBy(),
                    r.getAssetName() + " (" + r.getCondition() + ") • By Admin",
                    r.getDate()
            ));
        }

        // Real-time Maintenance
        for (int i = 0; i < Math.min(3, maintenanceRecords.size()); i++) {
            MaintenanceRecord m = maintenanceRecords.get(i);
            list.add(new TimelineActivity(
                    "MAINTENANCE",
                    "🛠️",
                    "icon-maintenance",
                    "Maintenance: " + m.getAssetName(),
                    m.getIssueDescription() + " • Status: " + m.getStatus() + " • By Admin",
                    m.getReportedDate()
            ));
        }

        // Real-time Assets Added
        for (int i = 0; i < Math.min(4, assets.size()); i++) {
            Asset ast = assets.get(i);
            list.add(new TimelineActivity(
                    "ASSET",
                    "📦",
                    "icon-asset",
                    "Asset Added: " + ast.getName(),
                    ast.getAssetTag() + " • " + ast.getCategory() + " • By Admin",
                    ast.getPurchaseDate()
            ));
        }

        return list;
    }

    public List<AssetActivity> getRecentActivities() {
        List<AssetActivity> activities = new ArrayList<>();
        int count = Math.min(8, assets.size());
        for (int i = 0; i < count; i++) {
            Asset a = assets.get(i);
            activities.add(new AssetActivity(
                    a.getAssetTag(),
                    a.getName(),
                    a.getCategory(),
                    a.getAssignedTo() != null ? a.getAssignedTo() : "Unassigned",
                    a.getDepartment() != null ? a.getDepartment() : "General",
                    a.getStatus() != null ? a.getStatus() : "Available",
                    a.getPurchaseDate() != null ? a.getPurchaseDate() : "Recently"
            ));
        }
        return activities;
    }
}
