package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class UserLottos {
    private final List<Lotto> lottos;

    public UserLottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public UserLottos(String inputMoney) {
        int lottoCount = Integer.parseInt(inputMoney) / 1000;
        lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(LottoGenerator.lotto());
        }
    }

    public double match(Lotto another) {
        int money = 0;
        for (Lotto lotto : lottos) {
            money += Reward.valueOf(getMatches(another, lotto)).money();
        }
        return money / (lottos.size() * 1000);
    }

    private int getMatches(Lotto another, Lotto lotto) {
        int matches = 0;
        for (Integer number : another.numbers()) {
            if (lotto.numbers().contains(number)) {
                matches++;
            }
        }
        return matches;
    }

    public int size() {
        return lottos.size();
    }
}
