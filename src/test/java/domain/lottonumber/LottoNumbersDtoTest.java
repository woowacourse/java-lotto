package domain.lottonumber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.LottoNumbersDtoGenerator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("로또번호 Dto 테스트")
public class LottoNumbersDtoTest {
    @Test
    @DisplayName("Dto 생성시 당첨 번호와 보너스 번호 중복 확인")
    void ticketConstructor() {
        Set<Integer> sixNumbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThatThrownBy(() -> LottoNumbersDtoGenerator.generateManualNumbersDto(sixNumbers, 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호와 중복되지 않는 보너스 번호를 입력해주세요.");
    }
}
