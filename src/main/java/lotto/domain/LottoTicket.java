package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lotto.exceptions.InvalidLottoTicketException;

public class LottoTicket {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private final List<LottoNumber> lottoTicket;

    public LottoTicket(final List<LottoNumber> lottoTicket) {
        validate(lottoTicket);
        this.lottoTicket = Collections.unmodifiableList(lottoTicket);
    }

    private void validate(List<LottoNumber> lottoTicket) {
        if (lottoTicket.size() != LOTTO_NUMBER_COUNT) {
            throw new InvalidLottoTicketException();
        }
        if (lottoTicket.size() != lottoTicket.stream().distinct().count()) {
            throw new InvalidLottoTicketException();
        }

    }

    static LottoTicket create() {
        List<LottoNumber> numbers = Arrays.asList(LottoNumber.values());
        List<LottoNumber> randomNumbers = new ArrayList<>();
        Collections.shuffle(numbers);
        for (int i = 0; i< LOTTO_NUMBER_COUNT; i++) {
            randomNumbers.add(numbers.get(i));
        }
        return new LottoTicket(randomNumbers);
    }

    @Override
    public String toString() {
        return lottoTicket.stream()
            .map(LottoNumber::getValue)
            .map(String::valueOf)
            .collect(Collectors.joining(", ", "[", "]"));
    }
}