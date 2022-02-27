package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lotto.utils.LottoNumbersGenerator;

public class Ticket {
    private static final String REQUEST_NON_EMPTY_INPUT = "빈 문자를 입력할 수 없습니다.";
    private static final String REQUEST_NON_DUPLICATED_NUMBER = "중복되지 않은 숫자 6개를 입력해주세요.";
    private static final String DELIMITER = ", ";
    private static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    public Ticket(LottoNumbersGenerator lottoNumbersGenerator) {
        this.lottoNumbers = lottoNumbersGenerator.generate();
    }

    public Ticket(Set<LottoNumber> lottoNumbers) {
        checkTicketSize(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static Ticket of(String winNumbersInput) {
        checkEmpty(winNumbersInput);
        List<String> splitWinNumbers = List.of(winNumbersInput.split(DELIMITER, -1));
        checkDelimiter(splitWinNumbers.size());
        Set<LottoNumber> winNumbers = new HashSet<>();
        for (String winNumber : splitWinNumbers) {
            addWinLottoNumbers(winNumbers, winNumber);
        }
        checkDuplicate(splitWinNumbers.size(), winNumbers.size());
        return new Ticket(winNumbers);
    }

    private void checkTicketSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(REQUEST_NON_DUPLICATED_NUMBER);
        }
    }

    private static void checkEmpty(String winNumbers) {
        if (winNumbers == null || winNumbers.isBlank()) {
            throw new IllegalArgumentException(REQUEST_NON_EMPTY_INPUT);
        }
    }

    private static void checkDelimiter(int size) {
        if (size != LOTTO_SIZE) {
            throw new IllegalArgumentException(",(콤마)와 공백으로 구분된 숫자 6자리를 입력해주세요. ex) 1, 2, 3, 4, 5, 6");
        }
    }

    private static void addWinLottoNumbers(Set<LottoNumber> winLottoNumbers, String win) {
        try {
            winLottoNumbers.add(new LottoNumber(Integer.parseInt(win)));
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(REQUEST_NON_DUPLICATED_NUMBER);
        }
    }

    private static void checkDuplicate(int originalSize, int newSize) {
        if (originalSize != newSize) {
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
        return (int)ticket.getLottoNumbers().stream()
            .filter(lottoNumbers::contains)
            .count();
    }
}
