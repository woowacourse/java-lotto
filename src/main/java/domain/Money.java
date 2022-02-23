package domain;

public class Money {

    private int money;

    public Money(final String input) {
        validateInput(input);
        this.money = Integer.parseInt(input);
        validateMoney(this.money);
    }

    private void validateInput(final String input) {
        if (input == null) {
            throw new IllegalArgumentException("Null을 입력할 수 없습니다.");
        }

        if (!input.matches("^[1-9]([0-9]*)$")) {
            throw new IllegalArgumentException("입력한 값이 숫자의 형태가 아닙니다.");
        }
    }

    private void validateMoney(final int money) {
        if (money < 1000) {
            throw new IllegalArgumentException("1000원 미만은 입력할 수 없습니다.");
        }
    }

    public int calculateCounts() {

        return this.money / 1_000;
    }


    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final Money money1 = (Money) object;

        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return money;
    }

    public double calculateProfit(final int totalWinPrice) {
        return (double) totalWinPrice / this.money;
    }
}
