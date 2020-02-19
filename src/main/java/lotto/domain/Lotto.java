package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
	private static final String DUPLICATED_NUMBER_EXCEPTION_MESSAGE = "중복된 볼이 포함";
	private static final int BALL_COUNT = 6;
	public static final String BALL_COUNT_EXCEPTION_MESSAGE = "로또 볼의 갯수가 적절하지 않습니다.";

	private final List<Ball> balls;

	public Lotto(List<Ball> balls) {
		validateDuplication(balls);
		validateBallCount(balls);
		this.balls = Collections.unmodifiableList(balls);
	}

	private void validateBallCount(List<Ball> balls) {
		if (balls.size() != BALL_COUNT) {
			throw new IllegalArgumentException(BALL_COUNT_EXCEPTION_MESSAGE);
		}
	}

	private void validateDuplication(List<Ball> balls) {
		Set<Ball> distinctBalls = new HashSet<>(balls);
		if (distinctBalls.size() != balls.size()) {
			throw new IllegalArgumentException(DUPLICATED_NUMBER_EXCEPTION_MESSAGE);
		}
	}

	public boolean contains(Ball ball) {
		return balls.contains(ball);
	}

	public int countCommonBalls(Lotto lotto) {
		List<Ball> sameBalls = new ArrayList<>(balls);
		sameBalls.retainAll(lotto.balls);
		return sameBalls.size();
	}
}
