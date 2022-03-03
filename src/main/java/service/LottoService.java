package service;

import domain.Lotto;
import domain.LottoFactory;
import domain.LottoNumber;
import domain.Money;
import domain.Result;
import dto.LottoDto;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoService {

    private static final int LOTTO_SIZE = 6;
    private static final int AUTO_LOTTO_COUNT_LOWER_BOUND = 0;
    private static final String ERROR_BONUS_NUMBER_CONTAIN_MESSAGE = "보너스 볼 번호가 지난 주 당첨 번호와 일치할 수 없습니다.";
    private static final String ERROR_TOTAL_LOTTO_SIZE_MESSAGE = "번호는 6개를 입력하셔야 합니다.";
    private static final String ERROR_MANUAL_LOTTO_SIZE_MESSAGE = "전체 로또 갯수보다 클 수 없습니다.";

    private Lotto lastWinLotto;
    private List<Lotto> issuedLotto;

    public void initLastWinLotto(final List<String> inputLotto) {
        validate(inputLotto);
        this.lastWinLotto = Lotto.fromInput(inputLotto);
    }

    private void validate(final List<String> inputLotto) {
        if (inputLotto.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_TOTAL_LOTTO_SIZE_MESSAGE);
        }
    }

    public void issueLotto(final Money money, final List<List<String>> issuedManualLottoInput) {
        final List<Lotto> issuedManualLotto = LottoFactory.generateManualLottoGroup(issuedManualLottoInput);
        this.issuedLotto = addAutoLotto(money, issuedManualLotto);
    }

    private List<Lotto> addAutoLotto(final Money money, final List<Lotto> issuedManualLotto) {
        final int autoCount = money.getLottoCount() - issuedManualLotto.size();
        validate(autoCount);
        if (autoCount > AUTO_LOTTO_COUNT_LOWER_BOUND) {
            final List<Lotto> issuedAutoLotto = LottoFactory.generateAutoLottoGroup(autoCount);
            return concatLotto(issuedManualLotto, issuedAutoLotto);
        }
        return issuedManualLotto;
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

    public List<LottoDto> getIssuedLotto() {
        return issuedLotto.stream()
            .map(LottoDto::from)
            .collect(Collectors.toUnmodifiableList());
    }

    public Result calculateResult(final int bonusNumberInput) {
        final LottoNumber bonusNumber = new LottoNumber(bonusNumberInput);
        if (isBonusNumberContain(this.lastWinLotto, bonusNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_CONTAIN_MESSAGE);
        }
        return new Result(this.issuedLotto, this.lastWinLotto, bonusNumber);
    }

    private boolean isBonusNumberContain(final Lotto lotto, final LottoNumber bonusNumber) {
        return lotto.isContainNumber(bonusNumber);
    }
}
