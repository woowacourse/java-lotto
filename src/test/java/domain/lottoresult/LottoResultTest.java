package domain.lottoresult;

import domain.lottoresult.LottoRank;
import domain.lottoresult.LottoResult;
import domain.lottoresult.ResultCount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
    LottoResult lottoResult;
    @BeforeEach
    void init(){
        lottoResult = new LottoResult();
    }

    @Test
    void 생성_테스트(){
        Assertions.assertThat(lottoResult)
                .hasFieldOrProperty("result")
                .extracting("result")
                .isNotNull();
    }

    @Test
    void 결과입력_메서드_호출_테스트(){
        lottoResult.add(LottoRank.FIRST);
        lottoResult.add(LottoRank.SECOND);
        lottoResult.add(LottoRank.SECOND);
        ResultCount countOne = new ResultCount();
        ResultCount countTwo = new ResultCount();
        ResultCount countZero = new ResultCount();
        countOne.increase();
        countTwo.increase();
        countTwo.increase();
        Assertions.assertThat(lottoResult.get(LottoRank.FIRST)).isEqualTo(countOne);
        Assertions.assertThat(lottoResult.get(LottoRank.SECOND)).isEqualTo(countTwo);
        Assertions.assertThat(lottoResult.get(LottoRank.THIRD)).isEqualTo(countZero);
    }

    @Test
    void null입력_방어_테스트() {
        Assertions.assertThatThrownBy(() -> lottoResult.add(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null값이 입력되었습니다.");
        Assertions.assertThatThrownBy(() -> lottoResult.get(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null값이 입력되었습니다.");
    }
}
