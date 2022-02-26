package lotto.domain.lottonumber;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {

    private static final int LOTTO_NUMBER_COUNT = 6;
    static final String INVALID_LOTTO_NUMBER_COUNT = "[ERROR] 로또 숫자는 6개만 입력해야 합니다";
    static final String LOTTO_NUMBER_DUPLICATED = "[ERROR] 중복된 번호는 고를 수 없습니다.";

    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(String lottoNumbersString) {
        this.lottoNumbers = Arrays.stream(lottoNumbersString.split(","))
                .map(String::trim)
                .map(LottoNumber::new).collect(Collectors.toUnmodifiableSet());
        isCorrectLottoNumbers();
    }

    public LottoTicket(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new HashSet<>(lottoNumbers);
        isCorrectLottoNumbers();
    }

    public Set<LottoNumber> lottoNumbers() {
//        lottoNumbers = Set.copyOf(lottoNumbers);
        return Collections.unmodifiableSet(lottoNumbers);
    }

    private void isCorrectLottoNumbers() {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_COUNT);
        }
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }
}
