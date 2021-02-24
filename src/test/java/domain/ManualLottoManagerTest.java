package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ManualLottoManagerTest {

    @DisplayName("수동 로또 생성 실패 : 입력 금액보다 많은 수동 로또 개수 입력")
    @Test
    void generate_tooManyLotto_ExceptionThrown() {
        Money money = Money.valueOf(3000);
        List<String> lottoNumbers = Stream.<String>builder()
            .add("1, 2, 3, 4, 5, 6")
            .add("7, 8, 9, 10, 11, 12")
            .add("13, 14, 15, 16, 17, 18")
            .add("19, 20, 21, 22, 23, 24")
            .build()
            .collect(Collectors.toList());

        assertThatIllegalArgumentException()
            .isThrownBy(() -> ManualLottoManager.generate(money, lottoNumbers))
            .withMessageContaining("구매 가능한");
    }

    @DisplayName("수동 로또 생성 실패 : 중복 숫자가 들어가 있는 번호 입력")
    @Test
    void generate_duplicatedNumber_ExceptionThrown() {
        Money money = Money.valueOf(3000);
        List<String> lottoNumbers = Stream.<String>builder()
            .add("1, 2, 3, 4, 5, 4")
            .add("7, 8, 9, 10, 11, 12")
            .add("13, 14, 15, 16, 17, 18")
            .build()
            .collect(Collectors.toList());

        assertThatIllegalArgumentException()
            .isThrownBy(() -> ManualLottoManager.generate(money, lottoNumbers))
            .withMessageContaining("개수가 6이 아닙니다.");
    }
}