package lotto.domain.lotto.dto;

public class MoneyDTO {
    private int money;

    public void set(String money) {
        this.money = Integer.parseInt(money);
    }

    public int getMoney() {
        return money;
    }
}
