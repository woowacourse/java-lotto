package lottogame.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {
    @Test
    void 로또_생성_테스트() {
        System.out.println(new Lotto());
        assertThat(new Lotto().size()).isEqualTo(6);
    }
}
