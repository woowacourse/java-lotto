package lotto.controller;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.function.Function;
import lotto.domain.machine.AutoLottoMachine;
import lotto.domain.machine.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.ticket.LottoTickets;
import lotto.domain.ticket.WinningLottoTicket;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private LottoMachine lottoMachine;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        lottoMachine = new AutoLottoMachine();

        int lottoPurchaseMoney = inputView.takeLottoMoney();

        LottoTickets lottoTickets = buyLottoTicket(lottoPurchaseMoney);

        WinningLottoTicket winningLottoTicket = createWinningLotto();

        LottoResult lottoResult = calculateLottoResult(lottoTickets, winningLottoTicket);

        outputView.printLottoResult(lottoResult, lottoPurchaseMoney);
    }

    private LottoTickets buyLottoTicket(int lottoPurchaseMoney) {
        LottoTickets lottoTickets = lottoMachine.createTicketsByMoney(lottoPurchaseMoney);
        outputView.printTicketsSize(lottoTickets.size());
        outputView.printAllLottoTickets(lottoTickets);
        return lottoTickets;
    }

    private WinningLottoTicket createWinningLotto() {
        List<Integer> winningNumbers = inputView.inputWinningNumbers();
        int bonusNumber = inputView.takeBonusNumber();
        return new WinningLottoTicket(winningNumbers, bonusNumber);
    }

    private LottoResult calculateLottoResult(LottoTickets lottoTickets,
        WinningLottoTicket winningLottoTicket) {
        return lottoTickets.list().stream()
            .map(winningLottoTicket::compareNumbers)
            .collect(collectingAndThen(
                groupingBy(Function.identity(), counting()),
                LottoResult::new));
    }
}
