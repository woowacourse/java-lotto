package lotto.domain.user;

import lotto.domain.lottofactory.LottoCreator;
import lotto.domain.lottofactory.LottoTicket;
import lotto.domain.lottofactory.shufflerule.Shuffle;
import lotto.utils.NullCheckUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserTicketManager {
    private List<LottoTicket> userLottoTickets;

    public UserTicketManager(PurchaseAmount purchaseAmount, Shuffle shuffle) {
        List<LottoTicket> lottoTickets = getCreatedLottoTickets(purchaseAmount, shuffle);
        NullCheckUtil.checkNullLottoTickets(lottoTickets);
        this.userLottoTickets = lottoTickets;
    }

    private List<LottoTicket> getCreatedLottoTickets(PurchaseAmount purchaseAmount, Shuffle shuffle) {
        return Stream.generate(() -> LottoCreator.getLottoTicket(shuffle))
                    .limit(purchaseAmount.getLottoAmount())
                    .collect(Collectors.toList());
    }

    public List<LottoTicket> getUserLottoTickets() {
        return userLottoTickets;
    }
}
