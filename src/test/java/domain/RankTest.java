package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {
    @ParameterizedTest(name = "맞춘 갯수:{0}, 보너스:{1}, 예상등수:{2}")
    @CsvSource(value = {
            "1,false,NO_MATCH", "1,true,NO_MATCH",
            "2,false,NO_MATCH", "2,true,NO_MATCH",
            "3,false,FIFTH", "3,true,FIFTH",
            "4,false,FOURTH", "4,true,FOURTH",
            "5,false,THIRD", "5,true,SECOND",
            "6,false,FIRST"})
    @DisplayName("맞춘 갯수, 보너스 여부에 따른 등수 반환 결과 확인")
    void rankByLottoNumberAndHasBonusNumber(int sameNumberCount, boolean hasBonusNumber, Rank expected) {
        assertThat(Rank.of(sameNumberCount, hasBonusNumber)).isEqualTo(expected);
    }
}
