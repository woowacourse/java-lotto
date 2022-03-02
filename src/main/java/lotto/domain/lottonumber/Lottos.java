package lotto.domain.lottonumber;

import lotto.domain.matchkind.LottoMatchKind;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private static final int INITIAL_MATCH_COUNT = 0;
    private static final String LOTTOS_DUPLICATION_EXCEPTION_MESSAGE = "로또 숫자들 간에 중복이 있습니다.";

    private final List<Lotto> values;

    public Lottos(final List<Lotto> values) {
        validateDuplication(values);
        this.values = values;
    }

    private void validateDuplication(final List<Lotto> values) {
        final int distinctCount = (int) values.stream()
                .distinct()
                .count();
        if (distinctCount != values.size()) {
            throw new IllegalArgumentException(LOTTOS_DUPLICATION_EXCEPTION_MESSAGE);
        }
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
                .forEach(lottoMatchKind -> matchResult.put(lottoMatchKind, matchResult.get(lottoMatchKind) + 1));
    }

    public List<Lotto> getLottos() {
        return values;
    }
}
