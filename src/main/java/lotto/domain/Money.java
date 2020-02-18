package lotto.domain;

public class Money {
	private int money;

	public Money(String money) {
		try {
			this.money = Integer.parseInt(money);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("구입금액을 다시 입력하세요");
		}
	}

	public int getMoney() {
		return money;
	}
}
