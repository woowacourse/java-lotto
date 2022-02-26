package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.model.number.LottoNumbers;
import lotto.model.prize.MatchResult;

/*
 * 구입한 로또들을 담는 일급 컬렉션 Class
 */
public class Lottos {

    private List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos purchaseAuto(Money money) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < Lotto.countAvailableTickets(money); i++) {
            lottos.add(new Lotto(LottoNumbers.ofRandomNumbers()));
        }
        return new Lottos(lottos);
    }

    public List<MatchResult> match(WinningLotto winningLotto) {
        return this.lottos.stream()
                .map(winningLotto::match)
                .collect(Collectors.toList());
    }

    public List<Lotto> getLottos() {
        return this.lottos;
    }

    public int getSize() {
        return this.lottos.size();
    }
}
