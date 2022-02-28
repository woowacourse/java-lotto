package domain;

import dto.LottoNumberDto;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_SIZE = 6;
    private static final String ERROR_LOTTO_SIZE_MESSAGE = "로또 번호 6개를 입력해주세요.";

    private final List<LottoNumber> lotto;

    public Lotto(final List<LottoNumber> lotto) {
        this.lotto = lotto;
    }

    private void checkSplitNumbersCount(final List<String> splitNumbers) {
        if (splitNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE_MESSAGE);
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
}
