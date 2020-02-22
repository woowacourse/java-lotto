package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RandomLottoTicketGenerator implements LottoTicketGenerator {
	@Override
	public LottoTicket create() {
		return new LottoTicket(createRandomBalls());
	}

	private Set<LottoBall> createRandomBalls() {
		List<LottoBall> balls = new ArrayList<>(LottoBall.values());
		Collections.shuffle(balls);

		return balls.stream()
			.limit(LottoTicket.BALL_COUNT)
			.collect(Collectors.toSet());
	}
}
