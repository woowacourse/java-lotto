package controller;

import java.util.List;
import java.util.stream.Collectors;

import model.GenerateStrategy;
import model.LottoGame;
import model.LottoTicket;
import model.LottoTicketDto;

public class LottoController {
    private final LottoGame lottoGame;
    private final GenerateStrategy generateStrategy;

    private final InputController inputController;
    private final OutputController outputController;

    public LottoController(GenerateStrategy generateStrategy, InputController inputController,
                           OutputController outputController) {
        this.generateStrategy = generateStrategy;
        this.lottoGame = new LottoGame();

        this.inputController = inputController;
        this.outputController = outputController;
    }

    public void runGame() {
        insertMoney();
        purchaseLottoTickets();
        settingLottoWinningNumbers();
        showLottoGameResult();
    }

    private void insertMoney() {
        int money = inputController.inputMoney();
        lottoGame.insertMoney(money);
    }

    private void purchaseLottoTickets() {
        lottoGame.purchaseLottoTickets(generateStrategy);

        List<LottoTicketDto> dto = convertToDto(lottoGame.lottoTickets());
        outputController.printPurchasedLottoTickets(dto);
    }

    private List<LottoTicketDto> convertToDto(final List<LottoTicket> lottoTickets) {
        return lottoTickets.stream()
                .map(lottoTicket -> new LottoTicketDto(lottoTicket.lottoNumbers()))
                .collect(Collectors.toList());
    }

    private void settingLottoWinningNumbers() {
        final List<Integer> winningNumbers = inputController.inputWinningNumbers();
        final int bonusNumber = inputController.inputBonusNumber();

        lottoGame.insertWinningNumbers(winningNumbers, bonusNumber);
    }

    private void showLottoGameResult() {
        outputController.printWinningResults(lottoGame.winningResult());
        outputController.printRateOfReturn(lottoGame.lottoRateOfReturn());
    }
}
