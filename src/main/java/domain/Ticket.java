package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Ticket {
    public static final int LOTTO_SIZE = 6;
    private static final String REQUEST_NON_DUPLICATED_NUMBER = String.format("중복되지 않은 숫자 %d개를 입력해주세요.", LOTTO_SIZE);
    private static final String DELIMITER = ", ";
    private final Set<LottoNumber> lottoNumbers;

    public Ticket(LottoNumbersGenerator lottoNumbersGenerator) {
        this(lottoNumbersGenerator.generate());
    }

    public Ticket(Set<LottoNumber> lottoNumbers) {
        checkTicketSize(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void checkTicketSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(REQUEST_NON_DUPLICATED_NUMBER);
        }
    }

    public static Ticket from(String winNumbersInput) {
        checkEmpty(winNumbersInput);
        List<LottoNumber> winNumbers = toLottoNumber(toInteger(toList(winNumbersInput)));
        checkDuplicateNumbers(winNumbers);
        return new Ticket(new HashSet<>(winNumbers));
    }

    private static void checkEmpty(String winNumbers) {
        if (winNumbers == null || winNumbers.isBlank()) {
            throw new IllegalArgumentException("빈 문자를 입력할 수 없습니다.");
        }
    }

    private static List<LottoNumber> toLottoNumber(List<Integer> winNumbers) {
        return winNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private static List<Integer> toInteger(List<String> winNumbers) {
        try {
            return winNumbers.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(REQUEST_NON_DUPLICATED_NUMBER);
        }
    }

    private static List<String> toList(String winNumbersInput) {
        return List.of(winNumbersInput.split(DELIMITER, -1));
    }

    private static void checkDuplicateNumbers(List<LottoNumber> winNumbers) {
        if (winNumbers.size() != new HashSet<>(winNumbers).size()) {
            throw new IllegalArgumentException(REQUEST_NON_DUPLICATED_NUMBER);
        }
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public boolean contains(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    public int getSameNumberCount(Ticket ticket) {
        return (int) ticket.getLottoNumbers().stream()
                .filter(lottoNumbers::contains)
                .count();
    }
}
