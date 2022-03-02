package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import exception.DuplicatedLottoNumbersException;
import exception.InvalidLottoNumbersSizeException;
import exception.InvalidRangeLottoNumberException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("중복된 로또 번호를 가질 수 없음")
    void checkDuplicatedLottoNumbers() {
        assertThatThrownBy(() -> Lotto.of(LottoNumber.convertAll(List.of(1, 2, 3, 3, 4, 5))))
                .isInstanceOf(DuplicatedLottoNumbersException.class);
    }

    @Test
    @DisplayName("로또 번호 리스트가 1 ~ 45 사이에 있는 경우 테스트")
    void checkValidLottoNumbersRangeTest() {
        assertThatCode(() -> Lotto.of(LottoNumber.convertAll(List.of(1, 2, 3, 4, 5, 45))))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또 번호 리스트가 1 ~ 45 사이에 있지 않은 경우 테스트")
    void checkInvalidLottoNumbersRangeTest() {
        assertThatThrownBy(() -> Lotto.of(LottoNumber.convertAll(List.of(1, 2, 3, 0, 5, 46))))
                .isInstanceOf(InvalidRangeLottoNumberException.class);
    }

    @Test
    @DisplayName("일치하는 숫자 갯수 구하기 테스트")
    void getMatchedNumberCountTest() {
        Lotto lotto = Lotto.of(LottoNumber.convertAll(List.of(1, 2, 3, 4, 5, 6)));
        Lotto winnerNumbers = Lotto.of(LottoNumber.convertAll(List.of(1, 10, 20, 30, 40, 6)));
        int count = lotto.getMatchedNumberCountWith(winnerNumbers);
        assertThat(count).isEqualTo(2);
    }

    @Test
    @DisplayName("로또 번호에 숫자 포함 여부 테스트")
    void contains() {
        Lotto lotto = Lotto.of(LottoNumber.convertAll(List.of(1, 2, 3, 4, 5, 6)));
        assertAll("lotto",
                () -> assertThat(lotto.contains(LottoNumber.of(1))).isTrue(),
                () -> assertThat(lotto.contains(LottoNumber.of(6))).isTrue(),
                () -> assertThat(lotto.contains(LottoNumber.of(9))).isFalse()
        );
    }

    @Test
    @DisplayName("로또 숫자가 6개가 아닌 경우 로또 생성")
    void invalidLottoNumbersSize() {
        assertThatThrownBy(() -> Lotto.of(LottoNumber.convertAll(List.of(1, 2, 3, 4, 5))))
                .isInstanceOf(InvalidLottoNumbersSizeException.class);
    }
}
