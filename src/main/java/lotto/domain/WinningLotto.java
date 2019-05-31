package lotto.domain;

import lotto.exception.UnmatchedLottoTicketAmountException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto {
    public static WinningLotto WINNING_LOTTO = null;

    private WinningLotto(String winningLotto) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        List<String> inputNumbers = Arrays.asList(winningLotto.split(","));

        for (String inputNumber : inputNumbers) {
            validateNumeric(inputNumber);
            lottoNumbers.add(LottoNumber.getNumber(Integer.parseInt(inputNumber)));
        }
        validateDistinctNumber(lottoNumbers);
    }

    public static WinningLotto getInstance(String manualLotto) {
        if (WINNING_LOTTO == null) {
            WINNING_LOTTO = new WinningLotto(manualLotto);
        }

        return WINNING_LOTTO;
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
