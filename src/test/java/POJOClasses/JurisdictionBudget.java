package POJOClasses;

public class JurisdictionBudget {
	
    private String budgetYear;
    private String annualAllowanceBudget;
    private String reserveAdjustedAllowanceBudget;
    private String id;

    // Getters and setters
    public String getBudgetYear() {
        return budgetYear;
    }

    public void setBudgetYear(String budgetYear) {
        this.budgetYear = budgetYear;
    }

    public String getAnnualAllowanceBudget() {
        return annualAllowanceBudget;
    }

    public void setAnnualAllowanceBudget(String annualAllowanceBudget) {
        this.annualAllowanceBudget = annualAllowanceBudget;
    }

    public String getReserveAdjustedAllowanceBudget() {
        return reserveAdjustedAllowanceBudget;
    }

    public void setReserveAdjustedAllowanceBudget(String reserveAdjustedAllowanceBudget) {
        this.reserveAdjustedAllowanceBudget = reserveAdjustedAllowanceBudget;
    }
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
