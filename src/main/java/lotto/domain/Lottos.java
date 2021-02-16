package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottoBunch = new ArrayList<>();

    public Lottos(int purchasedLottoCount) {
        for (int i = 0; i < purchasedLottoCount; i++) {
            lottoBunch.add(new Lotto());
        }
    }

    public int getSize() {
        return lottoBunch.size();
    }
}
