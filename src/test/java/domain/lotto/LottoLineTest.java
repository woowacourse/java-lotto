package domain.lotto;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import java.util.List;
import lotto.domain.Rank;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoLineTest {

    @Test
    @DisplayName("LottoNumbers 에는 중복된 LottoNubmer를 가지면 안된다")
    public void lottoNumbers_do_not_have_duplicated_lotto_number() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> {
                LottoLine lottoLine = new LottoLine(Arrays
                    .asList(new LottoNumber(1), new LottoNumber(1), new LottoNumber(2),
                        new LottoNumber(2), new LottoNumber(5), new LottoNumber(6)));
            });
    }

    @Test
    @DisplayName("로또 번호가 6개 미만일떄")
    public void lotto_number_count_under_6(){
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> {
                new LottoLine(Arrays.asList(new LottoNumber(1), new LottoNumber(2),
                    new LottoNumber(3), new LottoNumber(4), new LottoNumber(5)));
            });
    }

    @Test
    @DisplayName("로또 번호가 6개 초과일")
    public void lotto_number_count_over_7(){
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
            () -> {
                new LottoLine(Arrays.asList(new LottoNumber(1), new LottoNumber(2),
                    new LottoNumber(3), new LottoNumber(4), new LottoNumber(5),
                    new LottoNumber(6), new LottoNumber(7)));
            }
        );
    }

    @Test
    @DisplayName("로또 번호 매칭 테스트")
    void lotto_number_match_test() {
        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
            new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));

        List<LottoNumber> answerLottoNumbers = Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
            new LottoNumber(12), new LottoNumber(13), new LottoNumber(14));

        LottoLine lottoLine = new LottoLine(lottoNumbers);
        assertThat(lottoLine.matchLottoNumbers(lottoNumbers, new LottoNumber(3), answerLottoNumbers)).isEqualTo(Rank.FIFTH);
    }
}
