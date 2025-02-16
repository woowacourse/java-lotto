package service;

import domain.BonusNumber;
import domain.LottoDispenser;
import domain.WinningCase;
import domain.WinningNumber;
import domain.formatter.WinningCalculateFormatter;
import java.util.Map;
import repository.BonusNumberRepository;
import repository.LottoRepository;
import repository.WinningNumberRepository;

public class LottoService {

    private final LottoRepository lottoRepository;
    private final WinningNumberRepository winningNumberRepository;
    private final BonusNumberRepository bonusNumberRepository;

    public LottoService(LottoRepository lottoRepository, WinningNumberRepository winningNumberRepository,
                        BonusNumberRepository bonusNumberRepository) {
        this.lottoRepository = lottoRepository;
        this.winningNumberRepository = winningNumberRepository;
        this.bonusNumberRepository = bonusNumberRepository;
    }

    public void inputBuyLottoMoney(String inputBuyLottoMoney) {
        lottoRepository.saveLottoDispenser(new LottoDispenser(inputBuyLottoMoney));
    }

    public void inputWinningNumber(String inputWinningNumber) {
        winningNumberRepository.saveWinningNumber(new WinningNumber(inputWinningNumber));
    }

    public void inputBonusNumber(String inputBonusNumber) {
        bonusNumberRepository.saveBonusNumber(new BonusNumber(inputBonusNumber));
    }

    public String winningCalculate() {
        LottoDispenser lottoDispenser = lottoRepository.getLottoDispenser();
        WinningNumber winningNumber = winningNumberRepository.getWinningNumber();
        BonusNumber bonusNumber = bonusNumberRepository.getBonusNumber();
        Map<WinningCase, Integer> winningCalculateResult = lottoDispenser.winningCalculate(winningNumber, bonusNumber);
        long earnMoney = lottoDispenser.calculateEarnMoney(winningCalculateResult);
        double earnMoneyRatio = lottoDispenser.calculateEarnMoneyRatio(earnMoney);
        return WinningCalculateFormatter.winningResultFormatting(winningCalculateResult, earnMoneyRatio);
    }

    public String buyLottoResult() {
        return lottoRepository.getLottoDispenser()
                .buyLottoResult();
    }
}
