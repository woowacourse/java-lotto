package lotto.domain.user;

import lotto.domain.lottomanager.AutoCreator;
import lotto.domain.lottomanager.LottoCreator;
import lotto.domain.lottomanager.LottoTicket;
import lotto.domain.lottomanager.ManualCreator;
import lotto.domain.lottomanager.shufflerule.RandomShuffle;
import lotto.domain.result.Rank;
import lotto.domain.lottomanager.WinningLotto;
import lotto.view.inputview.InputParser;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserTickets {
    private static final String ERROR_NULL_LOTTO_TICKET = "UserTickets(List<LottoTicket>) has Null";
    private static final String ERROR_NULL_PURCHASE_AMOUNT = "UserTickets(PurchaseAmount) has Null";
    private static final String ERROR_NULL_WINNING_LOTTO = "getMatchedRanks(WinningLotto) has Null";

    private List<LottoTicket> userLottoTickets;

    public UserTickets(List<String> manualTickets, PurchaseAmount purchaseAmount) {
        manualTickets.forEach(UserTickets::checkNullLottoTicket);
        checkNullPurchaseAmount(purchaseAmount);

        List<LottoTicket> tickets = createManualTickets(manualTickets);
        this.userLottoTickets = getAutoTickets(tickets, purchaseAmount);
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

    private List<LottoTicket> createManualTickets(List<String> inputTickets) {
        return inputTickets.stream()
                .map(InputParser::getLottoNum)
                .map(numbers -> (LottoCreator) new ManualCreator(numbers))
                .map(LottoCreator::createTickets)
                .collect(Collectors.toList());
    }

    private List<LottoTicket> getAutoTickets(List<LottoTicket> tickets, PurchaseAmount purchaseAmount) {
        LottoCreator lottoCreator = new AutoCreator(new RandomShuffle());

        while (!isSufficientTickets(purchaseAmount, tickets)) {
            tickets.add(lottoCreator.createTickets());
        }

        return tickets;
    }

    private boolean isSufficientTickets(PurchaseAmount purchaseAmount, List<LottoTicket> tickets) {
        return purchaseAmount.isEqualsAmount(tickets.size());
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
