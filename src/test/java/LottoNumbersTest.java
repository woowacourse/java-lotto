import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.LottoNumber;
import domain.LottoNumbers;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersTest {

    @Test
    @DisplayName("로또 번호 갯수가 6개가 아닐 시 오류 발생 ")
    void lotto_numbers_size_fail() {

        List<LottoNumber> numbers = Stream.of(1, 2, 3, 4, 5).map(LottoNumber::new)
            .collect(Collectors.toList());

        assertThatThrownBy(() -> new LottoNumbers(numbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 번호는 6개만 입력 가능합니다.");

    }


    @Test
    @DisplayName("로또 번호는 중복될 수 없다.")
    void lotto_numbers_unique() {

        List<LottoNumber> numbers = Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new)
            .collect(Collectors.toList());

        LottoNumbers lottoNumbers = new LottoNumbers(numbers);

        assertThat(lottoNumbers.getLottoNumbers().size()).isEqualTo(numbers.size());
    }

    @Test
    @DisplayName("로또 번호가 중복될 시 예외 발생")
    void lotto_numbers_unique_fail() {

        List<LottoNumber> numbers = Stream.of(1, 2, 3, 4, 5, 5).map(LottoNumber::new)
            .collect(Collectors.toList());

        assertThatThrownBy(() -> new LottoNumbers(numbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 번호는 중복될 수 없습니다.");

    }

    @Test
    @DisplayName("로또 번호에 null값이 올 시 예외 발생")
    void lotto_numbers_null_fail() {

        assertThatThrownBy(() -> new LottoNumbers(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 번호에는 NULL 값이 올 수 없습니다.");

    }

    @Test
    @DisplayName("로또 번호에 빈 값이 올 시 예외 발생")
    void lotto_numbers_empty_fail() {

        assertThatThrownBy(() -> new LottoNumbers(new ArrayList<>()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 로또 번호에는 빈 값이 올 수 없습니다.");

    }

}
