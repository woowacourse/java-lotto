package domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {

    public static final int LOTTO_TICKET_SIZE = 6;
    public static final int LOTTO_TICKET_PRICE = 1000;
    private static final String INCORRECT_LOTTO_NUMBER_SIZE_ERROR = "로또 숫자의 개수가 6이 아닙니다.";

    private final Set<LottoNumber> lottoNumbers;

    private LottoTicket(final List<LottoNumber> numbers) {
        LinkedHashSet<LottoNumber> lottoNumbers = new LinkedHashSet<>(numbers);
        validateIncorrectSize(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket valueOf(final List<Integer> numbers) {
        return new LottoTicket(generateLottoNumbers(numbers));
    }

    private static List<LottoNumber> generateLottoNumbers(final List<Integer> numbers) {
        return numbers.stream()
            .sorted()
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());
    }

    private void validateIncorrectSize(final Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException(INCORRECT_LOTTO_NUMBER_SIZE_ERROR);
        }
    }

    public int countMatchingNumbers(LottoTicket lottoTicket) {
        return (int) lottoNumbers.stream()
            .filter(lottoTicket::contains)
            .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public Set<LottoNumber> toSet() {
        return Collections.unmodifiableSet(lottoNumbers);
    }
}
