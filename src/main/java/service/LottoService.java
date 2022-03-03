package service;

import domain.Count;
import domain.Lotto;
import domain.LottoFactory;
import domain.LottoNumber;
import domain.Money;
import domain.Result;
import domain.generator.AutoLottoGenerator;
import domain.generator.LottoGenerator;
import domain.generator.ManualLottoGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoService {

    private static final int AUTO_LOTTO_COUNT_LOWER_BOUND = 0;
    private static final String ERROR_BONUS_NUMBER_CONTAIN_MESSAGE = "보너스 볼 번호가 지난 주 당첨 번호와 일치할 수 없습니다.";
    private static final String ERROR_MANUAL_LOTTO_SIZE_MESSAGE = "전체 로또 갯수보다 클 수 없습니다.";

    public List<Lotto> issueManualLottoGroup(final List<List<String>> issuedManualLottoInput) {
        final List<Lotto> issuedManualLotto = new ArrayList<>();
        for (List<String> manualLottoInput : issuedManualLottoInput) {
            issuedManualLotto.add(LottoFactory.generateLotto(new ManualLottoGenerator(manualLottoInput)));
        }
        return Collections.unmodifiableList(issuedManualLotto);
    }

    public List<Lotto> issueLotto(final Money totalMoney, final List<Lotto> issuedManualLotto) {
        return issueAutoLottoGroup(totalMoney, issuedManualLotto);
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

    public Result calculateResult(final List<Lotto> issuedLotto, final Lotto winLotto,
                                  final LottoNumber bonusNumber) {
        if (isBonusNumberContain(winLotto, bonusNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_CONTAIN_MESSAGE);
        }
        return new Result(issuedLotto, winLotto, bonusNumber);
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
    
    private boolean isBonusNumberContain(final Lotto lotto, final LottoNumber bonusNumber) {
        return lotto.isContainNumber(bonusNumber);
    }
}
