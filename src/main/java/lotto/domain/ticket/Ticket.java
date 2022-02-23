package lotto.domain.ticket;

import java.util.List;

import lotto.domain.ball.Ball;
import lotto.domain.ball.Balls;
import lotto.domain.rank.Rank;
import lotto.domain.ticket.generator.TicketGenerator;

public class Ticket {

	private final Balls balls;

	public Ticket(final TicketGenerator ticketGenerator) {
		final List<Integer> numbers = ticketGenerator.generate();
		this.balls = new Balls(numbers);
	}

	public Rank getRank(final Balls answer, final Ball bonusBall) {
		final int matchCount = answer.countMatches(this.balls);
		final boolean bonusBallMatched = this.balls.contains(bonusBall);
		return Rank.of(matchCount, bonusBallMatched);
	}

	public List<Integer> getBallNumbers() {
		return balls.getBallNumbers();
	}

}
