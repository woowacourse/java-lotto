package domain.ball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoBallsTest {

    @DisplayName("LottoBalls 정상 생성 테스트.")
    @Test
    void lottoBallsGenerateTest() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        //when
        List<LottoBall> lottoBalls = lottoNumbers.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());

        //then
        assertThatCode(() -> new LottoBalls(lottoBalls))
                .doesNotThrowAnyException();
    }

    @DisplayName("LottoBalls 인스턴스의 사이즈가 6이 아니면 에러가 발생한다.")
    @Test
    void lottoBallsSizeErrorTest() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        //when
        List<LottoBall> lottoBalls = lottoNumbers.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());

        assertThatThrownBy(() -> new LottoBalls(lottoBalls))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("LottoBalls에 중복된 LottoBall이 있다면 에러가 발생한다.")
    @Test
    void lottoBallsDuplicateErrorTest() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 6, 6);

        //when
        List<LottoBall> lottoBalls = lottoNumbers.stream()
                .map(lottoNumber -> new LottoBall(lottoNumber))
                .collect(Collectors.toList());

        //then
        assertThatThrownBy(() -> new LottoBalls(lottoBalls))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
