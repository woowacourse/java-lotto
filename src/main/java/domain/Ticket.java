package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Ticket {
    public static final int LOTTO_SIZE = 6;
    private static final String REQUEST_NON_DUPLICATED_NUMBER = String.format("중복되지 않은 숫자 %d개를 입력해주세요.", LOTTO_SIZE);

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

    public static Ticket from(List<Integer> numbersInput) {
        List<LottoNumber> numbers = toLottoNumber(numbersInput);
        checkDuplicateNumbers(numbers);
        return new Ticket(new TreeSet<>(numbers));
    }

    private static List<LottoNumber> toLottoNumber(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
    }

    private static void checkDuplicateNumbers(List<LottoNumber> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException(REQUEST_NON_DUPLICATED_NUMBER);
        }
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public int getSameNumberCount(Ticket ticket) {
        return (int) ticket.getLottoNumbers().stream()
                .filter(lottoNumbers::contains)
                .count();
    }
}
