package lotto.domain.user;

import lotto.domain.lottofactory.LottoCreator;
import lotto.domain.lottofactory.LottoTicket;
import lotto.domain.lottofactory.shufflerule.Shuffle;
import lotto.utils.NullCheckUtil;

import java.util.ArrayList;
import java.util.List;

public class UserTicketManager {
    private List<LottoTicket> userLottoTickets;

    public UserTicketManager(PurchaseAmount purchaseAmount, Shuffle shuffle) {
        List<LottoTicket> lottoTickets = getCreatedLottoTickets(purchaseAmount, shuffle);
        NullCheckUtil.checkNullLottoTickets(lottoTickets);
        this.userLottoTickets = lottoTickets;
    }

    private List<LottoTicket> getCreatedLottoTickets(PurchaseAmount purchaseAmount, Shuffle shuffle) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        int count = 0;
        while (!purchaseAmount.isEqualsAmount(count)) {
            lottoTickets.add(LottoCreator.getLottoTicket(shuffle));
            count++;
        }

        return lottoTickets;
    }

    public List<LottoTicket> getUserLottoTickets() {
        return userLottoTickets;
    }
}
