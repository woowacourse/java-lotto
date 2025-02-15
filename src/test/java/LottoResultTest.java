import domain.LottoRanking;
import domain.LottoResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @Test
    @DisplayName("초기화 된 객체의 맵의 키 개수는 로또 순위 개수 만큼 받아 올 수 있다")
    void 초기화_된_객체의_맵의_키_개수는_로또_순위_개수_만큼_받아_올_수_있다() {
        LottoResult lottoResult = LottoResult.initialize();
        assertThat(lottoResult.result().size()).isEqualTo(LottoRanking.values().length);
    }

    @Test
    @DisplayName("초기화 된 객체의 맵의 모든 값은 0이다")
    void 초기화_된_객체의_맵의_모든_값은_0이다() {
        LottoResult lottoResult = LottoResult.initialize();
        boolean isAllZero = lottoResult.result().values().stream().filter((value) -> value != 0).toList().isEmpty();
        assertThat(isAllZero).isTrue();
    }

    @Test
    @DisplayName("결과에 로또 순위 개수를 추가할 수 있다")
    void 결과에_로또_순위_개수를_추가할_수_있다() {
        LottoResult lottoResult = LottoResult.initialize();
        lottoResult.add(LottoRanking.FIRST);
        assertThat(lottoResult.result().get(LottoRanking.FIRST)).isEqualTo(1);
    }

    @Test
    @DisplayName("총 상금을 계산할 수 있다")
    void 총_상금을_계산할_수_있다() {
        LottoResult lottoResult = LottoResult.initialize();
        lottoResult.add(LottoRanking.FIRST);
        lottoResult.add(LottoRanking.SECOND);
        lottoResult.add(LottoRanking.THIRD);

        assertThat(lottoResult.getTotalPrize())
                .isEqualTo(LottoRanking.FIRST.getPrize()
                        + LottoRanking.SECOND.getPrize()
                        + LottoRanking.THIRD.getPrize());
    }
}