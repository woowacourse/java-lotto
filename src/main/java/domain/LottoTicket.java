package domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTicket {
    private static final String DUPLICATE_NUMBER_ERROR = "중복 숫자가 존재합니다.";
    private static final String INCORRECT_LOTTO_NUMBER_SIZE_ERROR = "로또 숫자의 개수가 6이 아닙니다.";
    public static final int LOTTO_TICKET_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    private LottoTicket(final Set<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoTicket valueOf(final List<Integer> numbers) {
        return new LottoTicket(generateLottoNumbers(new HashSet<>(numbers)));
    }

    private static Set<LottoNumber> generateLottoNumbers(final HashSet<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toSet());
    }

    private static void validate(final Set<LottoNumber> lottoNumbers) {
        validateDuplicateNumbers(lottoNumbers);
        validateIncorrectSize(lottoNumbers);
    }

    private static void validateDuplicateNumbers(final Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() < LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER_ERROR);
        }
    }

    private static void validateIncorrectSize(final Set<LottoNumber> lottoNumbers) {
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
