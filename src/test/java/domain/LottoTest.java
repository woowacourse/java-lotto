package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Lotto 테스트")
public class LottoTest {
    private Set<LottoNumber> lottoNumbers;

    @BeforeEach
    void setup() {
        lottoNumbers = new HashSet<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    @Test
    @DisplayName("생성자에 6개 LottoNumber 를 전달 받으면 Lotto 객체가 생성된다.")
    void createLotto() {
        // given
        Lotto lotto = new Lotto(lottoNumbers);

        // when & then
        assertThat(lotto).isNotNull();
    }

    @Test
    @DisplayName("Lotto 생성시 전달된 List 길이가 6이 아니면 IAE 발생한다.")
    void createLottoWithInvalidSizeOfLottoNumbersShouldFail() {
        // given
        lottoNumbers.add(new LottoNumber(8));

        // then
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Lotto.ERROR_MESSAGE_FOR_INVALID_SIZE_OF_LOTTO_NUMBERS);
    }

    @Test
    @DisplayName("Lotto 는 다른 Lotto 객체를 전달 받아 같은 숫자의 수를 반환할 수 있다.")
    void lottoReturnsNumberOfSameNumberCount() {
        // given
        Lotto lotto = new Lotto(lottoNumbers);

        // when
        Set<LottoNumber> newLottoNumbers = new HashSet<>(lottoNumbers);
        newLottoNumbers.remove(new LottoNumber(6));
        newLottoNumbers.add(new LottoNumber(7));
        Lotto anotherLotto = new Lotto(newLottoNumbers);

        // then
        assertThat(lotto.getSameNumberCount(anotherLotto)).isEqualTo(5);
    }

    @DisplayName("Lotto 에 LottoNumber 를 전달하면 해당 숫자의 포함 여부 확인할 수 있다.")
    @ParameterizedTest(name = "{0} 전달")
    @CsvSource(value = {"1,true", "6,true", "7,false"})
    void lottoContainsLottoNumberTest(int lottoNumber, boolean expected) {
        // given
        LottoNumber lottoNumber1 = new LottoNumber(lottoNumber);

        // when
        Lotto lotto = new Lotto(lottoNumbers);

        // then
        assertThat(lotto.containsLottoNumber(lottoNumber1)).isEqualTo(expected);
    }
}
