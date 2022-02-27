package domain;

public class WinningNumbers {
    private final Ticket winTicket;
    private final LottoNumber bonusNumber;

    public WinningNumbers(Ticket winTicket, LottoNumber bonusNumber) {
        checkDuplicatedNumber(winTicket, bonusNumber);
        this.winTicket = winTicket;
        this.bonusNumber = bonusNumber;
    }

    private void checkDuplicatedNumber(Ticket winTicket, LottoNumber bonusNumber) {
        if (winTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호가 중복되지 않게 입력해주세요.");
        }
    }

    public Rank getTicketRank(Ticket ticket) {
        int count = winTicket.getSameNumberCount(ticket);
        return getRank(ticket, count);
    }

    private Rank getRank(Ticket ticket, int count) {
        return Rank.value(count, ticket.contains(bonusNumber));
    }
}
