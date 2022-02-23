package domain;

import domain.strategy.PurchaseStrategy;

import java.util.ArrayList;
import java.util.List;

public class CustomPurchaseStrategy implements PurchaseStrategy {

    private List<Integer> customNumbers = new ArrayList<>();

    public CustomPurchaseStrategy(List<Integer> customNumbers) {
        this.customNumbers = customNumbers;
    }

    @Override
    public List<Integer> getNumbers() {
        return customNumbers;
    }
}
