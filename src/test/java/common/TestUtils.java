package common;

import domain.Lotto;
import dto.LottoCountsDto;
import domain.LottoNumber;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestUtils {

    private TestUtils() {
    }

    public static Lotto createNewLotto(int... value) {
        return new Lotto(createLottoNumbersOf(value));
    }

    public static List<LottoNumber> createLottoNumbersOf(int... nums) {
        return Arrays.stream(nums).boxed()
                .map(LottoNumber::of)
                .sorted()
                .collect(Collectors.toList());
    }

    public static LottoCountsDto createCountsDto(int manuals, int randoms) {
        return new LottoCountsDto(manuals, randoms);
    }
}
