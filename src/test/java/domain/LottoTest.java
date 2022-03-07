package domain;

import exception.ExceptionMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {

    private Set<LottoNumber> lottoNumbers = new HashSet<>();

    public static Set<LottoNumber> createLottoNumbers(int... number) {
        return Arrays.stream(number)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toSet());
    }

    @BeforeEach
    void setUp() {
        lottoNumbers = createLottoNumbers(1, 2, 3, 4, 5);
    }

    @DisplayName("로또 생성시 숫자가 6개가 입력되면 성공")
    @Test
    void lotto_size_success() {
        lottoNumbers.add(LottoNumber.valueOf(6));
        assertThatCode(() -> new Lotto(lottoNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 생성시 숫자가 6개가 입력되지 않으면 실패")
    @Test
    void lotto_size_fail() {
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_LOTTO_SIZE.getMessage());
    }

    @DisplayName("로또 생성시 번호가 중복될 경우 실패")
    @Test
    void duplicate_fail() {
        lottoNumbers.add(LottoNumber.valueOf(5));
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_LOTTO_SIZE.getMessage());
    }

    @DisplayName("다른 로또와 비교해 매칭되는 번호 개수 계산")
    @Test
    void calculate_match_count() {
        //given
        lottoNumbers.add(LottoNumber.valueOf(6));
        Lotto lotto = new Lotto(lottoNumbers);
        Lotto targetLotto = new Lotto(createLottoNumbers(7, 5, 4, 3, 2, 1));

        //when
        int count = lotto.calculateMatchCount(targetLotto);

        //then
        assertThat(count).isEqualTo(5);
    }

    @DisplayName("로또 번호 중 동일한 번호를 포함하는지 확인")
    @Test
    void is_contain() {
        lottoNumbers.add(LottoNumber.valueOf(6));
        Lotto lotto = new Lotto(lottoNumbers);

        assertThat(lotto.isContain(LottoNumber.valueOf(6))).isTrue();
    }
}
