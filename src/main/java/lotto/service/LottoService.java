package lotto.service;

import lotto.domain.customer.Customer;
import lotto.domain.result.LottoMatchResultBundle;
import lotto.domain.result.OverallResult;
import lotto.domain.ticket.LottoMachine;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.WinLottoTicket;
import lotto.view.dto.WinLottoTicketDTO;

import java.util.List;

public class LottoService {
    public List<LottoTicket> createLottoTickets(LottoMachine machine, Customer customer) {
        return machine.buyTickets(customer);
    }

    public LottoTicketBundle createLottoTicketBundle(List<LottoTicket> lottoTickets) {
        return new LottoTicketBundle(lottoTickets);
    }

    public WinLottoTicket createWinLottoTicket(WinLottoTicketDTO winLottoTicketDTO) {
        return new WinLottoTicket(winLottoTicketDTO.getWinNumbers(), winLottoTicketDTO.getBonusNumber());
    }

    public OverallResult createOverallResult(WinLottoTicket winLottoTicket, LottoTicketBundle lottoTicketBundle) {
        LottoMatchResultBundle lottoMatchResultBundle = lottoTicketBundle.createLottoMatchResultBundle(winLottoTicket);

        return lottoMatchResultBundle.createOverallResult();
    }

    public double computeAnalysis(OverallResult overallResult) {
        return overallResult.calculateWinRate();
    }
}
