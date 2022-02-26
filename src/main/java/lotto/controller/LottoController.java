package lotto.controller;

import java.util.List;
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

        LottoTickets lottoTickets = createLottoTickets(lottoPurchaseMoney);
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

    private LottoTickets createLottoTickets(LottoPurchaseMoney lottoPurchaseMoney) {
        LottoMachine lottoMachine = new LottoMachine();
        LottoTickets lottoTickets = lottoMachine.purchase(lottoPurchaseMoney);
        LottoTicketsDto lottoTicketsDto = new LottoTicketsDto(lottoTickets);

        outputView.printTotalCount(lottoTickets.totalCount());
        outputView.printLottoTicketsInfo(lottoTicketsDto);

        return lottoTickets;
    }

    private WinningNumbers createWinningNumbers() {
        try {
            List<String> winningNumbers = inputView.getNormalWinningNumbers();
            String bonusNumber = inputView.getBonusNumber();

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
