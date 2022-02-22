package domain;

import java.util.List;

public class Lotto {
	private final Balls balls;

	public Lotto(LottoGenerator lottoGenerator) {
		final List<Integer> numbers = lottoGenerator.generate();
		this.balls = new Balls(numbers);
	}

	public Rank countMatches(Balls balls, Ball bonusBall) {
		int matchCount = balls.countMatches(this.balls);
		boolean bonusBallMatched = balls.contains(bonusBall);
		return Rank.of(matchCount, bonusBallMatched);
	}
}
