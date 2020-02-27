package lotto.service;

import lotto.domain.Money;
import lotto.domain.result.LottoMatchResultBundle;
import lotto.domain.result.OverallResult;
import lotto.domain.ticket.*;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private final LottoMachine defaultAutoMachine;

    public LottoService(LottoMachine defaultAutoMachine) {
        this.defaultAutoMachine = defaultAutoMachine;
    }

    public List<LottoTicket> createManualTickets(Money money, int numberOfTicket, List<List<Integer>> manualNumbers) {
        LottoMachine manualMachine = new ManualLottoMachine(manualNumbers);

        money.spendOnManualLotto(numberOfTicket);

        return manualMachine.buyTickets(numberOfTicket);
    }

    public List<LottoTicket> createAutoTickets(Money money) {
        int numberOfAutoTickets = money.calculateAffordableTicketNumbers();

        money.spendOnAutoLotto(numberOfAutoTickets);

        return defaultAutoMachine.buyTickets(numberOfAutoTickets);
    }

    public LottoTicketBundle createLottoTicketBundle(List<LottoTicket>... ticketsList) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (List<LottoTicket> tickets : ticketsList) {
            lottoTickets.addAll(tickets);
        }
        return new LottoTicketBundle(lottoTickets);
    }

    public WinLottoTicket createWinLottoTicket(List<Integer> winNumbers, int bonusNumber) {
        return defaultAutoMachine.createWinLottoTicket(winNumbers, bonusNumber);
    }

    public OverallResult createOverallResult(WinLottoTicket winLottoTicket, LottoTicketBundle lottoTicketBundle) {
        LottoMatchResultBundle lottoMatchResultBundle = lottoTicketBundle.createLottoMatchResultBundle(winLottoTicket);

        return lottoMatchResultBundle.createOverallResult();
    }

    public double computeAnalysis(OverallResult overallResult) {
        return overallResult.calculateWinRate();
    }
}
