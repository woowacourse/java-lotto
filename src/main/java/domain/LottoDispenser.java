package domain;

import dto.BuyLottoResultDto;
import domain.enums.WinningCase;
import exception.LottoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import utility.StringUtility;

public class LottoDispenser {

    private final int LOTTO_MONEY_UNIT = 1000;
    private final String INVALID_BUY_MONEY = "유효하지 않은 구매 금액입니다.";
    private final List<Lotto> lottos;
    private final int buyMoney;

    public LottoDispenser(String buyMoneyInput) {
        validateLottoDispenser(buyMoneyInput);
        this.buyMoney = Integer.parseInt(buyMoneyInput);
        lottos = generateLottos(calculateBuyLottoCount());
    }

    private int calculateBuyLottoCount() {
        int lottoCount = calculateBuyLottoAmount();
        return lottoCount;
    }

    private int calculateBuyLottoAmount() {
        return buyMoney / LOTTO_MONEY_UNIT;
    }

    public LottoDispenser(List<Lotto> lottos) {
        this.lottos = lottos;
        this.buyMoney = lottos.size() * LOTTO_MONEY_UNIT;
    }

    private void validateLottoDispenser(String buyMoney) {
        if(buyMoney == null || !StringUtility.isNumber(buyMoney)){
            throw new LottoException(INVALID_BUY_MONEY);
        }
        int buyMoneyNumber = Integer.parseInt(buyMoney);
        if(buyMoneyNumber == 0){
            throw new LottoException(INVALID_BUY_MONEY);
        }
        if(buyMoneyNumber % LOTTO_MONEY_UNIT != 0){
            throw new LottoException(INVALID_BUY_MONEY);
        }
    }

    private List<Lotto> generateLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(LottoRandomGenerator.generateNumbers()));
        }
        return lottos;
    }

    public Map<WinningCase,Integer> winningCalculate(WinningNumber winningNumber, BonusNumber bonusNumber) {
        Map<WinningCase,Integer> winningResult = WinningCase.toMap();
        for(Lotto lotto : lottos){
            int sameCount = lotto.compare(winningNumber, bonusNumber);
            boolean isBonus = lotto.compareBonusNumber(bonusNumber);
            WinningCase winningCase = WinningCase.getWinningCase(sameCount, isBonus);
            winningResult.put(winningCase,winningResult.getOrDefault(winningCase,0)+1);
        }
        return winningResult;
    }

    public long calculateEarnMoney(Map<WinningCase, Integer> winningCalculateResult) {
        long earnMoneySum = 0;
        for (Entry<WinningCase, Integer> winningCaseIntegerEntry : winningCalculateResult.entrySet()) {
            long earnMoney = winningCaseIntegerEntry.getKey()
                    .calculateEarnMoney(winningCaseIntegerEntry.getValue());
            earnMoneySum += earnMoney;
        }
        return earnMoneySum;
    }

    public double calculateEarnMoneyRatio(long earnMoney) {
        return  (double) earnMoney / buyMoney;
    }

    public BuyLottoResultDto getBuyLottos() {
        return new BuyLottoResultDto(
             lottos.stream().map(Lotto::getLottoNumbers).toList(),
             calculateBuyLottoAmount()
         );
    }

}
