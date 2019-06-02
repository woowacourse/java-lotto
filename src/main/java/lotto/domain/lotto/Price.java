package lotto.domain.lotto;

import lotto.domain.InvalidLottoPrice;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Price {
    private static final String MONEY_PATTERN = "^[0-9]*$";
    private static final int LOTTO_PRICE = 1000;
    private int money;

    public Price(String money){
        this.money = checkValidatePrice(money);
    }

    private int checkValidatePrice(String money){
        Matcher matcher = Pattern.compile(MONEY_PATTERN).matcher(money);
        if(!matcher.find()){
            throw new InvalidLottoPrice("제대로 된 금액이 아닙니다.");
        }
        if(Integer.parseInt(money) % 1000 != 0){
            throw new InvalidLottoPrice("1000원 단위로 입력해주세요.");
        }
        if(Integer.parseInt(money) < 0){
            throw new InvalidLottoPrice("음수가 나오면 안됩니다.");
        }
        return Integer.parseInt(money);
    }

    public int getNumberOfLotto(){
        return money/LOTTO_PRICE;
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
