package service;

import domain.Count;
import domain.Lotto;
import domain.LottoFactory;
import domain.Money;
import domain.Result;
import domain.WinLotto;
import domain.generator.AutoLottoGenerator;
import domain.generator.LottoGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoService {

    private static final int AUTO_LOTTO_COUNT_LOWER_BOUND = 0;
    private static final String ERROR_MANUAL_LOTTO_SIZE_MESSAGE = "전체 로또 갯수보다 클 수 없습니다.";

    public List<Lotto> issueLotto(final Money totalMoney, final List<Lotto> issuedManualLotto) {
        return issueAutoLottoGroup(totalMoney, issuedManualLotto);
    }

    public Result getResult(final List<Lotto> issuedLotto, final WinLotto lastWinLotto) {
        return new Result(issuedLotto, lastWinLotto);
    }

    private List<Lotto> issueAutoLottoGroup(final Money totalMoney, final List<Lotto> issuedManualLotto) {
        final int autoCount = totalMoney.getLottoCount() - issuedManualLotto.size();
        validate(autoCount);
        if (autoCount > AUTO_LOTTO_COUNT_LOWER_BOUND) {
            final List<Lotto> issuedAutoLotto = generateAutoLottoGroup(autoCount);
            return concatLotto(issuedManualLotto, issuedAutoLotto);
        }
        return issuedManualLotto;
    }

    public static List<Lotto> generateAutoLottoGroup(final int count) {
        final List<Lotto> issuedAutoLotto = accumulateAutoLottoWithCount(new AutoLottoGenerator(), new Count(count));
        return Collections.unmodifiableList(issuedAutoLotto);
    }

    private static List<Lotto> accumulateAutoLottoWithCount(final LottoGenerator lottoGenerator, Count count) {
        final List<Lotto> issuedLotto = new ArrayList<>();
        while (!count.isEnd()) {
            count = count.decrease();
            issuedLotto.add(LottoFactory.generateLotto(lottoGenerator));
        }
        return issuedLotto;
    }

    private void validate(final int autoCount) {
        if (autoCount < AUTO_LOTTO_COUNT_LOWER_BOUND) {
            throw new IllegalArgumentException(ERROR_MANUAL_LOTTO_SIZE_MESSAGE);
        }
    }

    private List<Lotto> concatLotto(final List<Lotto> issuedManualLotto, final List<Lotto> issuedAutoLotto) {
        return Stream.concat(
                issuedManualLotto.stream(),
                issuedAutoLotto.stream())
            .collect(Collectors.toList());
    }
}
