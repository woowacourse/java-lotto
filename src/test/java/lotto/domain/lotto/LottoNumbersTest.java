package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import lotto.domain.number.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoNumbersTest {

    @Test
    @DisplayName("로또 넘버에 중복이 있으면 예외")
    public void duplicateLottoNumber() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> LottoNumbers.valueOf("1,1,2,3,4,5"))
            .withMessage("로또 넘버에 중복이 있습니다.");
    }

    @Test
    @DisplayName("로또넘버가 6개가 아니면 예외(String)")
    public void valueOfInvalidCount() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> LottoNumbers.valueOf("1,2,3,4,5"))
            .withMessage("로또 넘버가 6개가 아닙니다.");
    }

    @ParameterizedTest
    @DisplayName("로또 넘버가 6개가 아니면 예외(Set)")
    @MethodSource("provideNewLottoNumberInvalidCountArgument")
    public void newLottoNumberInvalidCount(Set<LottoNumber> input) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new LottoNumbers(input))
            .withMessage("로또 넘버가 6개가 아닙니다.");
    }

    private static Stream<Set<LottoNumber>> provideNewLottoNumberInvalidCountArgument() {
        return Stream.of(
            new HashSet<>(Arrays.asList(LottoNumber.valueOf("1"),
                LottoNumber.valueOf("2"),
                LottoNumber.valueOf("3"),
                LottoNumber.valueOf("4"),
                LottoNumber.valueOf("5"))),
            new HashSet<>(Arrays.asList(LottoNumber.valueOf("1"),
                LottoNumber.valueOf("2"),
                LottoNumber.valueOf("3"),
                LottoNumber.valueOf("4"),
                LottoNumber.valueOf("5"),
                LottoNumber.valueOf("6"),
                LottoNumber.valueOf("7")))
        );
    }

    @Test
    @DisplayName("로또 넘버 정상 테스트(String)")
    void valueOf() {
        LottoNumbers lottoNumbers = LottoNumbers.valueOf("1,2,3,4,5,6");

        assertThat(lottoNumbers.unwrap()).isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("로또 넘버 정상 테스트(Map)")
    void newLottoNumbers() {
        Set<LottoNumber> lottoNumbers =
            new HashSet<>(Arrays.asList(LottoNumber.valueOf("1"),
                LottoNumber.valueOf("2"),
                LottoNumber.valueOf("3"),
                LottoNumber.valueOf("4"),
                LottoNumber.valueOf("5"),
                LottoNumber.valueOf("6")));

        assertThat(new LottoNumbers(lottoNumbers).unwrap())
            .isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("로또 번호 포함 확인")
    void containsTest() {
        LottoNumbers lottoNumbers = LottoNumbers.valueOf("1,2,3,4,5,6");

        assertThat(lottoNumbers.contains(LottoNumber.valueOf("6"))).isTrue();
        assertThat(lottoNumbers.contains(LottoNumber.valueOf("7"))).isFalse();
    }

    @Test
    @DisplayName("로또 번호 매칭 기능")
    void matchTest() {
        LottoNumbers lottoNumbers1 = LottoNumbers.valueOf("1,2,3,4,5,6");
        LottoNumbers lottoNumbers2 = LottoNumbers.valueOf("4,5,6,7,8,9");

        assertThat(lottoNumbers1.match(lottoNumbers2)).isEqualTo(3);
    }

    @Test
    @DisplayName("unwrap 시 제대로 정렬되는지 확인")
    void unwrapTest() {
        LottoNumbers lottoNumbers = LottoNumbers.valueOf("5,1,4,3,2,6");
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThat(lottoNumbers.unwrap()).isEqualTo(expected);
    }
}