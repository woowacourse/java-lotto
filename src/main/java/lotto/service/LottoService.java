package lotto.service;

import lotto.domain.customer.Customer;
import lotto.domain.result.LottoMatchResultBundle;
import lotto.domain.result.OverallResult;
import lotto.domain.result.rank.Rank;
import lotto.domain.ticket.*;
import lotto.view.dto.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoService {
    private static final List<LottoMachine> lottoMachines = Arrays.asList(new AutoLottoMachine(), new ManualLottoMachine());

    public LottoTicketBundle createLottoTicketBundle(Customer customer) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (LottoMachine machine : lottoMachines) {
            lottoTickets.addAll(machine.buyTickets(customer));
        }
        return new LottoTicketBundle(lottoTickets);
    }

    public PurchaseStatusDTO createPurchaseStatusDTO(Customer customer) {
        return new PurchaseStatusDTO(customer.getPurchaseInfo().getNumberOfManualTickets(), customer.getPurchaseInfo().getNumberOfLeftTickets());
    }

    public List<LottoTicketDTO> convertToLottoTicketDTOS(LottoTicketBundle lottoTicketBundle) {
        return lottoTicketBundle.getLottoTickets().stream()
                .map(LottoTicketDTO::new)
                .collect(Collectors.toList());
    }

    public WinLottoTicket createWinLottoTicket(WinLottoTicketDTO winLottoTicketDTO) {
        return new WinLottoTicket(winLottoTicketDTO.getWinNumbers(), winLottoTicketDTO.getBonusNumber());
    }

    public OverallResult createOverallResult(WinLottoTicket winLottoTicket, LottoTicketBundle lottoTicketBundle) {
        LottoMatchResultBundle lottoMatchResultBundle = lottoTicketBundle.createLottoMatchResultBundle(winLottoTicket);
        return lottoMatchResultBundle.createOverallResult();
    }

    public List<ResultDTO> convertToResultDTOS(OverallResult overallResult) {
        return Arrays.stream(Rank.values())
                .map(aRank -> new ResultDTO(aRank, overallResult.getNumberOfMatchTickets(aRank)))
                .collect(Collectors.toList());
    }

    public StatisticsDTO createStatisticsDTO(OverallResult overallResult) {
        return new StatisticsDTO(overallResult.calculateWinRate());
    }
}
