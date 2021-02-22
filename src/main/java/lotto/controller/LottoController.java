package lotto.controller;

import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.machine.AutoLottoMachine;
import lotto.domain.machine.ManualLottoMachine;
import lotto.domain.number.LottoNumbers;
import lotto.domain.ticket.LottoTickets;
import lotto.domain.ticket.WinningLottoTicket;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final AutoLottoMachine autoLottoMachine;
    private final ManualLottoMachine manualLottoMachine;

    public LottoController(InputView inputView, OutputView outputView, Money ticketPrice) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.autoLottoMachine = new AutoLottoMachine(ticketPrice);
        this.manualLottoMachine = new ManualLottoMachine(ticketPrice);
    }

    public void start() {
        Money purchaseMoney = new Money(inputView.takeLottoMoney());
        int sizeOfManualLotto = inputView.inputSizeOfManualLotto();

        purchaseMoney.minus(new Money(sizeOfManualLotto * manualLottoMachine.getLottoPrice().getMoney()));
        LottoTickets autoLottoTickets = buyAutoLottoTicket(purchaseMoney);

        inputView.printRequestMessageForInputManualLottoNumbers();
        List<LottoNumbers> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < sizeOfManualLotto; i++) {
            lottoNumbers.add(new LottoNumbers(inputView.inputManualLottoNumbers()));
        }

        LottoTickets lottoTickets = manualLottoMachine.createTickets(sizeOfManualLotto, lottoNumbers);
        outputView.printTicketsSize(sizeOfManualLotto, autoLottoTickets.size());

        lottoTickets.concat(autoLottoTickets);
        outputView.printAllLottoTickets(lottoTickets);

        WinningLottoTicket winningLotto = createWinningLotto(inputView.inputWinningNumbers(), inputView.takeBonusNumber());

        LottoResult lottoResult = calculateLottoResult(lottoTickets, winningLotto);

        outputView.printLottoResult(lottoResult);
    }

    public LottoTickets buyAutoLottoTicket(Money lottoPurchaseMoney) {
        int numberOfTickets = autoLottoMachine.calculateNumberOfTickets(lottoPurchaseMoney);
        return autoLottoMachine.createTicketsByMoney(numberOfTickets);
    }

    public WinningLottoTicket createWinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        return new WinningLottoTicket(winningNumbers, bonusNumber);
    }

    public LottoResult calculateLottoResult(LottoTickets lottoTickets,
                                            WinningLottoTicket winningLottoTicket) {
        return lottoTickets.list().stream()
                .map(winningLottoTicket::compareNumbers)
                .collect(collectingAndThen(
                        groupingBy(Function.identity(), counting()),
                        lottoResultMap -> new LottoResult(lottoResultMap, autoLottoMachine.getLottoPrice().multiply(lottoTickets.size()))));
    }

}
