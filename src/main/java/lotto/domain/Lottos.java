package lotto.domain;

import java.util.ArrayList;
import java.util.Map;

public class Lottos {
    private final ArrayList<Lotto> lottoGroup = new ArrayList<>();

    public Lottos(String input) {
        Money money = new Money(input);
        int count = money.count();
        generateLottoGroup(count);
    }

    private void generateLottoGroup(int count) {
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

    public ArrayList<Lotto> getLottoGroup() {
        return this.lottoGroup;
    }
}