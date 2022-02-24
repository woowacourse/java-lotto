package lotto.domain;

public class WinningNumbers {
    private static final String REQUEST_NOT_DUPLICATE_NUMBER = "당첨 번호와 보너스 번호가 중복되지 않게 입력해주세요.";
    private final Ticket winTicket;
    private final LottoNumber bonusNumber;

    public WinningNumbers(Ticket winTicket, LottoNumber bonusNumber) {
        checkContainsNumber(winTicket, bonusNumber);
        this.winTicket = winTicket;
        this.bonusNumber = bonusNumber;
    }

    private void checkContainsNumber(Ticket winTicket, LottoNumber bonusNumber) {
        if (winTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException(REQUEST_NOT_DUPLICATE_NUMBER);
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
        return count == Rank.SECOND.getCount() && ticket.contains(bonusNumber);
    }
}
