package util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static util.LottoCountUtils.getValidAutosCount;
import static util.LottoCountUtils.getValidTotalCount;

import org.junit.jupiter.api.Test;

class LottoCountUtilsTest {

    @Test
    void getValidTotalCount_returnsTotalCountOnPositive() {
        int actual = getValidTotalCount(5000);

        assertThat(actual).isEqualTo(5);
    }

    @Test
    void getValidTotalCount_returnsZeroOnZero() {
        int actual = getValidTotalCount(0);

        assertThat(actual).isEqualTo(0);
    }

    @Test
    void getValidTotalCount_exceptionIfChangesExist() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> getValidTotalCount(500));
    }

    @Test
    void getValidAutosCount_returnsAutoCount() {
        int actual = getValidAutosCount(0, 10);

        assertThat(actual).isEqualTo(10);
    }

    @Test
    void getValidAutosCount_returnsZeroIfTwoArgsAreSame() {
        int actual = getValidAutosCount(10, 10);

        assertThat(actual).isEqualTo(0);
    }

    @Test
    void getValidTotalCount_exceptionIfAutoCountBecomesNegative() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> getValidAutosCount(20, 10));
    }
}