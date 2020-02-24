package lotto.service;

import lotto.domain.result.LottoResultBundle;
import lotto.domain.result.rank.Rank;
import lotto.domain.ticket.LottoMachine;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.WinLottoTicket;
import lotto.view.dto.BettingMoneyDTO;
import lotto.view.dto.ResultDTO;
import lotto.view.dto.WinLottoTicketDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoService {
    private final LottoMachine lottoMachine;

    public LottoService(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public LottoTicketBundle createLottoTicketBundle(BettingMoneyDTO bettingMoneyDTO) {
        int bettingMoney = bettingMoneyDTO.getBettingMoney();

        return new LottoTicketBundle(lottoMachine.buyTickets(bettingMoney));
    }

    public List<ResultDTO> computeOverallWinResult(WinLottoTicketDTO winLottoTicketDTO, LottoTicketBundle lottoTicketBundle) {
        WinLottoTicket winLottoTicket = createWinLottoTicket(winLottoTicketDTO);

        LottoResultBundle lottoResultBundle = lottoTicketBundle.createLottoResultBundle(winLottoTicket);

        Map<Rank, Integer> overallWinResult = lottoResultBundle.computeOverallWinResult();

        return convertToResultDTOS(overallWinResult);
    }

    private WinLottoTicket createWinLottoTicket(WinLottoTicketDTO winLottoTicketDTO) {
        List<Integer> winNumbers = winLottoTicketDTO.getWinNumbers();
        int bonusNumber = winLottoTicketDTO.getBonusNumber();

        return lottoMachine.createWinLottoTicket(winNumbers, bonusNumber);
    }

    private List<ResultDTO> convertToResultDTOS(Map<Rank, Integer> overallWinResult) {
        return Arrays.stream(Rank.values())
                .map(aRank -> new ResultDTO(aRank, overallWinResult.get(aRank)))
                .collect(Collectors.toList());
    }

    public double computeAnalysis(List<ResultDTO> resultDTOS) {
        return resultDTOS.stream()
                .mapToDouble(dto -> dto.getNumberOfMatchTickets() * dto.getPrize())
                .sum();
    }
}
