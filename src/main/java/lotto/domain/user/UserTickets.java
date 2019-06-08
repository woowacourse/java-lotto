package lotto.domain.user;

import lotto.domain.result.Rank;
import lotto.domain.winning.WinningLotto;
import lotto.domain.winning.BonusBall;
import lotto.domain.lottomanager.LottoCreator;
import lotto.domain.lottomanager.LottoTicket;
import lotto.domain.lottomanager.shufflerule.Shuffle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserTickets {
    private static final String ERROR_NULL_LOTTO_TICKET = "createUserTickets(List<LottoTicket>) has Null";
    private static final String ERROR_NULL_PURCHASE_AMOUNT = "createUserTickets(PurchaseAmount) has Null";
    private static final String ERROR_NULL_SHUFFLE = "createUserTickets(Shuffle) has Null";
    private static final String ERROR_NULL_WINNING_LOTTO = "getMatchedRanks(WinningLotto) has Null";
    private static final String ERROR_NULL_BONUS_BALL = "getMatchedRanks(BonusBall) has Null";

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
        manualTickets.forEach(UserTickets::checkNullLottoTicket);
        checkNullPurchaseAmount(purchaseAmount);
        checkNullAutoShuffle(autoShuffle);

        return new UserTickets(manualTickets, purchaseAmount, autoShuffle);
    }

    private static void checkNullLottoTicket(LottoTicket lottoTicket) {
        if (lottoTicket == null) {
            throw new IllegalArgumentException(ERROR_NULL_LOTTO_TICKET);
        }
    }

    private static void checkNullPurchaseAmount(PurchaseAmount purchaseAmount) {
        if (purchaseAmount == null) {
            throw new IllegalArgumentException(ERROR_NULL_PURCHASE_AMOUNT);
        }
    }

    private static void checkNullAutoShuffle(Shuffle autoShuffle) {
        if (autoShuffle == null) {
            throw new IllegalArgumentException(ERROR_NULL_SHUFFLE);
        }
    }

    public List<LottoTicket> getUserLottoTickets() {
        return Collections.unmodifiableList(userLottoTickets);
    }

    public List<Rank> getMatchedRanks(WinningLotto winningLotto, BonusBall bonusBall) {
        checkNullWinningLotto(winningLotto);
        checkNullBonusBall(bonusBall);

        return Collections.unmodifiableList(getFilledRanks(winningLotto, bonusBall));
    }

    private void checkNullWinningLotto(WinningLotto winningLotto) {
        if (winningLotto == null) {
            throw new IllegalArgumentException(ERROR_NULL_WINNING_LOTTO);
        }
    }

    private void checkNullBonusBall(BonusBall bonusBall) {
        if (bonusBall == null) {
            throw new IllegalArgumentException(ERROR_NULL_BONUS_BALL);
        }
    }

    private List<Rank> getFilledRanks(WinningLotto winningLotto, BonusBall bonusBall) {
        return userLottoTickets.stream()
                .map(userTicket -> getRank(winningLotto, bonusBall, userTicket))
                .collect(Collectors.toList());
    }

    private Rank getRank(WinningLotto winningLotto, BonusBall bonusBall, LottoTicket userTicket) {
        return Rank.valueOf(getMatchOfCount(winningLotto, userTicket), getMatchBonus(bonusBall, userTicket));
    }

    private int getMatchOfCount(WinningLotto winningLotto, LottoTicket userTicket) {
        return winningLotto.getMatchedWinningNumbersCount(userTicket);
    }

    private boolean getMatchBonus(BonusBall bonusBall, LottoTicket userTicket) {
        return userTicket.isContainedNumbers(bonusBall);
    }
}
