package AssetOS.model;

public class CompanyProfile {
    private String companyName;
    private String gstNumber;
    private String phone;
    private String email;
    private String address;
    private String financialYear;

    public CompanyProfile() {}

    public CompanyProfile(String companyName, String gstNumber, String phone, String email, String address, String financialYear) {
        this.companyName = companyName;
        this.gstNumber = gstNumber;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.financialYear = financialYear;
    }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getGstNumber() { return gstNumber; }
    public void setGstNumber(String gstNumber) { this.gstNumber = gstNumber; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getFinancialYear() { return financialYear; }
    public void setFinancialYear(String financialYear) { this.financialYear = financialYear; }
}
