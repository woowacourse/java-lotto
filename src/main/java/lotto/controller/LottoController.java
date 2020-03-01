package lotto.controller;

import lotto.domain.*;
import lotto.exception.UnderLottoUnitMoney;
import lotto.utils.ValidationUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    public void play() {
        Money money = generateMoney();
        LottoTicketCount autoTicketLottoTicketCount = new LottoTicketCount(money.generateLottoTicketCount());
        LottoTicketCount manualTicketLottoTicketCount = generateManualTicketCount(autoTicketLottoTicketCount);

        OutputView.printInputManualLottoTicket(manualTicketLottoTicketCount.getTicketCount());
        for (int i = 0; i < manualTicketLottoTicketCount.getTicketCount(); i++) {
            LottoTickets.insertLottoTicket(generateManualLottoTicket());
        }
        generateAutoTicket(autoTicketLottoTicketCount, manualTicketLottoTicketCount);
        OutputView.printLottoTicket();
        OutputView.printChangeMoney(money.changeMoney());

        WinningTicket winningTicket = getWinningTicket();

        OutputView.printEachRankCount(Rank.calculateEachRankCount(winningTicket));
    }

    private WinningTicket getWinningTicket() {
        OutputView.printWinningTicket();
        LottoTicket lottoTicket = generateManualLottoTicket();

        try {
            return new WinningTicket(lottoTicket.getLottoTicket(), generateBonusBall(lottoTicket));
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getWinningTicket();
        }
    }

    private LottoBall generateBonusBall(LottoTicket lottoTicket) {
        LottoBall bonusBall = new LottoBall(InputView.inputBonusBall());
        List<LottoBall> winningTicket = lottoTicket.getLottoTicket();

        winningTicket.forEach(winningLottoBall ->
                ValidationUtils.validateDuplicateNumber(winningLottoBall.getLottoBall(),bonusBall.getLottoBall()));

        return bonusBall;
    }

    private LottoTicket generateManualLottoTicket() {
        try {
            return new LottoTicket(InputView.inputLottoTicket());
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return generateManualLottoTicket();
        }
    }

    private void generateAutoTicket(LottoTicketCount autoTicketLottoTicketCount, LottoTicketCount manualTicketLottoTicketCount) {
        autoTicketLottoTicketCount.calculateAutoTicketCount(manualTicketLottoTicketCount);
        OutputView.printLottoTicketCount(manualTicketLottoTicketCount, autoTicketLottoTicketCount);
        for (int i = 0; i < autoTicketLottoTicketCount.getTicketCount(); i++) {
            LottoBalls.shuffle();
            LottoTickets.insertLottoTicket(new LottoTicket(LottoBalls.generateLottoTicket()));
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
