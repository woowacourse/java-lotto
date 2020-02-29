package lotto.domain.customer;

import java.util.List;

public class Customer {
    private final Money money;
    private final List<List<Integer>> manualNumbers;

    public Customer(Money money, List<List<Integer>> manualNumbers) {
        this.money = money;
        this.manualNumbers = manualNumbers;
    }

    public Money getMoney() {
        return money;
    }

    public List<List<Integer>> getManualNumbers() {
        return manualNumbers;
    }
}
