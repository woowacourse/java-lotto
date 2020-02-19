package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
	private static final String DUPLICATED_NUMBER_EXCEPTION_MESSAGE = "중복된 볼이 포함";

	private final List<Ball> balls;

	public Lotto(List<Ball> balls) {
		validateBalls(balls);
		this.balls = Collections.unmodifiableList(balls);
	}

	private void validateBalls(List<Ball> balls) {
		Set<Ball> distinctBalls = new HashSet<>(balls);
		if (distinctBalls.size() != balls.size()) {
			throw new IllegalArgumentException(DUPLICATED_NUMBER_EXCEPTION_MESSAGE);
		}
	}

	public List<Ball> getBalls() {
		return balls;
	}
}
