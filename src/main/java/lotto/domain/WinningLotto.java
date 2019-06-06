package lotto.domain;

import lotto.exception.UnmatchedLottoTicketAmountException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinningLotto {
    private static WinningLotto WINNING_LOTTO = null;

    private final List<LottoNumber> lottoNumbers = new ArrayList<>();
    private final LottoNumber bonusBall;

    private WinningLotto(final String winningLotto, final int bonusBall) {
        List<String> inputNumbers = Arrays.asList(winningLotto.split(","));

        for (String inputNumber : inputNumbers) {
            validateNumeric(inputNumber);
            validateDistinctNumber(Integer.parseInt(inputNumber));
            lottoNumbers.add(LottoNumber.getNumber(Integer.parseInt(inputNumber)));
        }
        validateDistinctNumber(bonusBall);
        this.bonusBall = LottoNumber.getNumber(bonusBall);
    }

    public static WinningLotto of(final String winningLotto, final int bonusBall) {
        if (WINNING_LOTTO == null) {
            WINNING_LOTTO = new WinningLotto(winningLotto, bonusBall);
        }

        return WINNING_LOTTO;
    }

    public boolean hasEqualNumber(LottoNumber number) {
        return lottoNumbers.contains(number);// || lottoNumbers.contains(bonusBall);
    }

    public boolean hasEqualBonusBall(LottoNumber number) {
        return bonusBall == number;
    }

    private static void validateNumeric(String number) {
        if (!number.matches("(\\d+)?")) {
            throw new ArithmeticException("로또 번호가 유효하지 않습니다.");
        }
    }

    private void validateDistinctNumber(int lottoNumber) {
        boolean isDistinct = lottoNumbers.contains(LottoNumber.getNumber(lottoNumber));

        if (isDistinct) {
            throw new UnmatchedLottoTicketAmountException("중복된 번호는 입력할 수 없습니다.");
        }
    }

    static void makeObjectNull() {
        WINNING_LOTTO = null;
    }
}
