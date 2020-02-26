package lotto.controller;

import lotto.domain.result.OverallResult;
import lotto.domain.result.rank.Rank;
import lotto.domain.ticket.AutoLottoMachine;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.dto.BettingMoneyDTO;
import lotto.view.dto.LottoTicketDTO;
import lotto.view.dto.ResultDTO;
import lotto.view.dto.WinLottoTicketDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {


    private LottoService service = new LottoService(new AutoLottoMachine());

    public void run() {
        BettingMoneyDTO bettingMoney = new BettingMoneyDTO(InputView.inputBettingMoney());

        LottoTicketBundle lottoTicketBundle = service.createLottoTicketBundle(bettingMoney);

        OutputView.printLottoTickets(convertToLottoTicketDTOS(lottoTicketBundle));

        OverallResult overallResult = service.computeOverallWinResult(createWinLottoTicketDTO(), lottoTicketBundle);

        OutputView.printResult(convertToResultDTOS(overallResult), service.computeAnalysis(overallResult));
    }

    private List<LottoTicketDTO> convertToLottoTicketDTOS(LottoTicketBundle lottoTicketBundle) {
        List<LottoTicketDTO> lottoTicketDTOS = new ArrayList<>();

        for (LottoTicket lottoTicket : lottoTicketBundle.getLottoTickets()) {
            lottoTicketDTOS.add(new LottoTicketDTO(lottoTicket));
        }

        return lottoTicketDTOS;
    }

    private WinLottoTicketDTO createWinLottoTicketDTO() {
        List<Integer> winNumbers = InputView.inputWinningNumber();
        int bonusNumber = InputView.inputBonusNumber();

        return new WinLottoTicketDTO(winNumbers, bonusNumber);
    }

    private List<ResultDTO> convertToResultDTOS(OverallResult overallResult) {
        return Arrays.stream(Rank.values())
                .map(aRank -> new ResultDTO(aRank, overallResult.getNumberOfMatchTickets(aRank)))
                .collect(Collectors.toList());
    }
}
