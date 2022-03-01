package domain;

import dto.LottoNumberDto;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_SIZE = 6;
    private static final String ERROR_LOTTO_DUPLICATION_MESSAGE = "로또 번호를 중복해서 입력할 수 없습니다.";
    private static final String ERROR_LOTTO_LENGTH_MESSAGE = "로또 번호 6개를 입력하세요.";

    private final List<LottoNumber> lotto;

    public Lotto(final List<LottoNumber> lottoNumbers) {
        this.lotto = lottoNumbers;
    }

    public static Lotto fromInput(final List<String> inputLotto) {
        validate(inputLotto);
        return new Lotto(inputLotto.stream()
            .map(LottoNumber::from)
            .sorted()
            .collect(Collectors.toList()));
    }

    private static void validate(final List<String> inputLotto) {
        checkLottoDuplicated(inputLotto);
        checkoutLottoLength(inputLotto);
    }

    private static void checkoutLottoLength(final List<String> inputLotto) {
        if (inputLotto.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_LOTTO_LENGTH_MESSAGE);
        }
    }

    private static void checkLottoDuplicated(final List<String> inputLotto) {
        if (new HashSet<>(inputLotto).size() != inputLotto.size()) {
            throw new IllegalArgumentException(ERROR_LOTTO_DUPLICATION_MESSAGE);
        }
    }

    public boolean isContainNumber(final LottoNumber lottoNumber) {
        return this.lotto.contains(lottoNumber);
    }

    public int compare(final Lotto lotto) {
        return (int) this.lotto.stream()
            .filter(lotto::isContainNumber)
            .count();
    }

    public List<LottoNumberDto> toDto() {
        return this.lotto.stream()
            .map(LottoNumberDto::from)
            .collect(Collectors.toList());
    }

    public List<LottoNumber> get() {
        return this.lotto;
    }
}
