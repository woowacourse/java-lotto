package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    private LottoTickets(final Money money, final List<String> manualTicketNumbers) {
        validateSizeOfManualTickets(money, manualTicketNumbers);
        this.lottoTickets = LottoMachine.generateManualLottoTickets(manualTicketNumbers);
        int sizeOfAutoLotto = money.purchasedAutoLottoSize(lottoTickets.size());
        this.lottoTickets.addAll(LottoMachine.generateAutoLottoTickets(sizeOfAutoLotto));
    }

    public static LottoTickets valueOf(final Money money, final List<String> manualTicketNumbers) {
        return new LottoTickets(money, manualTicketNumbers);
    }

    public List<LottoTicket> toList() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public int getLottoQuantity() {
        return lottoTickets.size();
    }

    private void validateSizeOfManualTickets(final Money money, final List<String> manualTicketNumbers) {
        if (!money.canBuy(manualTicketNumbers.size())) {
            throw new IllegalArgumentException("구매 가능한 로또 개수가 아닙니다.");
        }
    }

    public List<Ranking> calculateRankings(WinningNumbers winningNumbers) {
        return lottoTickets.stream()
            .map(winningNumbers::checkRanking)
            .collect(Collectors.toList());
    }
}
