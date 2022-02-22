package domain;

import java.util.List;

public class Lotto {
	private final Balls balls;

	public Lotto(LottoGenerator lottoGenerator) {
		final List<Integer> numbers = lottoGenerator.generate();
		this.balls = new Balls(numbers);
	}

	public Rank getRank(Balls answer, Ball bonusBall) {
		int matchCount = answer.countMatches(this.balls);
		boolean bonusBallMatched = this.balls.contains(bonusBall);
		return Rank.of(matchCount, bonusBallMatched);
	}
}
