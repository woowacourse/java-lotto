package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Lottos {

    private List<Lotto> lottos = new ArrayList<>();

    public Lottos(int autoAmount) {
        makeRandomLottos(autoAmount);
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    private void makeRandomLottos(int autoAmount) {
        for (int i = 0; i < autoAmount; i++) {
            lottos.add(Lotto.ofRandomLotto());
        }
    }

    public void addMatchLotto(WinningLotto winningLotto, LottoGameResult lottoGameResult) {
        for (Lotto lotto : lottos) {
            lottoGameResult.add(winningLotto.findRank(lotto));
        }
    }

    public List<Lotto> toList() {

        return Collections.unmodifiableList(lottos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottos lottos1 = (Lottos) o;
        return Objects.equals(lottos, lottos1.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
