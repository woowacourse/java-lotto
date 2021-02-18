package lotto.domain;

import java.util.ArrayList;
import java.util.Map;

public class Lottos {

    private static Money money;
    private static int count;
    ArrayList<Lotto> lottoGroup = new ArrayList<>();

    public Lottos(String input) {
        this.money = new Money(input);
        count = money.count();
        generateLottoGroup();
    }

    public static String findResult(Map<Rank, Integer> countByRank) {
        money.findEarning(countByRank);
        return money.findEarningRate();
    }

    public void generateLottoGroup() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        for (int i = 0; i < count; i++) {
            Lotto generatedLotto = new Lotto(lottoGenerator.generateLottoNums());
            lottoGroup.add(generatedLotto);
        }
    }

    public int getCount() {
        return count;
    }

    public ArrayList<Lotto> getLottoGroup() {
        return this.lottoGroup;
    }
}