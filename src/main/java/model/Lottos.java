package model;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public void addLotto(Lotto lotto) {
        lottos.add(lotto);
    }

    public int countTiers(PrizeTier prizeTier) {
        int count = 0;
        for (Lotto lotto : lottos) {
            if (lotto.isTierMatched(prizeTier)) {
                count++;
            }
        }
        return count;
    }

    public long calculateTotalPrize() {
        long totalPrize = 0;
        for (Lotto lotto : lottos) {
            totalPrize += lotto.extractPrize();
        }
        return totalPrize;
    }


}
