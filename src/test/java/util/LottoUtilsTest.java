package util;

import static domain.Lotto.LOTTO_NUMBERS_SIZE;
import static domain.LottoNumber.allLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;
import static util.LottoUtils.generateAutos;
import static util.LottoUtils.getValidManuals;

import domain.Lotto;
import domain.LottoNumber;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class LottoUtilsTest {

    private static final String firstPrize = "1, 2, 3, 4, 5, 6";
    private static final String secondPrize = "1, 2, 3, 4, 5, 7";
    private static final String fifthPrize = "1, 2, 3, 14, 15, 16";

    @Test
    void getValidManuals_returnLottosWithRaws() {
        List<String> manualsRaw = List.of(firstPrize, secondPrize, fifthPrize);
        List<Lotto> actual = getValidManuals(manualsRaw);

        assertThat(actual).hasOnlyElementsOfType(Lotto.class);
        assertThat(actual).hasSize(3);
    }

    @Test
    void getValidManuals_returnsEmptyLottoListOnEmpty() {
        List<Lotto> actual = getValidManuals(List.of());

        assertThat(actual).isEmpty();
    }

    @Test
    void getValidManuals_eachLottoIsAlwaysDifferent() {
        List<String> manualsRaw = List.of(firstPrize, firstPrize, firstPrize);
        List<Lotto> actual = getValidManuals(manualsRaw);

        Lotto lottoWithSameNumbers = new Lotto(getOneToSix());

        for (Lotto lotto : actual) {
            assertThat(lotto).doesNotHaveSameHashCodeAs(lottoWithSameNumbers);
        }
    }

    @Test
    void generateAutos_returnsLottosCreatedWithStrategy() {
        List<Lotto> actual = generateAutos(5, LottoUtilsTest::getOneToSix);

        assertThat(actual).hasOnlyElementsOfType(Lotto.class);
        assertThat(actual).hasSize(5);
    }

    @Test
    void generateAutos_eachLottoIsAlwaysDifferent() {
        List<Lotto> actual = generateAutos(5, LottoUtilsTest::getOneToSix);

        Lotto lottoWithSameNumbers = new Lotto(getOneToSix());

        for (Lotto lotto : actual) {
            assertThat(lotto.getChosenNumbers())
                    .isEqualTo(lottoWithSameNumbers.getChosenNumbers());
            assertThat(lotto).doesNotHaveSameHashCodeAs(lottoWithSameNumbers);
        }
    }

    @Test
    void generateAutos_returnsEmptyLottoListOnCountZero() {
        List<Lotto> actual = generateAutos(0, LottoUtilsTest::getOneToSix);

        assertThat(actual).isEmpty();
    }

    public static List<LottoNumber> getOneToSix() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(allLottoNumbers);
        return lottoNumbers.subList(0, LOTTO_NUMBERS_SIZE);
    }
}
