package domain;

public class Money {
    private final int originMoney;

    public Money(int originMoney) {
        validate(originMoney);
        this.originMoney = originMoney;
    }

    private void validate(int originMoney) {
        if (originMoney < 0) {
            throw new IllegalArgumentException("돈은 양의 정수여야 합니다.");
        }
        if (originMoney < 1000) {
            throw new IllegalArgumentException("돈은 1,000원 이상이어야 합니다.");
        }
    }

    public int getBuyableLottoCount() {
        return originMoney / 1000;
    }

    public int getSpentMoney() {
        return (originMoney / 1000) * 1000;
    }

    public int getChange() {
        return originMoney % 1000;
    }
}
