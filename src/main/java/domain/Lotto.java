package domain;

import java.util.List;

public class Lotto {
	private final Balls balls;

	public Lotto(LottoGenerator lottoGenerator) {
		final List<Integer> numbers = lottoGenerator.generate();
		this.balls = new Balls(numbers);
	}

	public int countMatches(Balls balls) {
		return balls.countMatches(this.balls);
	}
}
