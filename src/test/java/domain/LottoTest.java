package domain;

import static org.junit.jupiter.api.Assertions.*;

import domain.numbergenerator.RandomNumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @DisplayName("로또 객체 생성 테스트")
    @Test
    void 로또_객체_생성_테스트() {
        // given
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

        // when
        Lotto lotto = Lotto.from(randomNumberGenerator);

        // then
        Assertions.assertThat(lotto).isInstanceOf(Lotto.class);
    }
}