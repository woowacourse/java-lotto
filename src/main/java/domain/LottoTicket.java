package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
    private static final String DUPLICATE_NUMBER_ERROR = "중복 숫자가 존재합니다.";
    private static final String INCORRECT_LOTTO_NUMBER_SIZE_ERROR = "로또 숫자의 개수가 6이 아닙니다.";

    private static final int LOTTO_TICKET_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    private LottoTicket(final List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
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

    private static void validate(final List<LottoNumber> lottoNumbers) {
        validateDuplicateNumbers(lottoNumbers);
        validateIncorrectSize(lottoNumbers);
    }

    private static void validateDuplicateNumbers(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != new HashSet<>(lottoNumbers).size()) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER_ERROR);
        }
    }

    private static void validateIncorrectSize(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException(INCORRECT_LOTTO_NUMBER_SIZE_ERROR);
        }
    }

    public static int getLottoTicketSize() {
        return LOTTO_TICKET_SIZE;
    }

    public Ranking checkRanking(final LottoTicket winningTicket, LottoNumber bonusNumber) {
        int matching = (int) lottoNumbers.stream()
                .filter(number -> winningTicket.contains(number))
                .count();

        boolean bonusMatching = contains(bonusNumber);

        return Ranking.select(matching, bonusMatching);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }
}
