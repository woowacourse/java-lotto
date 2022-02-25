package controller;

import java.util.List;
import java.util.stream.Collectors;

import model.lottonumbergenerator.Generator;
import model.LottoMachine;
import model.lottotickets.LottoTicketDto;

public class MachineController {
    private final LottoMachine lottoMachine;
    private final Generator generator;

    private final InputController inputController;
    private final OutputController outputController;

    public MachineController(Generator generator, InputController inputController,
                             OutputController outputController) {
        this.generator = generator;
        this.lottoMachine = new LottoMachine();

        this.inputController = inputController;
        this.outputController = outputController;
    }

    public void runMachine() {
        insertMoney();
        purchaseLottoTickets();
        settingLottoWinningNumbers();
        showLottoGameResult();
    }

    private void insertMoney() {
        int money = inputController.inputMoney();
        lottoMachine.insertMoney(money);
    }

    private void purchaseLottoTickets() {
        lottoMachine.purchaseLottoTickets(generator);

        List<LottoTicketDto> dto = convertToDto(lottoMachine.lottoTickets());
        outputController.printPurchasedLottoTickets(dto);
    }

    private List<LottoTicketDto> convertToDto(final List<List<Integer>> lottoTickets) {
        return lottoTickets.stream()
                .map(LottoTicketDto::new)
                .collect(Collectors.toList());
    }

    private void settingLottoWinningNumbers() {
        final List<Integer> winningNumbers = inputController.inputWinningNumbers();
        final int bonusNumber = inputController.inputBonusNumber();

        lottoMachine.insertWinningNumbers(winningNumbers, bonusNumber);
    }

    private void showLottoGameResult() {
        outputController.printWinningResults(lottoMachine.winningResult());
        outputController.printRateOfReturn(lottoMachine.rateOfReturn());
    }
}
