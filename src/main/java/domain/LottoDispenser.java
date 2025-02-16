package domain;

import dto.BuyLottoResultDto;
import dto.DrawResultDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LottoDispenser {

    private final List<Lotto> lottos;
    private final Money money;

    public LottoDispenser(String buyMoneyInput) {
        this.money = new Money(buyMoneyInput);
        lottos = generateLottos(money.calculateBuyLottoCount());
    }

    public DrawResultDto getDrawResult(WinningLotto winningLotto){
        Map<WinningCase, Integer> winningCalculateResult = winningLotto.winningCalculate(lottos);
        long earnMoney = calculateTotalEarnMoney(winningCalculateResult);
        double earnMoneyRatio = money.calculateEarnMoneyRatio(earnMoney);
        return new DrawResultDto(winningCalculateResult,earnMoneyRatio);
    }

    public BuyLottoResultDto getBuyLottos() {
        return new BuyLottoResultDto(
            lottos.stream().map(Lotto::getLottoNumbers).toList(),
            money.calculateBuyLottoAmount()
        );
    }

    private List<Lotto> generateLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(LottoRandomGenerator.generateNumbers()));
        }
        return lottos;
    }

    private long calculateTotalEarnMoney(Map<WinningCase, Integer> winningCalculateResult) {
        long earnMoneySum = 0;
        for (Entry<WinningCase, Integer> winningCaseIntegerEntry : winningCalculateResult.entrySet()) {
            long earnMoney = winningCaseIntegerEntry.getKey()
                    .calculateEarnMoney(winningCaseIntegerEntry.getValue());
            earnMoneySum += earnMoney;
        }
        return earnMoneySum;
    }

}
