package lotto.controller;

import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.number.LottoNumbers;
import lotto.domain.ticket.LottoTickets;
import lotto.domain.ticket.WinningLottoTicket;
import lotto.service.LottoMachineService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class LottoController {
    private static final int ONE = 1;

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachineService lottoMachineService;

    public LottoController(InputView inputView, OutputView outputView, Money ticketPrice) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachineService = new LottoMachineService(ticketPrice);
    }

    public void start() {
        LottoTickets lottoTickets = buyAllLottoTickets();

        outputView.printAllLottoTickets(lottoTickets);

        LottoResult lottoResult = calculateLottoResult(lottoTickets,
                createWinningLotto(inputView.inputWinningNumbers(), inputView.takeBonusNumber())
        );

        outputView.printLottoResult(lottoResult);
    }

    private LottoTickets buyAllLottoTickets() {
        Money purchaseMoney = new Money(inputView.takeLottoMoney());
        int sizeOfManualLotto = inputView.inputSizeOfManualLotto();
        List<LottoNumbers> lottoNumbersBundle = inputManualLottoNumbers(sizeOfManualLotto);
        LottoTickets lottoTickets = createAllLottoTickets(purchaseMoney, sizeOfManualLotto, lottoNumbersBundle);
        outputView.printTicketsSize(sizeOfManualLotto, lottoTickets.size() - sizeOfManualLotto);
        return lottoTickets;
    }

    public LottoTickets createAllLottoTickets(Money purchaseMoney, int sizeOfManualLotto, List<LottoNumbers> lottoNumbersBundle) {
        LottoTickets autoLottoTickets = lottoMachineService.buyAutoLottoTicket(subtractManualLottoPrice(purchaseMoney, sizeOfManualLotto));
        LottoTickets manualLottoTickets = lottoMachineService.buyManualLottoTicket(sizeOfManualLotto, lottoNumbersBundle);
        return manualLottoTickets.concat(autoLottoTickets);
    }

    private Money subtractManualLottoPrice(Money purchaseMoney, int sizeOfManualLotto) {
        return purchaseMoney.minus(new Money(sizeOfManualLotto * lottoMachineService.getLottoPrice().getMoney()));
    }

    private List<LottoNumbers> inputManualLottoNumbers(int sizeOfManualLotto) {
        if (sizeOfManualLotto < ONE) {
            return Collections.emptyList();
        }

        inputView.printRequestMessageForInputManualLottoNumbers();

        return Stream.generate(() -> new LottoNumbers(inputView.inputManualLottoNumbers()))
                .limit(sizeOfManualLotto)
                .collect(toList());
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
                        lottoResultMap -> new LottoResult(lottoResultMap, lottoMachineService.getLottoPrice().multiply(lottoTickets.size()))));
    }
}
