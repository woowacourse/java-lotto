package domain;

import domain.formatter.WinningCalculateFormatter;
import dto.BuyLottoResultDto;
import domain.enums.WinningCase;
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

    public LottoDispenser(List<Lotto> lottos, Money money) {
        this.lottos = lottos;
        this.money = money;
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


    public BuyLottoResultDto getBuyLottos() {
        return new BuyLottoResultDto(
             lottos.stream().map(Lotto::getLottoNumbers).toList(),
             money.calculateBuyLottoAmount()
         );
    }

    public String winningCalculateResultFormat(WinningLotto winningLotto){
        Map<WinningCase, Integer> winningCalculateResult = winningLotto.winningCalculate(lottos);
        long earnMoney = calculateTotalEarnMoney(winningCalculateResult);
        double earnMoneyRatio = money.calculateEarnMoneyRatio(earnMoney);
        return WinningCalculateFormatter.winningResultFormatting(winningCalculateResult,earnMoneyRatio);
    }

}
