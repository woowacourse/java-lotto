package lotto.domain;

import java.util.List;

public class UserLottos {
    private final List<Lotto> lottos;

    public UserLottos(List<Lotto> lottos) {
        this.lottos = lottos;
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
