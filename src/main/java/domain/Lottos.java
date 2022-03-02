package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        validateNull(lottos);
        validateSize(lottos);
        this.lottos = new ArrayList<>(lottos);
    }

    private void validateNull(List<Lotto> lottos) {
        if (lottos == null) {
            throw new NullPointerException("null 로 Lottos 를 생성할 수 없습니다.");
        }
    }

    private void validateSize(List<Lotto> lottos) {
        if (lottos.isEmpty()) {
            throw new IllegalArgumentException("Lottos 를 생성하려면 1개 이상의 로또가 존재해야 합니다.");
        }
    }

    public List<LottoReward> match(WinningLotto winningLotto) {
        List<LottoReward> lottoRewards = new ArrayList<>();
        for (Lotto lotto : lottos) {
            LottoReward reward = winningLotto.match(lotto);
            lottoRewards.add(reward);
        }

        return lottoRewards;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
