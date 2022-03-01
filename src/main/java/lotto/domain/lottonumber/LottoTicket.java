package lotto.domain.lottonumber;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class LottoTicket {

    private static final int LOTTO_NUMBER_COUNT = 6;
    static final String INVALID_LOTTO_NUMBER_COUNT = "[ERROR] 로또 숫자는 6개만 입력해야 합니다";

    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(String lottoNumbersString) {
        this.lottoNumbers = createLottoNumbersFromString(lottoNumbersString);
        isCorrectLottoNumbers();
    }

    public LottoTicket(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new HashSet<>(lottoNumbers);
        isCorrectLottoNumbers();
    }

    @NotNull
    private Set<LottoNumber> createLottoNumbersFromString(String lottoNumbersString) {
        return Arrays.stream(lottoNumbersString.split(","))
                .map(String::trim)
                .map(LottoNumber::new)
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<LottoNumber> lottoNumbers() {
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

    public int getMatchCount(LottoTicket lottoTicket) {
        return (int) this.lottoNumbers.stream()
                .filter(lottoTicket::contains)
                .count();
    }
}
