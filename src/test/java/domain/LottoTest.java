package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoTest {
    private List<LottoNumber> lottoNumbers;

    @BeforeEach
    void setup() {
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(LottoNumber.getInstance(i));
        }
    }

    @Test
    @DisplayName("6개 LottoNumber 를 전달 받아 Lotto 생성")
    void createLotto() {
        // given
        Lotto lotto = new Lotto(lottoNumbers);

        // when & then
        assertThat(lotto).isNotNull();
    }

    @Test
    @DisplayName("로또 생성자에 Null 전달 시 NPE 발생")
    void createLottoWithNullShouldFail() {
        assertThatThrownBy(() -> new Lotto(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("로또 번호가 비었습니다");
    }

    @Test
    @DisplayName("Lotto 생성시 전달된 List 길이가 6이 아니면 IAE 발생")
    void createLottoWithInvalidSizeOfLottoNumbersShouldFail() {
        // given
        lottoNumbers.add(LottoNumber.getInstance(8));

        // then
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("\\d개의 숫자를 골라주세요.");
    }

    @Test
    @DisplayName("Lotto 생성시 전달된 List에 중복이 있을 경우 IAE 발생")
    void createLottoWithDuplicateLottoNumbersShouldFail() {
        // given
        lottoNumbers.set(1, LottoNumber.getInstance(1));

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
        newLottoNumbers.set(0, LottoNumber.getInstance(7));
        Lotto anotherLotto = new Lotto(newLottoNumbers);

        // then
        assertThat(lotto.getSameNumberCount(anotherLotto)).isEqualTo(5);
    }

    @ParameterizedTest(name = "포함 여부를 확인할 숫자 : {0}")
    @CsvSource(value = {"1,true", "6,true", "7,false"})
    @DisplayName("Lotto에 LottoNumber를 전달하여 포함 여부 확인")
    void lottoContainsLottoNumberTest(int lottoNumber, boolean expected) {
        // given
        LottoNumber lottoNumber1 = LottoNumber.getInstance(lottoNumber);

        // when
        Lotto lotto = new Lotto(lottoNumbers);

        // then
        assertThat(lotto.containsLottoNumber(lottoNumber1)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Lotto 에서 getter로 가져온 컬렉션 수정 시 UOE 발생")
    void modifyingCollectionFromGetterOfLottoShouldFail() {
        // given
        Lotto lotto = new Lotto(lottoNumbers);

        // when
        List<LottoNumber> lottoNumbers = lotto.getLottoNumbers();

        // then
        assertThatThrownBy(() -> lottoNumbers.add(LottoNumber.getInstance(22)))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
