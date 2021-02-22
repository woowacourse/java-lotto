package lottogame.domain.lotto;

public class LottoNumberGenerator {
    private final int lottoMin;
    private final int lottoMax;

    public LottoNumberGenerator(int lottoMin, int lottoMax) {
        this.lottoMin = lottoMin;
        this.lottoMax = lottoMax;
    }

    public Integer generate() {
        return (int) (Math.random() * this.lottoMax) + lottoMin;
    }
}
