import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoStore {

    private static final int LOTTO_PRICE = 1_000;

    public List<LottoTicket> buy(final int purchaseAmount) {
        validateAmountUnit(purchaseAmount);
        int purchaseCount = purchaseAmount / LOTTO_PRICE;
        return IntStream.range(0, purchaseCount)
                .mapToObj(i -> new LottoTicket())
                .collect(Collectors.toList());
    }

    private void validateAmountUnit(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
    }
}
