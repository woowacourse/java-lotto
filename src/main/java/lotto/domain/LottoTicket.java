package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class LottoTicket {
	private static final String DUPLICATED_NUMBER_EXCEPTION_MESSAGE = "중복된 볼이 포함";
	private static final String BALL_COUNT_EXCEPTION_MESSAGE = "로또 볼의 갯수가 적절하지 않습니다.";
	private static final String NULL_LOTTO_EXCEPTION_MESSAGE = "객체에 null인 값은 들어갈수 없다네";
	static final int BALL_COUNT = 6;

	private final List<Ball> balls;

	public LottoTicket(List<Ball> balls) {
		validate(balls);
		this.balls = Collections.unmodifiableList(new ArrayList<>(balls));
	}

	private void validate(List<Ball> balls) {
		validateNull(balls);
		validateDuplication(balls);
		validateBallCount(balls);
	}

	private void validateNull(List<Ball> balls) {
		if (Objects.isNull(balls)) {
			throw new NullPointerException(NULL_LOTTO_EXCEPTION_MESSAGE);
		}
	}

	private void validateDuplication(List<Ball> balls) {
		Set<Ball> distinctBalls = new HashSet<>(balls);
		if (distinctBalls.size() != balls.size()) {
			throw new IllegalArgumentException(DUPLICATED_NUMBER_EXCEPTION_MESSAGE);
		}
	}

	private void validateBallCount(List<Ball> balls) {
		if (balls.size() != BALL_COUNT) {
			throw new IllegalArgumentException(BALL_COUNT_EXCEPTION_MESSAGE);
		}
	}

	public boolean contains(Ball ball) {
		return balls.contains(ball);
	}

	public int countMatch(LottoTicket lottoTicket) {
		List<Ball> sameBalls = new ArrayList<>(balls);
		sameBalls.retainAll(lottoTicket.balls);
		return sameBalls.size();
	}

	public List<Ball> getBalls() {
		return Collections.unmodifiableList(new ArrayList<>(balls));
	}
}
