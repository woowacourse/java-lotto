package lotto.domain;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.stream.Stream;

public class PurchaseResult {

    private final LottoTicket manualTicket;
    private final LottoTicket autoTicket;

    public PurchaseResult(LottoTicket manualTicket, LottoTicket autoTicket) {
        this.manualTicket = manualTicket;
        this.autoTicket = autoTicket;
    }

    public LottoTicket getManualTicket() {
        return manualTicket;
    }

    public LottoTicket getAutoTicket() {
        return autoTicket;
    }

    public Money sumMoney() {
        int manualSize = manualTicket.getLines().size();
        int autoSize = autoTicket.getLines().size();
        return Money.from((manualSize + autoSize) * LottoLine.PRICE);
    }

    public List<LottoRank> compareWinningTicket(WinningTicket winningTicket) {
        return Stream.concat(
            winningTicket.compareTicket(manualTicket).stream(),
            winningTicket.compareTicket(autoTicket).stream()
        ).collect(toList());
    }
}
