package lotto.controller;

import lotto.domain.LottoResult;
import lotto.domain.machine.LottoMachine;
import lotto.domain.ticket.LottoTickets;
import lotto.domain.ticket.WinningLottoTicket;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public LottoController(InputView inputView, OutputView outputView, LottoMachine lottoMachine) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachine = lottoMachine;
    }

    public void start(){
        LottoTickets lottoTickets = buyLottoTicket(inputView.takeLottoMoney());

        WinningLottoTicket winningLotto = createWinningLotto(inputView.inputWinningNumbers(), inputView.takeBonusNumber());

        LottoResult lottoResult = calculateLottoResult(lottoTickets, winningLotto);

        outputView.printLottoResult(lottoResult);
    }

    public LottoTickets buyLottoTicket(int lottoPurchaseMoney) {
        LottoTickets lottoTickets = lottoMachine.createTicketsByMoney(lottoPurchaseMoney);
        outputView.printTicketsSize(lottoTickets.size());
        outputView.printAllLottoTickets(lottoTickets);
        return lottoTickets;
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
                LottoResult::new));
    }
}
