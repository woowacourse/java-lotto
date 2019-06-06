package lotto.domain.user;

import lotto.domain.result.Rank;
import lotto.domain.winning.WinningLotto;
import lotto.domain.winning.BonusBall;
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

    private UserTickets(List<LottoTicket> manualTickets, PurchaseAmount purchaseAmount, Shuffle autoShuffle) {
        List<LottoTicket> tickets = new ArrayList<>(manualTickets);
        this.userLottoTickets = getAutoTickets(tickets, purchaseAmount, autoShuffle);
    }

    private List<LottoTicket> getAutoTickets(List<LottoTicket> tickets, PurchaseAmount purchaseAmount
            , Shuffle autoShuffle) {
        while (!isSufficientTickets(purchaseAmount, tickets)) {
            tickets.add(LottoCreator.createAutoTickets(autoShuffle));
        }

        return tickets;
    }

    private boolean isSufficientTickets(PurchaseAmount purchaseAmount, List<LottoTicket> tickets) {
        return purchaseAmount.isEqualsAmount(tickets.size());
    }

    public static UserTickets createUserTickets(List<LottoTicket> manualTickets, PurchaseAmount purchaseAmount
            , Shuffle autoShuffle) {
        NullCheckUtil.checkNullLottoTickets(manualTickets);
        NullCheckUtil.checkNullPurchaseAmount(purchaseAmount);
        NullCheckUtil.checkNullShuffle(autoShuffle);

        return new UserTickets(manualTickets, purchaseAmount, autoShuffle);
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
