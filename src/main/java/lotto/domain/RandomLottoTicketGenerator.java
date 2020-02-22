package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomLottoTicketGenerator implements LottoTicketGenerator {
	@Override
	public LottoTicket create() {
		return new LottoTicket(createRandomBalls());
	}

	private List<Ball> createRandomBalls() {
		List<Ball> balls = new ArrayList<>(Ball.values());
		Collections.shuffle(balls);

		return balls.stream()
			.sorted()
			.limit(LottoTicket.BALL_COUNT)
			.collect(Collectors.toList());
	}
}
