package lotto.domain;

import lotto.exception.UnmatchedLottoTicketAmountException;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTicketFactory {

    public static LottoTicket create(String manualLotto) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        List<String> inputNumbers = Arrays.asList(manualLotto.split(","));

        // TODO 이부분 WinningLotto와 겹치는데 하나로 묶어버리기
        for (String inputNumber : inputNumbers) {
            validateNumeric(inputNumber);
            lottoNumbers.add(LottoNumber.getNumber(Integer.parseInt(inputNumber)));
        }
        validateDistinctNumber(lottoNumbers);

        return new LottoTicket(lottoNumbers);
    }

    private static void validateNumeric(String number) {
        if (!number.matches("(\\d+)?")) {
            throw new ArithmeticException("로또 번호가 유효하지 않습니다.");
        }
    }

    private static void validateDistinctNumber(List<LottoNumber> lottoNumbers) {
        boolean isDistinct = lottoNumbers.stream().distinct().collect(Collectors.toList()).size() != lottoNumbers.size();
        if (isDistinct) {
            throw new UnmatchedLottoTicketAmountException("중복된 번호는 입력할 수 없습니다.");
        }
    }
}
