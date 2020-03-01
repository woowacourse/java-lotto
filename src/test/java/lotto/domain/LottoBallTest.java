package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class LottoBallTest {

    @ParameterizedTest
    @DisplayName("로또볼 범위를 벗어날때 생기는 오류 테스트")
    @ValueSource(strings ={"0","46"})
    void throw_lotto_ball_test(String lottoBall) {
        Assertions.assertThatThrownBy(()->new LottoBall(lottoBall)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("로또볼 생성 테스트")
    @ValueSource(strings ={"1","45"})
    void lotto_ball_test(String lottoBall) {
        Assertions.assertThatCode(()->new LottoBall(lottoBall)).doesNotThrowAnyException();
    }
}