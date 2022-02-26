package lotto.controller;

import java.util.List;
import lotto.controller.dto.LottoResultDto;
import lotto.controller.dto.LottoTicketsDto;
import lotto.controller.dto.WinningNumberDto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningNumber;

public class LottoController {

    public int createTotalCount(int money) {
        return new Money(money).calculateTicketCount();
    }

    public LottoTicketsDto createLottoTickets(int money) {
        return new LottoTicketsDto(new LottoMachine().issue(new Money(money)));
    }

    public WinningNumberDto createWinningNumber(List<Integer> normalNumbers, int bonusNumber) {
        return new WinningNumberDto(new WinningNumber(new LottoTicket(normalNumbers), new LottoNumber(bonusNumber)));
    }

    public LottoResultDto createLottoResult(int money, WinningNumberDto winningNumberDto, LottoTicketsDto lottoTicketsDto) {
        WinningNumber winningNumber = winningNumberDto.toWinningNumber();
        LottoTickets lottoTickets = lottoTicketsDto.toLottoTickets();
        LottoResult lottoResult = lottoTickets.determine(winningNumber);
        return new LottoResultDto(lottoResult.getRanks(), lottoResult.calculateYield(new Money(money)));
    }
}
