package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Lottos {

    private static final String ERROR_MESSAGE_NULL = "값이 null 입니다.";

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        Objects.requireNonNull(lottos, ERROR_MESSAGE_NULL);
        this.lottos = new ArrayList<>(lottos);
    }

    public List<LottoReward> calculateLottoReward(WinningLotto winningLotto) {
        List<LottoReward> lottoRewards = new ArrayList<>();
        for (Lotto lotto : lottos) {
            lottoRewards.add(winningLotto.calculateMatchResult(lotto));
        }

        return lottoRewards;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
