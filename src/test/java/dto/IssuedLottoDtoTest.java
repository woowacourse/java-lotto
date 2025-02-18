package dto;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IssuedLottoDtoTest {

    @DisplayName("equals 메서드 테스트 - 포함되었을 때")
    @Test
    void equalsTest1() {
        List<IssuedLottoDto> dtos = new ArrayList<>(
                List.of(new IssuedLottoDto(List.of(1, 2, 3, 4, 5, 6)),
                        new IssuedLottoDto(List.of(1, 2, 3, 4, 5, 7)),
                        new IssuedLottoDto(List.of(1, 2, 3, 4, 5, 8))));

        IssuedLottoDto test = new IssuedLottoDto(List.of(1, 2, 3, 4, 5, 6));

        Assertions.assertThat(dtos.contains(test)).isTrue();
    }

    @DisplayName("equals 메서드 테스트 - 포함되지 않았을 때")
    @Test
    void equalsTest2() {
        List<IssuedLottoDto> dtos = new ArrayList<>(
                List.of(new IssuedLottoDto(List.of(2, 1, 3, 4, 5, 9)),
                        new IssuedLottoDto(List.of(1, 2, 3, 4, 5, 7)),
                        new IssuedLottoDto(List.of(1, 2, 3, 4, 5, 8))));

        IssuedLottoDto test = new IssuedLottoDto(List.of(1, 2, 3, 4, 5, 6));

        Assertions.assertThat(dtos.contains(test)).isFalse();
    }
}