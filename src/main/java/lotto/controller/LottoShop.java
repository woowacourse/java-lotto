package lotto.controller;

import lotto.domain.WinningLotto;
import lotto.domain.WinningResult;
import lotto.domain.lottomanager.BonusBall;
import lotto.domain.lottomanager.LottoCreator;
import lotto.domain.lottomanager.LottoTicket;
import lotto.domain.lottomanager.shufflerule.RandomShuffle;
import lotto.domain.user.PurchaseAmount;
import lotto.domain.user.UserTickets;
import lotto.view.inputview.InputView;
import lotto.view.inputview.PriceParser;
import lotto.view.inputview.LottoNumParser;
import lotto.view.outputview.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoShop {
    public void operate() {
        Integer purchasePrice = getPurchasePrice();
        PurchaseAmount purchaseAmount = getPurchaseAmount(purchasePrice);
        List<LottoTicket> manualLottoTickets = getManualTickets();
        OutputView.printAmount(purchaseAmount, manualLottoTickets.size());

        UserTickets userTickets = getUserTickets(manualLottoTickets, purchaseAmount);
        OutputView.printUserLottoTickets(userTickets);

        WinningLotto winningLotto = getWinningLotto();
        BonusBall bonusBall = getBonusBall(winningLotto);

        WinningResult winningResult = getWinningResult(userTickets, winningLotto, bonusBall);
        OutputView.printWinningStatistics(winningResult, purchasePrice);
    }

    private Integer getPurchasePrice() {
        return PriceParser.getPurchasePrice(InputView.inputPrice());
    }

    private PurchaseAmount getPurchaseAmount(Integer purchasePrice) {
        return PurchaseAmount.createLottoAmount(purchasePrice);
    }

    private List<LottoTicket> getManualTickets() {
        return InputView.inputManualLottoTickets(InputView.inputManualLottoAmount())
                .stream()
                .map(LottoNumParser::getLottoNum)
                .map(LottoCreator::createManualTickets)
                .collect(Collectors.toList());
    }

    private UserTickets getUserTickets(List<LottoTicket> manualTickets, PurchaseAmount purchaseAmount) {
        return UserTickets.createUserTickets(manualTickets, purchaseAmount, new RandomShuffle());
    }

    private WinningLotto getWinningLotto() {
        return WinningLotto.createWinningLotto(LottoNumParser.getLottoNum(InputView.inputWinningNum()));
    }

    private BonusBall getBonusBall(WinningLotto winningLotto) {
        return BonusBall.createBonusBall(InputView.inputBonusBall(), winningLotto);
    }

    private WinningResult getWinningResult(UserTickets userTickets, WinningLotto winningLotto, BonusBall bonusBall) {
        return WinningResult.createWinningResult(userTickets, winningLotto, bonusBall);
    }
}
