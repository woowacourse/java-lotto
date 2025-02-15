package service;

import domain.BonusNumber;
import domain.LottoDispenser;
import domain.WinningNumber;
import dto.BuyLottoResultDto;
import domain.enums.WinningCase;
import domain.formatter.WinningCalculateFormatter;
import exception.LottoException;
import java.util.Map;
import repository.BonusNumberRepository;
import repository.LottoRepository;
import repository.WinningNumberRepository;

public class LottoService {

    private final LottoRepository lottoRepository;
    private final WinningNumberRepository winningNumberRepository;
    private final BonusNumberRepository bonusNumberRepository;
    private final String INVALID_BONUS_NUMBER = "유효하지 않은 보너스번호입니다.";

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
        BonusNumber bonusNumber;
        validateDuplicationBonusNumber(
            winningNumberRepository.getWinningNumber(),
            bonusNumber = new BonusNumber(inputBonusNumber)
        );
        bonusNumberRepository.saveBonusNumber(bonusNumber);
    }

    public String winningCalculate(){
        LottoDispenser lottoDispenser = lottoRepository.getLottoDispenser();
        WinningNumber winningNumber = winningNumberRepository.getWinningNumber();
        BonusNumber bonusNumber = bonusNumberRepository.getBonusNumber();
        Map<WinningCase, Integer> winningCalculateResult = lottoDispenser.winningCalculate(winningNumber, bonusNumber);
        long earnMoney = lottoDispenser.calculateEarnMoney(winningCalculateResult);
        double earnMoneyRatio = lottoDispenser.calculateEarnMoneyRatio(earnMoney);
        return WinningCalculateFormatter.winningResultFormatting(winningCalculateResult,earnMoneyRatio);
    }

    public BuyLottoResultDto getBuyLottos() {
        return lottoRepository.getLottoDispenser().getBuyLottos();
    }

    private void validateDuplicationBonusNumber(WinningNumber winningNumber, BonusNumber bonusNumber) {
        if (bonusNumber.isDuplicate(winningNumber)) {
            throw new LottoException(INVALID_BONUS_NUMBER);
        }
    }
}
