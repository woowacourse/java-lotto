package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(LottoMoney money) {
        validate(money);
        int lottoCount = money.calculateLottoCount();
        this.lottos = IntStream.range(0, lottoCount)
                .mapToObj(index -> Lotto.create())
                .collect(Collectors.toList());
    }

    private static void validate(LottoMoney money) {
        if (money == null) {
            throw new RuntimeException("LottoMoney로 null이 입력되었습니다.");
        }
    }

    public MatchResults toMatchResults(Lotto winningLotto, LottoNumber bonusNumber) {
        List<MatchResult> matchResults = lottos.stream()
                .filter(lotto -> MatchResult.hasMatchCount(lotto.countSameNumbers(winningLotto)))
                .map(lotto -> lotto.createResult(winningLotto, bonusNumber))
                .collect(Collectors.toList());
        return new MatchResults(matchResults);
    }

    public List<Lotto> get() {
        return Collections.unmodifiableList(lottos);
    }
}
