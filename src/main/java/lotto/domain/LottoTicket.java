package lotto.domain;

import lotto.exception.UnmatchedLottoTicketAmountException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoTicket {
    private final List<LottoNumber> lottoNumbers = new ArrayList<>();

    public LottoTicket(String manualLotto) {
        List<String> inputNumbers = Arrays.asList(manualLotto.split(","));

        for (String inputNumber : inputNumbers) {
            validateNumeric(inputNumber);

            int number = Integer.parseInt(inputNumber);

            lottoNumbers.add(LottoNumber.getNumber(number));

            validateDistinctNumber();
        }
    }

    private void validateNumeric(String number) {
        if (!number.matches("(\\d+)?")) {
            throw new ArithmeticException("로또 번호가 유효하지 않습니다.");
        }
    }

    private void validateDistinctNumber() {
        boolean isDistinct = lottoNumbers.stream().distinct().collect(Collectors.toList()).size() != lottoNumbers.size();
        if (isDistinct) {
            throw new UnmatchedLottoTicketAmountException("중복된 번호는 입력할 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
