package lotto.domain;

public class CustomLottoCountFactory {
    public static CustomLottoCount createCustomLottoCount(int customLottoCount, Money money) {
        return new CustomLottoCount(customLottoCount, money);
    }
}
