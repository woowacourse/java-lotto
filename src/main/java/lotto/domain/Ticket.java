package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import lotto.utils.LottoNumbersGenerator;

public class Ticket {
    private static final String REQUEST_NON_DUPLICATED_NUMBER = "중복되지 않은 숫자 6개를 입력해주세요.";
    private static final String REQUEST_DELIMITER_MESSAGE = ",(콤마)와 공백으로 구분된 숫자 6자리를 입력해주세요. ex) 1, 2, 3, 4, 5, 6";
    private static final int TICKET_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;
    private final boolean isAuto;

    public Ticket(Set<LottoNumber> lottoNumbers, boolean isAuto) {
        checkTicketSize(lottoNumbers.size());
        this.lottoNumbers = lottoNumbers;
        this.isAuto = isAuto;
    }

    public static Ticket createByAuto(LottoNumbersGenerator lottoNumbersGenerator) {
        Set<LottoNumber> lottoNumbers = lottoNumbersGenerator.generate(TICKET_SIZE);
        return new Ticket(lottoNumbers, true);
    }

    public static Ticket createByManual(List<Integer> numbers) {
        checkDelimiter(numbers.size());
        Set<LottoNumber> lottoNumbers = createWinNumbers(numbers);
        checkDuplicate(numbers.size(), lottoNumbers.size());
        return new Ticket(lottoNumbers, false);
    }

    private static void checkDelimiter(int size) {
        if (size != TICKET_SIZE) {
            throw new IllegalArgumentException(REQUEST_DELIMITER_MESSAGE);
        }
    }

    private static Set<LottoNumber> createWinNumbers(List<Integer> numbers) {
        Set<LottoNumber> winNumbers = new TreeSet<>();
        for (Integer number : numbers) {
            addWinLottoNumbers(winNumbers, number);
        }
        return winNumbers;
    }

    private static void addWinLottoNumbers(Set<LottoNumber> winLottoNumbers, Integer number) {
        try {
            winLottoNumbers.add(new LottoNumber(number));
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(REQUEST_NON_DUPLICATED_NUMBER);
        }
    }

    private static void checkDuplicate(int originalSize, int newSize) {
        if (originalSize != newSize) {
            throw new IllegalArgumentException(REQUEST_NON_DUPLICATED_NUMBER);
        }
    }

    private void checkTicketSize(int size) {
        if (size != TICKET_SIZE) {
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
