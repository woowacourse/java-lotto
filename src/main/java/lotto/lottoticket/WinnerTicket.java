package lotto.lottoticket;

import java.util.List;
import java.util.Objects;

public class WinnerTicket extends LottoTicketMaker {
    private final List<LottoNumber> winnerTicket;

    public WinnerTicket(String numbers) {
        this.winnerTicket = makeLottoNumbers(numbers);
    }

    public boolean containsSameNumber(LottoNumber number) {
        return this.winnerTicket.contains(number);
    }

    public int findMatchCount(LottoTicket lottoTicket) {
        int matchCount = 0;
        for (LottoNumber number : winnerTicket) {
            matchCount += lottoTicket.countWithContaining(number);
        }
        return matchCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinnerTicket that = (WinnerTicket) o;
        return Objects.equals(winnerTicket, that.winnerTicket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winnerTicket);
    }
}