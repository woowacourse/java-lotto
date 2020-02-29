package lotto.service;

import lotto.domain.Money;
import lotto.domain.result.LottoMatchResultBundle;
import lotto.domain.result.OverallResult;
import lotto.domain.ticket.*;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private final LottoMachine autoMachine;

    public LottoService(LottoMachine autoMachine) {
        this.autoMachine = autoMachine;
    }

    public List<LottoTicket> createManualTickets(Money money, List<List<Integer>> manualNumbers) {
        LottoMachine manualMachine = new ManualLottoMachine(manualNumbers);

        return manualMachine.buyTickets(money.getNumberOfManualTickets());
    }

    public List<LottoTicket> createAutoTickets(Money money) {

        return autoMachine.buyTickets(money.getNumberOfLeftTickets());
    }

    public LottoTicketBundle createLottoTicketBundle(List<LottoTicket>... ticketsList) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (List<LottoTicket> tickets : ticketsList) {
            lottoTickets.addAll(tickets);
        }
        return new LottoTicketBundle(lottoTickets);
    }

    public WinLottoTicket createWinLottoTicket(List<Integer> winNumbers, int bonusNumber) {
        return autoMachine.createWinLottoTicket(winNumbers, bonusNumber);
    }

    public OverallResult createOverallResult(WinLottoTicket winLottoTicket, LottoTicketBundle lottoTicketBundle) {
        LottoMatchResultBundle lottoMatchResultBundle = lottoTicketBundle.createLottoMatchResultBundle(winLottoTicket);

        return lottoMatchResultBundle.createOverallResult();
    }

    public double computeAnalysis(OverallResult overallResult) {
        return overallResult.calculateWinRate();
    }
}
