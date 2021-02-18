package lotto.domain;

import java.util.ArrayList;
import java.util.Map;

public class Lottos {
    private static Money money;
    private static int count;
    private final ArrayList<Lotto> lottoGroup = new ArrayList<>();

    public Lottos(String input) {
        money = new Money(input);
        count = money.count();
        generateLottoGroup();
    }

    private void generateLottoGroup() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        for (int i = 0; i < count; i++) {
            Lotto generatedLotto = new Lotto(lottoGenerator.generateLottoNums());
            lottoGroup.add(generatedLotto);
        }
    }

    public static String findResult(Map<Rank, Integer> countByRank) {
        Money.findEarning(countByRank);
        return Money.findEarningRate();
    }

    public int getCount() {
        return count;
    }

    public ArrayList<Lotto> getLottoGroup() {
        return this.lottoGroup;
    }
}