package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicket {

    public static final int LOTTO_TICKET_SIZE = 6;
    private final static int MIN_LOTTO_NUMBER = 1;
    private final static int MAX_LOTTO_NUMBER = 45;

    private static final String DUPLICATE_NUMBER_ERROR = "중복 숫자가 존재합니다.";
    private static final String INCORRECT_LOTTO_NUMBER_SIZE_ERROR = "로또 숫자의 개수가 6이 아닙니다.";

    private final List<LottoNumber> lottoNumbers;

    private LottoTicket(final List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public static LottoTicket generateRandom() {
        return new LottoTicket(createRandomIntegerList());
    }

    public static LottoTicket generateManual(final List<Integer> lottoNumbers) {
        List<LottoNumber> numbers = lottoNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new LottoTicket(numbers);
    }

    private static List<LottoNumber> createRandomIntegerList() {
        return createShuffledIntegerList().stream()
                .limit(LottoTicket.LOTTO_TICKET_SIZE)
                .map(LottoNumber::new)
                .sorted()
                .collect(Collectors.toList());
    }

    private static List<Integer> createShuffledIntegerList() {
        final List<Integer> numbers = IntStream
                .rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numbers);
        return numbers;
    }

    private void validate(final List<LottoNumber> lottoNumbers) {
        validateDuplicateNumbers(lottoNumbers);
        validateIncorrectSize(lottoNumbers);
    }

    private void validateDuplicateNumbers(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != new HashSet<>(lottoNumbers).size()) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER_ERROR);
        }
    }

    private void validateIncorrectSize(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException(INCORRECT_LOTTO_NUMBER_SIZE_ERROR);
        }
    }

    public Ranking checkRanking(final LottoTicket winningTicket, LottoNumber bonusNumber) {
        int matching = (int) lottoNumbers.stream()
                .filter(winningTicket::contains)
                .count();

        boolean bonusMatching = contains(bonusNumber);

        return Ranking.select(matching, bonusMatching);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<LottoNumber> toList() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
