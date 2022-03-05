package lotto.domain.winning;

import java.util.List;
import java.util.Optional;

import lotto.domain.ball.Ball;
import lotto.domain.ball.BallStorage;
import lotto.domain.ticket.Ticket;
import lotto.utils.Rank;

public class WinningTicket {

    private final Ticket ticket;
    private final Ball bonusBall;

    public WinningTicket(final List<Integer> numbers, final int bonusNumber) {
        WinningTicketValidator.validateWinningTicket(numbers, bonusNumber);
        this.ticket = new Ticket(numbers);
        this.bonusBall = BallStorage.getBall(bonusNumber);
    }

    public Optional<Rank> calculateRank(final Ticket anotherTicket) {
        final int matchCount = anotherTicket.countMatches(this.ticket);
        final boolean bonusBallMatched = anotherTicket.contains(this.bonusBall);
        return Rank.of(matchCount, bonusBallMatched);
    }

}
