package lotto.domain.user;

import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.domain.lottomanager.BonusBall;
import lotto.domain.lottomanager.LottoCreator;
import lotto.domain.lottomanager.LottoTicket;
import lotto.domain.lottomanager.shufflerule.Shuffle;
import lotto.utils.NullCheckUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserTickets {
    private List<LottoTicket> userLottoTickets;

    private UserTickets(PurchaseAmount purchaseAmount, Shuffle shuffle) {
        List<LottoTicket> lottoTickets = getCreatedLottoTickets(purchaseAmount, shuffle);
        NullCheckUtil.checkNullLottoTickets(lottoTickets);
        this.userLottoTickets = lottoTickets;
    }

    private List<LottoTicket> getCreatedLottoTickets(PurchaseAmount purchaseAmount, Shuffle shuffle) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        while (!isSufficientTickets(purchaseAmount, lottoTickets)) {
            lottoTickets.add(LottoCreator.getLottoTicket(shuffle));
        }

        return lottoTickets;
    }

    private boolean isSufficientTickets(PurchaseAmount purchaseAmount, List<LottoTicket> lottoTickets) {
        return purchaseAmount.isEqualsAmount(lottoTickets.size());
    }

    public static UserTickets createUserTickets(PurchaseAmount purchaseAmount, Shuffle shuffle){
        NullCheckUtil.checkNullPurchaseAmount(purchaseAmount);
        NullCheckUtil.checkNullShuffle(shuffle);

        return new UserTickets(purchaseAmount, shuffle);
    }

    public List<LottoTicket> getUserLottoTickets() {
        return Collections.unmodifiableList(userLottoTickets);
    }

    public List<Rank> getMatchedRanks(WinningLotto winningLotto, BonusBall bonusBall) {
        NullCheckUtil.checkNullWinningLotto(winningLotto);
        NullCheckUtil.checkNullBonusBall(bonusBall);

        return Collections.unmodifiableList(getFilledRanks(winningLotto, bonusBall));
    }

    private List<Rank> getFilledRanks(WinningLotto winningLotto, BonusBall bonusBall) {
        List<Rank> matchedRanks = new ArrayList<>();

        for (LottoTicket userTicket : userLottoTickets){
            matchedRanks.add(getRank(winningLotto, bonusBall, userTicket));
        }
        return matchedRanks;
    }

    private Rank getRank(WinningLotto winningLotto, BonusBall bonusBall, LottoTicket userTicket) {
        return Rank.valueOf(getMatchOfCount(winningLotto, userTicket), getMatchBonus(bonusBall, userTicket));
    }

    private Integer getMatchOfCount(WinningLotto winningLotto, LottoTicket userTicket) {
        return winningLotto.getMatchedWinningNumbersCount(userTicket);
    }

    private Boolean getMatchBonus(BonusBall bonusBall, LottoTicket userTicket) {
        return userTicket.isContainedNumbers(bonusBall);
    }
}
