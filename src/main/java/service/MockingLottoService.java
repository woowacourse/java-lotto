package service;

import domain.BonusNumber;
import domain.Lotto;
import domain.LottoDispenser;
import domain.WinningCase;
import domain.WinningNumber;
import domain.WinningCalculateFormatter;
import java.util.List;
import java.util.Map;
import repository.BonusNumberRepository;
import repository.LottoRepository;
import repository.WinningNumberRepository;

public class MockingLottoService extends LottoService {

    public MockingLottoService(LottoRepository lottoRepository, WinningNumberRepository winningNumberRepository,
                               BonusNumberRepository bonusNumberRepository) {
        super(lottoRepository, winningNumberRepository, bonusNumberRepository);
    }

    public String winningCalculate(List<Lotto> lottos, String winningNumberInput, String bonusMoneyInput) {
        LottoDispenser lottoDispenser = new LottoDispenser(lottos);
        WinningNumber winningNumber = new WinningNumber(winningNumberInput);
        BonusNumber bonusNumber = new BonusNumber(bonusMoneyInput);
        Map<WinningCase, Integer> winningCalculateResult = lottoDispenser.winningCalculate(winningNumber, bonusNumber);
        long earnMoney = lottoDispenser.calculateEarnMoney(winningCalculateResult);
        double earnMoneyRatio = lottoDispenser.calculateEarnMoneyRatio(earnMoney);
        return WinningCalculateFormatter.winningResultFormatting(winningCalculateResult, earnMoneyRatio);
    }
}
