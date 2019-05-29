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

    public int match(WinningLotto winningLotto) {
        int money = 0;
        for (Lotto lotto : lottos) {
            money += winningLotto.match(lotto);
        }
        return money / (lottos.size() * 1000);
    }

    public int size() {
        return lottos.size();
    }
}
