package lotto.domain;

public class Budget {
    private int budget;

    public Budget(int budget) {
        validateNotNegative(budget);
        this.budget = budget;
    }

    private void validateNotNegative(int budget) {
        if (budget < 0) {
            throw new IllegalArgumentException("잘못된 구입 금액입니다. 양수를 입력해주세요.");
        }
    }
}