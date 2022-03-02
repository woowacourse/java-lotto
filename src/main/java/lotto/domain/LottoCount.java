package lotto.domain;

public class LottoCount {

    private final int totalCount;

    public LottoCount(final int lottoCount) {
        if (lottoCount < 0) {
            throw new IllegalArgumentException("로또 구매 개수는 음수가 될 수 없습니다");
        }
        totalCount = lottoCount;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
