package domain;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Money money1 = (Money)o;
		return Double.compare(money1.money, money) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money);
	}
}
