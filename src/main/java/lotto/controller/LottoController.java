package lotto.controller;

import com.sun.tools.internal.ws.wsdl.document.Output;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lotto.domain.AutoLottoMachine;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.Prize;
import lotto.domain.WinningLottoTicket;
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
        int lottoMoney = inputView.takeLottoMoney();

        int numberOfTickets = lottoMoney / 1000;

        List<LottoTicket> lottoTickets = lottoMachine.createTickets(numberOfTickets);
        outputView.printTicketsSize(lottoTickets.size());
        outputView.printAllLottoTickets(lottoTickets);

        List<Integer> winningNumbers = inputView.inputWinningNumbers();
        int bonusNumber = inputView.takeBonusNumber();
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket(winningNumbers, bonusNumber);

        LottoResult lottoResult = new LottoResult(
            calculateLottoResult(lottoTickets, winningLottoTicket));
        outputView.printLottoResult(lottoResult, lottoMoney);
    }

    private Map<Prize, Long> calculateLottoResult(List<LottoTicket> lottoTickets,
        WinningLottoTicket winningLottoTicket) {
        return lottoTickets.stream()
            .map(winningLottoTicket::compareNumbers)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
