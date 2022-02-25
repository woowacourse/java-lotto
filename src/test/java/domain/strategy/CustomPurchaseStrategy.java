package domain.strategy;

import java.util.List;

public class CustomPurchaseStrategy implements PurchaseStrategy {

    private List<Integer> customNumbers;

    public CustomPurchaseStrategy(List<Integer> customNumbers) {
        this.customNumbers = customNumbers;
    }

    @Override
    public List<Integer> getNumbers() {
        return customNumbers;
    }
}
