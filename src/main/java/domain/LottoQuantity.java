package domain;

import java.util.Objects;

public class LottoQuantity {
    static final String ERROR_MESSAGE_FOR_INVALID_LOTTO_QUANTITY = "개수는 음수가 될 수 없습니다.";
    static final String ERROR_MESSAGE_FOR_INVALID_MANUAL_LOTTO_QUANTITY = "입력 금액을 초과하는 수동 로또 수를 입력할 수 없습니다.";

    private static final int QUANTITY_CRITERIA = 0;
    private final int lottoQuantity;

    private LottoQuantity(int lottoQuantity) {
        this.lottoQuantity = lottoQuantity;
    }

    public static LottoQuantity from(int lottoQuantity) {
        validateZeroOrPositive(lottoQuantity);
        return new LottoQuantity(lottoQuantity);
    }


    public static LottoQuantity from(InputMoney inputMoney) {
        int lottoQuantity = inputMoney.getMoney() / Lotto.SINGLE_LOTTO_PRICE;
        return new LottoQuantity(lottoQuantity);
    }

    public static LottoQuantity of(int lottoQuantity, InputMoney inputMoney) {
        validateNotOverInputMoney(lottoQuantity, inputMoney);
        return new LottoQuantity(lottoQuantity);
    }

    private static void validateZeroOrPositive(int lottoQuantity) {
        if (lottoQuantity < QUANTITY_CRITERIA) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_INVALID_LOTTO_QUANTITY);
        }
    }

    private static void validateNotOverInputMoney(int lottoQuantity, InputMoney inputMoney) {
        if (lottoQuantity * Lotto.SINGLE_LOTTO_PRICE > inputMoney.getMoney()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_INVALID_MANUAL_LOTTO_QUANTITY);
        }
    }

    public LottoQuantity subtract(LottoQuantity otherLottoQuantity) {
        return new LottoQuantity(this.lottoQuantity - otherLottoQuantity.lottoQuantity);
    }

    public int getLottoQuantity() {
        return lottoQuantity;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        LottoQuantity that = (LottoQuantity) object;
        return lottoQuantity == that.lottoQuantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoQuantity);
    }

    @Override
    public String toString() {
        return "LottoQuantity{" + "lottoQuantity=" + lottoQuantity + '}';
    }
}
