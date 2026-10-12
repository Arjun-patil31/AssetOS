package AssetOS.model;

public class Vendor {
    private String vendorCode;
    private String vendorName;
    private String contactPerson;
    private String mobile;
    private String email;
    private String gstNumber;
    private String address;
    private String remarks;
    private String department = "IT";

    public Vendor() {}

    public Vendor(String vendorCode, String vendorName, String contactPerson,
                  String mobile, String email, String gstNumber,
                  String address, String remarks) {
        this.vendorCode = vendorCode;
        this.vendorName = vendorName;
        this.contactPerson = contactPerson;
        this.mobile = mobile;
        this.email = email;
        this.gstNumber = gstNumber;
        this.address = address;
        this.remarks = remarks;
    }

    private String category = "Hardware";
    private String status = "Active";
    private String createdDate = "2024-01-15";

    public String getVendorCode() { return vendorCode; }
    public void setVendorCode(String vendorCode) { this.vendorCode = vendorCode; }

    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }
    public String getName() { return vendorName; }
    public void setName(String name) { this.vendorName = name; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public String getPhone() { return mobile; }
    public void setPhone(String phone) { this.mobile = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGstNumber() { return gstNumber; }
    public void setGstNumber(String gstNumber) { this.gstNumber = gstNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getDepartment() { return department != null ? department : "IT"; }
    public void setDepartment(String department) { this.department = department; }

    public String getCategory() { return category != null ? category : "Hardware"; }
    public void setCategory(String category) { this.category = category; }

    public String getStatus() { return status != null ? status : "Active"; }
    public void setStatus(String status) { this.status = status; }

    public String getCreatedDate() { return createdDate != null ? createdDate : "2024-01-15"; }
    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }
}
