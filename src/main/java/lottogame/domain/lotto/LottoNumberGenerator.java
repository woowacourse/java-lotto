package lottogame.domain.lotto;

public class LottoNumberGenerator {
    private LottoNumberGenerator() {
    }

    static Integer generate(int lottoMin, int lottoMax) {
        return (int) (Math.random() * lottoMax) + lottoMin;
    }
}
