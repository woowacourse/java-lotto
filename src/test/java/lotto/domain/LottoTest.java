package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    private ArrayList<String> nums1;
    private ArrayList<String> nums2;
    private ArrayList<String> nums3;

    @BeforeEach
    void setUp() {
        nums1 = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
        nums2 = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "5"));
        nums3 = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "50"));
    }

    @Test
    @DisplayName("숫자가 6개인지 확인")
    void validateSixNumbers() {
        assertThatThrownBy(() -> new Lotto(nums1)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Lotto.NUMBER_COUNT_ERROR);
    }

    @Test
    @DisplayName("숫자가 중복인지 확인")
    void validateOverlappedNumber() {
        assertThatThrownBy(() -> new Lotto(nums2)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Lotto.NUMBER_DUPLICATE_ERROR);
    }

    @Test
    @DisplayName("숫자가 범위에 있는지 확인")
    void validateNumberRange() {
        assertThatThrownBy(() -> new Lotto(nums3)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(LottoNumber.NUMBER_RANGE_ERROR);
    }

    @Test
    @DisplayName("입력받은 숫자들로 로또가 잘 생성되는지 확인")
    void from() {
        Lotto expected = new Lotto(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6")));
        String sampleWithBlank = "1, 2, 3, 4, 5, 6";
        String sampleWithoutBlank = "1,2,3,4,5,6";
        assertThat(Lotto.from(sampleWithBlank)).isEqualTo(expected);
        assertThat(Lotto.from(sampleWithoutBlank)).isEqualTo(expected);
    }
}