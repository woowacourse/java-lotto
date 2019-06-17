package lotto.domain;

public class WinningLotto {
    private final Ticket winningTicket;
    private final LottoNumber bonus;

    public WinningLotto(Ticket winningTicket, LottoNumber bonus) {
        this.winningTicket = winningTicket;
        this.bonus = bonus;
    }

    public int match(Ticket lotto) {
        return (int) lotto.ticketNumbers().numbers().stream()
                .filter(ticketNumber -> winningTicket.contains(ticketNumber))
                .count();
    }

    public boolean bonus(Ticket lotto) {
        return lotto.ticketNumbers().contains(bonus);
    }
}
