package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lotto.exceptions.InvalidLottoTicketException;

public class LottoTicket {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final String DELIMITER = ", ";
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";

    private final List<LottoNumber> lottoTicket;

    private LottoTicket(final List<LottoNumber> lottoTicket) {
        validate(lottoTicket);
        this.lottoTicket = Collections.unmodifiableList(lottoTicket);
    }

    public LottoTicket(String[] numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (String number : numbers) {
            lottoNumbers.add(LottoNumber.find(number.trim()));
        }
        validate(lottoNumbers);
        this.lottoTicket = lottoNumbers;
    }

    private void validate(List<LottoNumber> lottoTicket) {
        validateLottoSize(lottoTicket);
        validateDuplicate(lottoTicket);
    }

    private void validateDuplicate(List<LottoNumber> lottoTicket) {
        long distinctCount = lottoTicket.stream().distinct().count();
        if (lottoTicket.size() != distinctCount) {
            throw new InvalidLottoTicketException("유효하지 않은 당첨 번호 값입니다.");
        }
    }

    private void validateLottoSize(List<LottoNumber> lottoTicket) {
        if (lottoTicket.size() != LOTTO_NUMBER_COUNT) {
            throw new InvalidLottoTicketException("유효하지 않은 당첨 번호 값입니다.");
        }
    }

    static LottoTicket create() {
        List<LottoNumber> numbers = Arrays.asList(LottoNumber.values());
        List<LottoNumber> randomNumbers = new ArrayList<>();
        Collections.shuffle(numbers);
        for (int i = 0; i < LOTTO_NUMBER_COUNT; i++) {
            randomNumbers.add(numbers.get(i));
        }
        return new LottoTicket(randomNumbers);
    }

    public void validateBonusNumber(LottoNumber number) {
        if (this.lottoTicket.contains(number)) {
            throw new InvalidLottoTicketException("보너스 숫자는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    int compare(LottoTicket other) {
        Set<LottoNumber> winnerSet = new HashSet<>(this.lottoTicket);
        Set<LottoNumber> otherSet = new HashSet<>(other.lottoTicket);
        winnerSet.retainAll(otherSet);
        return winnerSet.size();
    }

    boolean contains(LottoNumber lottoNumber) {
        return lottoTicket.contains(lottoNumber);
    }

    @Override
    public String toString() {
        return lottoTicket.stream()
            .map(LottoNumber::getValue)
            .sorted()
            .map(String::valueOf)
            .collect(Collectors.joining(DELIMITER, PREFIX, SUFFIX));
    }
}
