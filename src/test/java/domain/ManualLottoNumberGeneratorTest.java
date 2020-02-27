package domain;

import Lotto.domain.LottoNumber;
import Lotto.utils.ManualLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ManualLottoNumberGeneratorTest {
    @Test
    @DisplayName("수동 로또 스트링으로 입력 시 로또넘버의 리스트로 잘 반환하는지 확인")
    void init() {
        String input = "1, 2, 3, 4, 6, 11";
        ManualLottoNumberGenerator manualGenerator = new ManualLottoNumberGenerator();
        List<LottoNumber> lottoNumbers = manualGenerator.generate(input);
        List<LottoNumber> results = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(6),
                new LottoNumber(11)
        );

        assertThat(lottoNumbers).isEqualTo(results);
    }

    @Test
    @DisplayName("수동 로또가 잘못된 범위의 번호로 입력될 때 exception이 잘 나는지 확인")
    void wrongInput() {
        String input = "1, 2, 3, 4, -6, 11";
        ManualLottoNumberGenerator manualGenerator = new ManualLottoNumberGenerator();

        assertThatThrownBy(() -> manualGenerator.generate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위");
    }
}
