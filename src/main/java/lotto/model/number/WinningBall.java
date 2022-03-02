package lotto.model.number;

import java.util.Objects;

public class WinningBall {

	private final LottoBall lottoBall;

	private WinningBall(LottoBall lottoBall) {
		this.lottoBall = lottoBall;
	}

	public static WinningBall from(String input) {
		LottoBall lottoBall = LottoBall.from(input.trim());
		return new WinningBall(lottoBall);
	}

	public boolean match(int number) {
		return this.lottoBall.getNumber() == number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		WinningBall that = (WinningBall)o;
		return lottoBall.equals(that.lottoBall);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoBall);
	}
}
