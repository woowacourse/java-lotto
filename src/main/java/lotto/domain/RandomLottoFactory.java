package lotto.domain;

import java.util.Collections;
import java.util.List;

public class RandomLottoFactory implements LottoFactory {
	private static final int FROM_INDEX = 0;
	private static final int TO_INDEX = 6;

	@Override
	public Lotto create() {
		return new Lotto(createRandomBalls());
	}

	private List<Ball> createRandomBalls() {
		List<Ball> randomBalls = Ball.getBallList();
		Collections.shuffle(randomBalls);
		return randomBalls.subList(FROM_INDEX, TO_INDEX);
	}
}
