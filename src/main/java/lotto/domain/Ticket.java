package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import lotto.utils.LottoNumbersGenerator;

public class Ticket {
    private static final String REQUEST_NON_DUPLICATED_NUMBER = "중복되지 않은 숫자 6개를 입력해주세요.";
    private static final String REQUEST_RIGHT_SIZE_MESSAGE = "숫자 6자리를 입력해주세요. ex) 1, 2, 3, 4, 5, 6";
    private static final int TICKET_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;
    private final boolean isAuto;

    private Ticket(Set<LottoNumber> lottoNumbers, boolean isAuto) {
        this.lottoNumbers = lottoNumbers;
        this.isAuto = isAuto;
    }

    public static Ticket createByImplementation(LottoNumbersGenerator lottoNumbersGenerator, boolean isAuto) {
        Set<LottoNumber> lottoNumbers = lottoNumbersGenerator.generate(TICKET_SIZE);
        return new Ticket(lottoNumbers, isAuto);
    }

    public static Ticket createByIntegers(List<Integer> numbers, boolean isAuto) {
        checkTicketSize(numbers.size());
        checkDuplicate(numbers);
        Set<LottoNumber> lottoNumbers = convertToLottoNumbers(numbers);
        return new Ticket(lottoNumbers, isAuto);
    }

    private static void checkTicketSize(int size) {
        if (size != TICKET_SIZE) {
            throw new IllegalArgumentException(REQUEST_RIGHT_SIZE_MESSAGE);
        }
    }

    private static Set<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        Set<LottoNumber> lottoNumbers = new TreeSet<>();
        for (Integer number : numbers) {
            addLottoNumbers(lottoNumbers, number);
        }
        return lottoNumbers;
    }

    private static void addLottoNumbers(Set<LottoNumber> lottoNumbers, Integer number) {
        try {
            lottoNumbers.add(new LottoNumber(number));
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(REQUEST_NON_DUPLICATED_NUMBER);
        }
    }

    private static void checkDuplicate(List<Integer> rawNumbers) {
        Set<Integer> numbers = new HashSet<>(rawNumbers);
        if (rawNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(REQUEST_NON_DUPLICATED_NUMBER);
        }
    }

    public boolean isAuto() {
        return isAuto;
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public int getSameNumberCount(Ticket ticket) {
        return (int)ticket.getLottoNumbers().stream()
            .filter(lottoNumbers::contains)
            .count();
    }
}
