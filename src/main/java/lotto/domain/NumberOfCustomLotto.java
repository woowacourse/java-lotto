package lotto.domain;

import lotto.domain.exception.InvalidNumberOfCustomLottoException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberOfCustomLotto {
    private static final String CUSTOM_LOTTO_REGEX = "^[0-9]*$";
    private static final int LEAST_NUMBER = 0;

    private int number;

    public NumberOfCustomLotto(String number, Price price) {
        this.number = invalidCustomLottoNumber(number, price.getNumberOfLotto());
    }

    private int invalidCustomLottoNumber(String number, int numberOfLotto) {
        Matcher matcher = Pattern.compile(CUSTOM_LOTTO_REGEX).matcher(number);
        if (!matcher.find()) {
            throw new InvalidNumberOfCustomLottoException(number);
        }
        if (Integer.parseInt(number) < LEAST_NUMBER) {
            throw new InvalidNumberOfCustomLottoException(Integer.parseInt(number));
        }
        if (Integer.parseInt(number) > numberOfLotto){
            throw new InvalidNumberOfCustomLottoException(Integer.parseInt(number), numberOfLotto);
        }
        return Integer.parseInt(number);
    }

    public int getNumberOfCustomLotto() {
        return number;
    }
}
