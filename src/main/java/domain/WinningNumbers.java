package domain;

public class WinningNumbers {
    private final Ticket winTicket;
    private final LottoNumber bonusNumber;

    public WinningNumbers(Ticket winTicket, LottoNumber bonusNumber) {
        checkContainsNumber(winTicket, bonusNumber);
        this.winTicket = winTicket;
        this.bonusNumber = bonusNumber;
    }

    private void checkContainsNumber(Ticket winTicket, LottoNumber bonusNumber) {
        if (winTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호가 중복됩니다.");
        }
    }

    public Rank getTicketRank(Ticket ticket) {
        int count = winTicket.getSameNumberCount(ticket);
        return getRank(ticket, count);
    }

    private Rank getRank(Ticket ticket, int count) {
        if (isSecondRank(ticket, count)) {
            return Rank.SECOND;
        }
        return Rank.value(count, false);
    }

    private boolean isSecondRank(Ticket ticket, int count) {
        return count == 5 && ticket.contains(bonusNumber);
    }
}
