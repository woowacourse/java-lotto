package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.domain.NumberValidator.validateIfEmptyValueThenInvokeException;
import static lotto.domain.NumberValidator.validateIfNotNumberThenInvokeException;

public class Lottos {
    public static final int MIN_MANUAL_LOTTO_COUNT = 0;

    private final List<Lotto> lottos;

    public Lottos(LottoMoney money, String manualLottoCount) {
        validate(money);
        int lottoCount = money.calculateLottoCount();
        validate(manualLottoCount, lottoCount);
        int manualLottoCountValue = Integer.parseInt(manualLottoCount);
        this.lottos = IntStream.range(0, lottoCount)
                .mapToObj(index -> Lotto.create())
                .collect(Collectors.toList());
    }

    private static void validate(LottoMoney money) {
        if (money == null) {
            throw new RuntimeException("LottoMoney로 null이 입력되었습니다.");
        }
    }

    private static void validate(String manualLottoCount, int lottoCount) {
        validateIfEmptyValueThenInvokeException(manualLottoCount, "수동으로 구매할 로또 수를 입력해 주세요.");
        validateIfNotNumberThenInvokeException(manualLottoCount, "수동으로 구매할 로또 수는 숫자만 입력 가능합니다.");
        validateIfNotInRangeThenInvokeException(manualLottoCount, lottoCount, String.format("수동으로 구매할 로또 수는 %d 이상 %d" +
                                                                                                    "(=구매한 총 로또 수) " +
                                                                                                    "이하여야 합니다.",
                                                                                            MIN_MANUAL_LOTTO_COUNT,
                                                                                            lottoCount));
    }

    private static void validateIfNotInRangeThenInvokeException(String manualLottoCount, int lottoCount,
                                                                String message) {
        int manualLottoCountValue = Integer.parseInt(manualLottoCount);
        if (manualLottoCountValue < MIN_MANUAL_LOTTO_COUNT || lottoCount < manualLottoCountValue) {
            throw new RuntimeException(message);
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
