package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class UserLottos {
    private final List<Lotto> lottos;

    public UserLottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public UserLottos(int number) {
        lottos = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            lottos.add(LottoGenerator.lotto());
        }
    }

    public int match(Lotto another) {
        int matches = 0;
        for (Lotto lotto : lottos) {
            for (Integer number : another.numbers()) {
                if (lotto.numbers().contains(number)) {
                    matches++;
                }
            }
        }
        return matches;
    }

    public int size() {
        return lottos.size();
    }
}
