package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.LottoTicket;
import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.LottoPurchaseMoney;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;
import lotto.domain.WinningNumbers;
import lotto.dto.LottoTicketsDto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        LottoPurchaseMoney lottoPurchaseMoney = createMoney();

        int manualCount = getManualCount(lottoPurchaseMoney);

        List<LottoTicket> manualLottoTickets = createManualLottoTicket(manualCount);

        LottoTickets lottoTickets = createLottoTickets(lottoPurchaseMoney, manualLottoTickets, manualCount);

        WinningNumbers winningNumbers = createWinningNumbers();

        LottoResult lottoResult = createLottoResult(lottoTickets, winningNumbers);

        outputView.printYield(lottoResult.getRanks(), lottoResult.calculateYield(lottoPurchaseMoney));
    }

    private LottoPurchaseMoney createMoney() {
        try {
            return LottoPurchaseMoney.create(inputView.getMoney());
        } catch(IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            return createMoney();
        }
    }

    private int getManualCount(LottoPurchaseMoney lottoPurchaseMoney) {
        try {
            int manualCount = inputView.getManualCount();
            lottoPurchaseMoney.calculate(manualCount);

            return manualCount;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            return getManualCount(lottoPurchaseMoney);
        }
    }

    private List<LottoTicket> createManualLottoTicket(int manualCount) {
        try {
            List<LottoTicket> manualLottoTickets = getLottoTickets(manualCount);

            return manualLottoTickets;
        } catch(IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            return createManualLottoTicket(manualCount);
        }
    }

    private List<LottoTicket> getLottoTickets(int manualCount) {
        List<LottoTicket> manualLottoTickets = new ArrayList<>();

        if (manualCount > 0) {
            setManualLottoTickets(manualCount, manualLottoTickets);
        }
        return manualLottoTickets;
    }

    private void setManualLottoTickets(int manualCount, List<LottoTicket> manualLottoTickets) {
        outputView.printManualLottoGuide();

        for (int i = 0; i < manualCount; i++) {
            List<LottoNumber> manualLottoNumber = inputView.getManualLottoNumber();

            manualLottoTickets.add(new LottoTicket(manualLottoNumber));
        }
    }

    private LottoTickets createLottoTickets(LottoPurchaseMoney lottoPurchaseMoney, List<LottoTicket> manualLottoTickets, int manualCount) {
        LottoMachine lottoMachine = new LottoMachine();

        LottoTickets lottoTickets = lottoMachine.purchase(lottoPurchaseMoney, manualLottoTickets);

        LottoTicketsDto lottoTicketsDto = new LottoTicketsDto(lottoTickets);

        outputView.printTotalCount(manualCount, lottoPurchaseMoney.calculate(manualCount));
        outputView.printLottoTicketsInfo(lottoTicketsDto);

        return lottoTickets;
    }

    private WinningNumbers createWinningNumbers() {
        try {
            List<LottoNumber> winningNumbers = inputView.getNormalWinningNumbers();
            LottoNumber bonusNumber = inputView.getBonusNumber();

            return WinningNumbers.create(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());

            return createWinningNumbers();
        }
    }

    private LottoResult createLottoResult(LottoTickets lottoTickets, WinningNumbers winningNumbers) {
        outputView.printLottoResultMessage();

        LottoResult lottoResult = lottoTickets.determine(winningNumbers);

        return lottoResult;
    }
}
