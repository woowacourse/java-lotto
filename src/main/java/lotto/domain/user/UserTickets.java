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
import java.util.stream.Collectors;

public class UserTickets {
    private List<LottoTicket> userLottoTickets;

    private UserTickets(List<LottoTicket> manualTickets, PurchaseAmount purchaseAmount, Shuffle shuffle) {
        List<LottoTicket> userLottoTickets = new ArrayList<>(manualTickets);
        this.userLottoTickets = getCreatedLottoTickets(userLottoTickets, purchaseAmount, shuffle);
    }

    private List<LottoTicket> getCreatedLottoTickets(List<LottoTicket> userLottoTickets, PurchaseAmount purchaseAmount, Shuffle shuffle) {
        while (!isSufficientTickets(purchaseAmount, userLottoTickets)) {
            userLottoTickets.add(LottoCreator.createAutoTickets(shuffle));
        }

        return userLottoTickets;
    }

    private boolean isSufficientTickets(PurchaseAmount purchaseAmount, List<LottoTicket> userLottoTickets) {
        return purchaseAmount.isEqualsAmount(userLottoTickets.size());
    }

    public static UserTickets createUserTickets(List<LottoTicket> manualTickets, PurchaseAmount purchaseAmount, Shuffle shuffle) {
        NullCheckUtil.checkNullLottoTickets(manualTickets);
        NullCheckUtil.checkNullPurchaseAmount(purchaseAmount);
        NullCheckUtil.checkNullShuffle(shuffle);

        return new UserTickets(manualTickets, purchaseAmount, shuffle);
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
        return userLottoTickets.stream()
                .map(userTicket -> getRank(winningLotto, bonusBall, userTicket))
                .collect(Collectors.toList());
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
