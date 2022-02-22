package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

public class LottoTest {
    private List<LottoNumber> lottoNumbers;

    @BeforeEach
    void setup() {
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    @ParameterizedTest(name = "6개 LottoNumber 를 전달 받아 Lotto 생성")
    void createLotto() {
        // given
        Lotto lotto = new Lotto(lottoNumbers);

        // when

        // then
        assertThat(Objects.isNull(lotto)).isFalse();
    }

    @Test
    @DisplayName("Lotto 생성시 전달된 List 길이가 6이 아니면 IAE 발생")
    void createLottoWithInvalidSizeOfLottoNumbersShouldFail() {
        // given
        lottoNumbers.add(new LottoNumber(8));

        // then
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("\\d개의 숫자를 골라주세요.");
    }

    @Test
    @DisplayName("Lotto 생성시 전달된 List에 중복이 있을 경우 IAE 발생")
    void createLottoWithDuplicateLottoNumbersShouldFail() {
        // given
        lottoNumbers.set(1, new LottoNumber(1));

        // then
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("숫자는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("Lotto는 다른 Lotto 객체를 전달 받아 같은 숫자의 수를 반환할 수 있다")
    void lottoReturnsNumberOfSameNumberCount() {
        // given
        Lotto lotto = new Lotto(lottoNumbers);

        // when
        List<LottoNumber> newLottoNumbers = new ArrayList<>(lottoNumbers);
        newLottoNumbers.set(0, new LottoNumber(7));
        Lotto anotherLotto = new Lotto(newLottoNumbers);

        // then
        assertThat(lotto.getSameNumberCount(anotherLotto)).isEqualTo(5);
    }
}
