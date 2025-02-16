package controller;

import domain.dto.LottoDto;
import domain.dto.LottoResultDto;
import domain.dto.LottosDto;
import domain.dto.TicketDto;
import domain.dto.WinningNumberDto;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void start() {
        int purchaseAmount = inputView.purchaseAmountInput();
        TicketDto ticketDto = ticketProcess(purchaseAmount);
        LottosDto lottosDto = lottoProcess(ticketDto);
        WinningNumberDto winningNumberDto = winningNumberProcess();
        LottoResultDto lottoResultDto = calculateRankProcess(winningNumberDto, lottosDto);
        profitProcess(lottoResultDto, purchaseAmount);
    }

    private TicketDto ticketProcess(int purchaseAmount) {
        TicketDto ticketDto = lottoService.makeTicket(purchaseAmount);
        outputView.printPurchaseResult(ticketDto);
        return ticketDto;
    }

    private void profitProcess(LottoResultDto lottoResultDto, int purchaseAmount) {
        double calculateRate = lottoService.calculateProfit(lottoResultDto, purchaseAmount);
        outputView.printProfit(calculateRate);
    }

    private LottoResultDto calculateRankProcess(
        WinningNumberDto winningNumber, LottosDto lottos) {
        lottoService.calculateRank(winningNumber, lottos);
        LottoResultDto lottoResult = lottoService.getRankResult();
        outputView.printWinningStatistic(lottoResult);
        return lottoResult;
    }

    private WinningNumberDto winningNumberProcess() {
        String winningNumbers = inputView.winningNumbersInput();
        LottoDto lottoDto = lottoService.makeLotto(winningNumbers);
        int bonusNumber = inputView.bonusNumberInput();
        return lottoService.makeWinningNumber(lottoDto, bonusNumber);
    }

    private LottosDto lottoProcess(TicketDto ticketDto) {
        lottoService.saveLotto(ticketDto);
        LottosDto lottos = lottoService.getLottos();
        outputView.printLottos(lottos);
        return lottos;
    }
}
