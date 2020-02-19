package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoFactory implements LottoFactory {

	@Override
	public Lotto create() {
		return new Lotto(createRandomBalls());
	}

	private List<Ball> createRandomBalls() {
		List<Ball> random = new ArrayList<>();
		for(int i = 1; i <= 45; i++) {
			random.add(Ball.of(i));
		}
		Collections.shuffle(random);
		return random.subList(0,6);
	}

}
