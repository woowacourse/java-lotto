package domain;

public class Money implements Comparable<Money> {
	private final double money;

	public Money(double money) {
		this.money = money;
	}

	public double getMoney() {
		return money;
	}

	@Override
	public int compareTo(Money other) {
		return (int)(this.money - other.money);
	}
}
