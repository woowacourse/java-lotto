import enums.LottoRanking;
import domain.LottoResult;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoResultTest {

    @Test
    void 초기화_된_객체의_맵의_키_개수는_로또_순위_개수_만큼_받아_올_수_있다() {
        //when & then
        LottoResult lottoResult = LottoResult.initialize();
        assertThat(lottoResult.result().size()).isEqualTo(LottoRanking.values().length);
    }

    @Test
    void 초기화_된_객체의_맵의_모든_값은_0이다() {
        //when & then
        LottoResult lottoResult = LottoResult.initialize();
        boolean isAllZero = lottoResult.result().values().stream().filter((value) -> value != 0).toList().isEmpty();
        assertThat(isAllZero).isTrue();
    }

    @Test
    void 결과에_로또_순위_개수를_추가할_수_있다() {
        //when & then
        LottoResult lottoResult = LottoResult.initialize();
        lottoResult.add(LottoRanking.FIRST);
        assertThat(lottoResult.result().get(LottoRanking.FIRST)).isEqualTo(1);
    }

    @Test
    void 총_상금을_계산할_수_있다() {
        //given & when
        LottoResult lottoResult = LottoResult.initialize();
        lottoResult.add(LottoRanking.FIRST);
        lottoResult.add(LottoRanking.SECOND);
        lottoResult.add(LottoRanking.THIRD);
        //then
        assertThat(lottoResult.getTotalPrize())
                .isEqualTo(LottoRanking.FIRST.getPrize()
                        + LottoRanking.SECOND.getPrize()
                        + LottoRanking.THIRD.getPrize());
    }
}
