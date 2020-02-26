package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(LottoCount lottoCount, List<String[]> manualLottos) {
        validate(lottoCount, manualLottos);
        this.lottos = createLottos(lottoCount, manualLottos);
    }

    private static void validate(LottoCount lottoCount, List<String[]> manualLottos) {
        if (lottoCount == null || manualLottos == null) {
            throw new RuntimeException("Lottos 생성자의 매개변수로 null이 입력되었습니다.");
        }
    }

    private static List<Lotto> createLottos(LottoCount lottoCount, List<String[]> manualLottos) {
        List<Lotto> lottos = new ArrayList<>();
        if (!manualLottos.isEmpty()) {
            putManualLotto(lottos, manualLottos);
        }
        for (int i = 0; i < lottoCount.calculateAutoLottoCount(); i++) {
            lottos.add(Lotto.create());
        }
        return lottos;
    }

    private static void putManualLotto(List<Lotto> lottos, List<String[]> manualLottos) {
        for (String[] manualLotto : manualLottos) {
            lottos.add(Lotto.from(manualLotto));
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
