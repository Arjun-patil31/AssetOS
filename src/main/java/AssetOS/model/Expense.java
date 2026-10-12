package AssetOS.model;

public class Expense {
    private String expenseNo;
    private String date;
    private String category;
    private String paidTo;
    private String amount;
    private String mode;
    private String remarks;
    private String department = "Operations";

    public Expense() {}

    public Expense(String expenseNo, String date, String category, String paidTo, String amount, String mode, String remarks) {
        this(expenseNo, date, category, paidTo, amount, mode, remarks, "Operations");
    }

    public Expense(String expenseNo, String date, String category, String paidTo, String amount, String mode, String remarks, String department) {
        this.expenseNo = expenseNo;
        this.date = date;
        this.category = category;
        this.paidTo = paidTo;
        this.amount = amount;
        this.mode = mode;
        this.remarks = remarks;
        this.department = department != null ? department : "Operations";
    }

    public String getExpenseNo() { return expenseNo; }
    public void setExpenseNo(String expenseNo) { this.expenseNo = expenseNo; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getPaidTo() { return paidTo; }
    public void setPaidTo(String paidTo) { this.paidTo = paidTo; }

    public String getAmount() { return amount; }
    public void setAmount(String amount) { this.amount = amount; }

    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getDepartment() { return department != null ? department : "Operations"; }
    public void setDepartment(String department) { this.department = department; }

    public String getDescription() { return remarks != null ? remarks : ""; }
    public void setDescription(String description) { this.remarks = description; }
}
