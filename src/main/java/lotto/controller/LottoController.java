package lotto.controller;

import java.util.List;
import lotto.controller.dto.LottoResultDto;
import lotto.controller.dto.LottoTicketsDto;
import lotto.controller.dto.WinningNumberDto;
import lotto.controller.dto.MoneyDto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningNumber;

public class LottoController {

    public MoneyDto createMoney(int money) {
        return MoneyDto.from(new Money(money));
    }

    public LottoTicketsDto createLottoTickets(int money) {
        LottoMachine lottoMachine = new LottoMachine();
        LottoTickets lottoTickets = lottoMachine.issue(new Money(money));

        return new LottoTicketsDto(lottoTickets);
    }

    public WinningNumberDto createWinningNumber(List<Integer> normalNumbers, int bonusNumber) {
        WinningNumber winningNumber = new WinningNumber(new LottoTicket(normalNumbers), new LottoNumber(bonusNumber));
        return WinningNumberDto.from(winningNumber);
    }

    public LottoResultDto createLottoResult(int money, WinningNumberDto winningNumberDto,
                                            LottoTicketsDto lottoTicketsDto) {
        WinningNumber winningNumber = winningNumberDto.toWinningNumber();
        LottoTickets lottoTickets = lottoTicketsDto.toLottoTickets();
        LottoResult lottoResult = lottoTickets.determine(winningNumber);
        return new LottoResultDto(lottoResult.getRanks(), lottoResult.calculateYield(new Money(money)));
    }
}
