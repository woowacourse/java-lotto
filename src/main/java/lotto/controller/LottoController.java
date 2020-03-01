package lotto.controller;

import lotto.domain.*;
import lotto.exception.UnderLottoUnitMoney;
import lotto.utils.ValidationUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Map;

public class LottoController {
    public void play() {
        Money money = generateMoney();
        LottoTicketCount autoTicketLottoTicketCount = new LottoTicketCount(money.generateLottoTicketCount());
        LottoTicketCount manualTicketLottoTicketCount = generateManualTicketCount(autoTicketLottoTicketCount);

        LottoTickets lottoTickets = new LottoTickets();
        generateManualTicket(manualTicketLottoTicketCount,lottoTickets);
        generateAutoTicket(autoTicketLottoTicketCount, manualTicketLottoTicketCount,lottoTickets);
        OutputView.printLottoTicketAndChangeMoney(money.changeMoney(),lottoTickets);

        Map<Rank,Long> eachRankCount = Rank.calculateEachRankCount(getWinningTicket(),lottoTickets);
        result(money, eachRankCount);
    }

    private void result(Money money, Map<Rank, Long> eachRankCount) {
        try {
            OutputView.printResult(eachRankCount,EarningRate.calculateEarningRate(eachRankCount,money));
        }catch (RuntimeException e){
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void generateManualTicket(LottoTicketCount manualTicketLottoTicketCount,LottoTickets lottoTickets) {
        OutputView.printInputManualLottoTicket(manualTicketLottoTicketCount.getTicketCount());
        for (int i = 0; i < manualTicketLottoTicketCount.getTicketCount(); i++) {
            lottoTickets.insertLottoTicket(generateLottoTicket());
        }
    }

    private WinningTicket getWinningTicket() {
        OutputView.printWinningTicket();
        LottoTicket lottoTicket = generateLottoTicket();

        try {
            return new WinningTicket(lottoTicket.getLottoTicket(), generateBonusBall(lottoTicket));
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getWinningTicket();
        }
    }

    private LottoBall generateBonusBall(LottoTicket lottoTicket) {
        LottoBall bonusBall = new LottoBall(InputView.inputBonusBall());

        lottoTicket.getLottoTicket()
                .forEach(winningLottoBall ->
                ValidationUtils.validateDuplicateNumber(winningLottoBall.getLottoBall(),bonusBall.getLottoBall()));

        return bonusBall;
    }

    private LottoTicket generateLottoTicket() {
        try {
            return new LottoTicket(InputView.inputLottoTicket());
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return generateLottoTicket();
        }
    }

    private void generateAutoTicket(LottoTicketCount autoLottoTicketCount, LottoTicketCount manualLottoTicketCount
            ,LottoTickets lottoTickets) {
        autoLottoTicketCount.calculateAutoTicketCount(manualLottoTicketCount);
        OutputView.printLottoTicketCount(manualLottoTicketCount, autoLottoTicketCount);
        for (int i = 0; i < autoLottoTicketCount.getTicketCount(); i++) {
            LottoBalls.shuffle();
            lottoTickets.insertLottoTicket(new LottoTicket(LottoBalls.generateLottoTicket()));
        }
    }

    private Money generateMoney() {
        String inputMoney = InputView.inputMoney();

        try {
            return new Money(inputMoney);
        } catch (UnderLottoUnitMoney e) {
            OutputView.printErrorMessage(e.getMessage());
            OutputView.printChangeMoney(inputMoney);
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return generateMoney();
    }

    private LottoTicketCount generateManualTicketCount(LottoTicketCount allTicketLottoTicketCount) {
        try {
            LottoTicketCount manualTicketLottoTicketCount = new LottoTicketCount(InputView.inputManualLottoCount());

            manualTicketLottoTicketCount.validateOverTicketCount(allTicketLottoTicketCount);
            return manualTicketLottoTicketCount;
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return generateManualTicketCount(allTicketLottoTicketCount);
        }
    }
}
