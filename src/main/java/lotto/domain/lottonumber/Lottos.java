package lotto.domain.lottonumber;

import lotto.domain.matchkind.LottoMatchKind;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private static final int INITIAL_MATCH_COUNT = 0;

    private final List<Lotto> values;

    public Lottos(final List<Lotto> values) {
        this.values = values;
    }

    public Map<LottoMatchKind, Integer> match(final WinningNumbers winningNumbers) {
        final Map<LottoMatchKind, Integer> matchResult = new EnumMap<>(LottoMatchKind.class);
        initializeResult(matchResult);
        match(matchResult, winningNumbers);
        return matchResult;
    }

    private void initializeResult(final Map<LottoMatchKind, Integer> result) {
        for (LottoMatchKind matchKind : LottoMatchKind.values()) {
            result.put(matchKind, INITIAL_MATCH_COUNT);
        }
    }

    private void match(final Map<LottoMatchKind, Integer> matchResult, final WinningNumbers winningNumbers) {
        values.stream()
                .map(winningNumbers::getLottoMatchResult)
                .filter(lottoMatchKind -> lottoMatchKind != LottoMatchKind.LOWER_THAN_THREE)
                .forEach(lottoMatchKind -> matchResult.put(lottoMatchKind, matchResult.get(lottoMatchKind) + 1));
    }

    public List<Lotto> getLottos() {
        return values;
    }
}
