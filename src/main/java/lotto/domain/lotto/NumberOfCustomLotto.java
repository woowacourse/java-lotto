package lotto.domain.lotto;

import lotto.domain.InvalidNumberOfCustomLotto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberOfCustomLotto {
    private static final String CUSTOM_LOTTO_REGEX = "^[0-9]*$";
    private static final int LEAST_NUMBER = 0;

    private int number;

    public NumberOfCustomLotto(String number, int numberOfLotto) {
        this.number = invalidCustomLottoNumber(number, numberOfLotto);
    }

    private int invalidCustomLottoNumber(String number, int numberOfLotto) {
        Matcher matcher = Pattern.compile(CUSTOM_LOTTO_REGEX).matcher(number);
        if (!matcher.find()) {
            throw new InvalidNumberOfCustomLotto("잘못된 입력입니다.");
        }
        if (Integer.parseInt(number) < LEAST_NUMBER) {
            throw new InvalidNumberOfCustomLotto("0이상의 수만 입력하세요.");
        }
        if (Integer.parseInt(number) > numberOfLotto){
            throw new InvalidNumberOfCustomLotto("총 개수보다 넘을 수는 없습니다.");
        }
        return Integer.parseInt(number);
    }

    public int getNumberOfCustomLotto() {
        return number;
    }
}
