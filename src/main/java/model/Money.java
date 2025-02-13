package model;

public record Money(
        int value
) {
    public Money {
        if (value % 1000 != 0) {
            throw new IllegalArgumentException("금액은 1000원 단위로 입력해 주세요.");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("금액은 0원 이상이여야 합니다.");
        }
    }

    public int computeTicketCount() {
        return value / 1000;
    }
}
