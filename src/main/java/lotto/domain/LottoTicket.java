package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lotto.domain.LottoRule.LottoBalls;
import lotto.domain.LottoRule.LottoNumber;
import lotto.exceptions.InvalidLottoTicketException;

public class LottoTicket {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final String DELIMITER = ", ";
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";

    private final List<LottoNumber> lottoTicket;

    private LottoTicket(List<LottoNumber> lottoTicket) {
        validate(lottoTicket);
        this.lottoTicket = lottoTicket;
    }

    public LottoTicket(String[] numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (String number : numbers) {
            lottoNumbers.add(LottoBalls.find(number.trim()));
        }
        this.lottoTicket = new LottoTicket(lottoNumbers).lottoTicket;
    }

    static LottoTicket create() {
        List<LottoNumber> numbers = LottoBalls.getLottoBalls();
        List<LottoNumber> randomNumbers = new ArrayList<>();
        Collections.shuffle(numbers);
        for (int i = 0; i < LOTTO_NUMBER_COUNT; i++) {
            randomNumbers.add(numbers.get(i));
        }
        return new LottoTicket(randomNumbers);
    }

    public static List<LottoTicket> createManualLottoTickets(List<String[]> manualNumbers) {
        List<LottoTicket> manualTickets = new ArrayList<>();
        for (String[] manualNumber : manualNumbers) {
            manualTickets.add(new LottoTicket(manualNumber));
        }
        return manualTickets;
    }

    private void validate(List<LottoNumber> lottoTicket) {
        validateLottoSize(lottoTicket);
        validateDuplicate(lottoTicket);
    }

    private void validateDuplicate(List<LottoNumber> lottoTicket) {
        if (lottoTicket.size() != lottoTicket.stream().distinct().count()) {
            throw new InvalidLottoTicketException("당첨 번호는 중복될 수 없습니다.");
        }
    }

    private void validateLottoSize(List<LottoNumber> lottoTicket) {
        if (lottoTicket.size() != LOTTO_NUMBER_COUNT) {
            throw new InvalidLottoTicketException("당첨 번호는 여섯 개의 숫자로 이루어져 있어야 합니다.");
        }
    }

    int compare(LottoTicket other) {
        Set<LottoNumber> winnerSet = new HashSet<>(this.lottoTicket);
        Set<LottoNumber> otherSet = new HashSet<>(other.lottoTicket);
        winnerSet.retainAll(otherSet);
        return winnerSet.size();
    }

    boolean contains(LottoNumber lottoNumber) {
        String lottoNumberValue = String.valueOf(lottoNumber.getValue());
        return lottoTicket.contains(LottoBalls.find(lottoNumberValue));
    }

    @Override
    public String toString() {
        return lottoTicket.stream()
            .sorted()
            .map(String::valueOf)
            .collect(Collectors.joining(DELIMITER, PREFIX, SUFFIX));
    }
}