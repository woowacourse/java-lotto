package lotto.domain;

class Money {
    private int money;

    public Money(String input) {
        isNumberFormat(input);
        int value = Integer.parseInt(input);
        isUnder1000(value);
        isDivideBy1000(value);
        this.money = value;
    }

    private void isNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아니에요");
        }
    }

    private void isUnder1000(int value) {
        if (value < 1000) {
            throw new IllegalArgumentException("1000원 미만인 입력 금액에 대한 예외처리");
        }
    }

    private void isDivideBy1000(int value) {
        if (value % 1000 != 0) {
            throw new IllegalArgumentException("거스름돈이 발생하는 경우에 대한 예외처리");
        }
    }

    public int getMoney() {
        return money;
    }
}
