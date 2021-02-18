package domain.lotto;

import domain.ball.LottoBalls;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

public class LottoTest {

    @DisplayName("Lotto 정상 생성된다.")
    @Test
    public void Lotto_생성() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThatCode(() -> new Lotto(LottoBalls.generate(lottoNumbers))).doesNotThrowAnyException();
    }
}
