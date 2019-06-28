package lotto.domain;

import java.util.List;

public class WinningLotto {
    private final Ticket winningTicket;
    private final LottoNumber bonus;

    public WinningLotto(Ticket winningTicket, LottoNumber bonus) {
        this.winningTicket = winningTicket;
        this.bonus = bonus;
    }

    public int match(Ticket lotto) {
        return (int) lotto.ticketNumbers().stream()
                .filter(ticketNumber -> winningTicket.contains(ticketNumber))
                .count();
    }

    public boolean bonus(Ticket lotto) {
        return lotto.ticketNumbers().contains(bonusNumbers());
    }

    public List<Integer> ticketNumbers() {
        return winningTicket.ticketNumbers();
    }

    public int bonusNumbers() {
        return bonus.toInt();
    }
}
