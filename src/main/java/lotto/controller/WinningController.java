package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;
import lotto.domain.LottoTicket;
import lotto.domain.WinningTicket;
import lotto.dto.LottoStatisticsResponse;
import lotto.dto.PurchaseResult;
import lotto.utils.IntegerUtils;
import lotto.view.ErrorView;
import lotto.view.InputView;
import lotto.view.OutputView;

public class WinningController {

    private final InputView inputView;
    private final OutputView outputView;
    private final ErrorView errorView;

    private WinningController(InputView inputView, OutputView outputView, ErrorView errorView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.errorView = errorView;
    }

    private static class WinningControllerHelper {
        private static final WinningController INSTANCE = new WinningController(
            InputView.getInstance(), OutputView.getInstance(), ErrorView.getInstance()
        );
    }

    public static WinningController getInstance() {
        return WinningControllerHelper.INSTANCE;
    }

    public void drawLots(PurchaseResult purchaseResult) {
        WinningTicket winningTicket = createWinningTicket();
        showStatistics(winningTicket, purchaseResult);
    }

    private WinningTicket createWinningTicket() {
        LottoTicket winningNumber = createWinningNumber();
        LottoNumber bonusBall = createWinningBonus();
        return new WinningTicket(winningNumber, bonusBall);
    }

    private LottoTicket createWinningNumber() {
        String inputWinningNumber = inputView.inputWinningNumber();
        List<LottoNumber> numbers = IntegerUtils.parseAll(inputWinningNumber)
            .stream()
            .map(LottoNumber::from)
            .collect(Collectors.toList());
        return new LottoTicket(numbers);
    }

    private LottoNumber createWinningBonus() {
        String inputBonusBall = inputView.inputBonusBall();
        return LottoNumber.from(IntegerUtils.parse(inputBonusBall));
    }

    private void showStatistics(WinningTicket winningTicket, PurchaseResult purchaseResult) {
        LottoStatisticsResponse statistics = toStatisticsResponse(winningTicket, purchaseResult);
        outputView.outputStatistics(statistics);
    }

    private LottoStatisticsResponse toStatisticsResponse(
        WinningTicket winningTicket, PurchaseResult purchaseResult) {
        List<LottoRank> ranks = purchaseResult.getTickets().stream()
            .map(winningTicket::compare)
            .collect(Collectors.toList());
        return new LottoStatisticsResponse(ranks, purchaseResult.getMoney());
    }

}
