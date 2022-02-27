package domain.strategy;

import java.util.List;

public class CustomPurchaseStrategy implements PurchaseStrategy {

    private final List<Integer> customNumbers;

    public CustomPurchaseStrategy(List<Integer> customNumbers) {
        this.customNumbers = customNumbers;
    }

    @Override
    public List<Integer> generateNumbers() {
        return customNumbers;
    }
}
