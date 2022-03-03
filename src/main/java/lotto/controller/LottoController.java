package lotto.controller;

import static lotto.domain.LottoMachine.*;

import java.util.List;
import java.util.stream.Collectors;
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

    public SalesInfoDto purchase(PurchaseInfoDto purchaseInfo) {
        LottoMachine lottoMachine = new LottoMachine();
        Money money = new Money(purchaseInfo.getMoney());

        LottoTickets lottoTickets = lottoMachine.issueManual(purchaseInfo.getManualNumbers());
        Money calculateMoney = money.calculateProduct(LottoMachine.LOTTO_PRICE, purchaseInfo.getManualCount());

        int autoCount = calculateMoney.getProductCount(LOTTO_PRICE);
        lottoTickets.combine(lottoMachine.issueAuto(autoCount));

        return SalesInfoDto.valueOf(money.getAmount(), purchaseInfo.getManualCount(), autoCount,
                LottoTicketsDto.from(lottoTickets));
    }

    public WinningNumberDto createWinningNumber(List<Integer> normalNumbers, int bonusNumber) {
        List<LottoNumber> numbers = normalNumbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList());
        
        WinningNumber winningNumber = new WinningNumber(new LottoTicket(numbers), LottoNumber.of(bonusNumber));

        return WinningNumberDto.from(winningNumber);
    }

    public LottoResultDto createLottoResult(SalesInfoDto salesInfoDto, WinningNumberDto winningNumberDto) {
        WinningNumber winningNumber = winningNumberDto.toWinningNumber();
        LottoTicketsDto totalLottoTickets = salesInfoDto.getLottoTickets();
        LottoTickets lottoTickets = totalLottoTickets.toLottoTickets();

        LottoResult lottoResult = lottoTickets.determine(winningNumber);
        double yield = lottoResult.calculateYield(new Money(salesInfoDto.getMoney()));

        return LottoResultDto.from(lottoResult.getRanks(), yield);
    }
}
