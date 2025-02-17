package domain;

public class LottoMachine {

    public static final int LOTTO_PRICE = 1000;

    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoMachine(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public Lottos buyLottos(Money money) {
        validateMoney(money);
        int purchasedLottoCount = money.getAmount() / LOTTO_PRICE;

        return lottoNumberGenerator.generateLottos(purchasedLottoCount);
    }

    private void validateMoney(Money money) {
        validateZeroMoney(money);
        validateMultipleOfLottoPrice(money);
    }

    private static void validateMultipleOfLottoPrice(Money money) {
        if (money.getAmount() % LOTTO_PRICE == 0) {
            return;
        }
        throw new IllegalArgumentException("구입 금액은 로또 가격 단위(" + LOTTO_PRICE + ")의 배수여야 합니다.");
    }

    private void validateZeroMoney(Money money) {
        if (money.getAmount() < LOTTO_PRICE) {
            throw new IllegalArgumentException("로또는 최소" + LOTTO_PRICE + "원부터 구매할 수 있습니다");
        }
    }
}
