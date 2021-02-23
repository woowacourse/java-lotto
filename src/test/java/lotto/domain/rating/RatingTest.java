package lotto.domain.rating;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RatingTest {

    @ParameterizedTest
    @DisplayName("로또 등수에 따른 보너스볼 제외 맞춘 번호의 개수 테스트")
    @CsvSource(value = {"1,2,3,4,5,6:6", "1,2,3,4,5,7:5", "1,2,3,4,5,8:5", "1,2,3,4,8,9:4",
        "1,2,3,7,8,9:3", "10,11,12,13,14,15:0"}, delimiter = ':')
    void getRatingByNumberAndBonusBall(String input, int expected) {
        Integer[] arr = Arrays.stream(input.split(","))
            .mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
        Lotto inputLotto = Lotto.from(Arrays.asList(arr));

        Lotto lotto = Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);

        Rating rating = Rating
            .getRating(inputLotto.countCommonValue(lotto), inputLotto.containNumber(bonusNumber));
        assertThat(rating.getMatchCount()).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("로또 등수에 따른 상금 테스트")
    @CsvSource(value = {"1,2,3,4,5,6:2000000000", "1,2,3,4,5,7:30000000", "1,2,3,4,5,8:1500000",
        "1,2,3,4,8,9:50000",
        "1,2,3,7,8,9:5000", "10,11,12,13,14,15:0"}, delimiter = ':')
    void getRatingRewardCheck(String input, int expected) {
        Integer[] arr = Arrays.stream(input.split(","))
            .mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
        Lotto inputLotto = Lotto.from(Arrays.asList(arr));

        Lotto lotto = Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);

        Rating rating = Rating
            .getRating(inputLotto.countCommonValue(lotto), inputLotto.containNumber(bonusNumber));
        assertThat(rating.getReward()).isEqualTo(expected);
    }
}
