package lotto.domain;

public class LottoNumberFactory {
    public static LottoNumber generateLottoNumber(int number) {
        return new LottoNumber(number);
    }
}
