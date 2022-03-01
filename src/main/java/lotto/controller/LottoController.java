package lotto.controller;

import java.util.List;
import lotto.controller.dto.LottoResultDto;
import lotto.controller.dto.LottoTicketsDto;
import lotto.controller.dto.PurchaseInfoDto;
import lotto.controller.dto.SalesInfoDto;
import lotto.controller.dto.WinningNumberDto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningNumber;

public class LottoController {

    public SalesInfoDto purchase(PurchaseInfoDto purchaseInfoDto) {
        LottoMachine lottoMachine = new LottoMachine();
        Money money = new Money(purchaseInfoDto.getMoney());
        int manualCount = purchaseInfoDto.getManualCount();
        List<List<Integer>> manualNumbers = purchaseInfoDto.getManualNumbers();

        LottoTickets manualLottoTickets = lottoMachine.issueManualLottoTickets(manualCount, manualNumbers);
        Money calculateMoney = money.calculateProduct(LottoMachine.LOTTO_PRICE, manualCount);

        int autoCount = calculateMoney.getProductCount(LottoMachine.LOTTO_PRICE);
        LottoTickets autoLottoTickets = lottoMachine.issueAutoLottoTickets(autoCount);

        LottoTickets totalLottoTickets = manualLottoTickets.combine(autoLottoTickets);

        return SalesInfoDto.valueOf(manualCount, autoCount, LottoTicketsDto.from(totalLottoTickets));
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

        return LottoResultDto.from(lottoResult.getRanks(), lottoResult.calculateYield(new Money(money)));
    }
}
