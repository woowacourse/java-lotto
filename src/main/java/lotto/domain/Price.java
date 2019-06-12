package lotto.domain;

import lotto.domain.exception.InvalidLottoPriceException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Price {
    private static final String MONEY_PATTERN = "^[0-9]*$";
    private static final int MIN_PRICE = 0;
    static final int LOTTO_PRICE = 1000;

    private int money;

    public Price(String money) {
        this.money = checkValidatePrice(money);
    }

    private int checkValidatePrice(String money) {
        Matcher matcher = Pattern.compile(MONEY_PATTERN).matcher(money);
        if (!matcher.find()) {
            throw new InvalidLottoPriceException(money);
        }
        if (Integer.parseInt(money) % LOTTO_PRICE != MIN_PRICE) {
            throw new InvalidLottoPriceException(Integer.parseInt(money) % LOTTO_PRICE);
        }

        return Integer.parseInt(money);
    }

    public int getMoney() {
        return money;
    }

    public int getNumberOfLotto() {
        return money / LOTTO_PRICE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return money == price.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
