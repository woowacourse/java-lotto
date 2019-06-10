package lotto.domain.user;

import lotto.domain.lottomanager.LottoCreator;
import lotto.domain.lottomanager.LottoTicket;
import lotto.domain.lottomanager.shufflerule.Shuffle;
import lotto.domain.result.Rank;
import lotto.domain.winning.WinningLotto;
import lotto.view.inputview.InputParser;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserTickets {
    private static final String ERROR_NULL_LOTTO_TICKET = "createUserTickets(List<LottoTicket>) has Null";
    private static final String ERROR_NULL_PURCHASE_AMOUNT = "createUserTickets(PurchaseAmount) has Null";
    private static final String ERROR_NULL_SHUFFLE = "createUserTickets(Shuffle) has Null";
    private static final String ERROR_NULL_WINNING_LOTTO = "getMatchedRanks(WinningLotto) has Null";

    private List<LottoTicket> userLottoTickets;

    private UserTickets(List<String> manualTickets, PurchaseAmount purchaseAmount, Shuffle autoShuffle) {
        List<LottoTicket> tickets = createManualTickets(manualTickets);
        this.userLottoTickets = getAutoTickets(tickets, purchaseAmount, autoShuffle);
    }

    private List<LottoTicket> createManualTickets(List<String> manualTickets) {
        return manualTickets.stream()
                .map(InputParser::getLottoNum)
                .map(LottoCreator::createManualTickets)
                .collect(Collectors.toList());
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

    public static UserTickets createUserTickets(List<String> manualTickets, PurchaseAmount purchaseAmount
            , Shuffle autoShuffle) {
        manualTickets.forEach(UserTickets::checkNullLottoTicket);
        checkNullPurchaseAmount(purchaseAmount);
        checkNullAutoShuffle(autoShuffle);

        return new UserTickets(manualTickets, purchaseAmount, autoShuffle);
    }

    private static void checkNullLottoTicket(String lottoTicket) {
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

    public List<Rank> getMatchedRanks(WinningLotto winningLotto) {
        checkNullWinningLotto(winningLotto);

        return Collections.unmodifiableList(getFilledRanks(winningLotto));
    }

    private void checkNullWinningLotto(WinningLotto winningLotto) {
        if (winningLotto == null) {
            throw new IllegalArgumentException(ERROR_NULL_WINNING_LOTTO);
        }
    }

    private List<Rank> getFilledRanks(WinningLotto winningLotto) {
        return userLottoTickets.stream()
                .map(userTicket -> getRank(winningLotto, userTicket))
                .collect(Collectors.toList());
    }

    private Rank getRank(WinningLotto winningLotto, LottoTicket userTicket) {
        return winningLotto.getMatchRank(userTicket);
    }
}
