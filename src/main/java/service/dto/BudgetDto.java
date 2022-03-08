package service.dto;

public class BudgetDto {
    private int budgetAmount;

    public BudgetDto(int budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public int getBudgetAmount() {
        return budgetAmount;
    }
}
