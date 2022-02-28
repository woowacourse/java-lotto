package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValidationUtilsTest {

    @Test
    @DisplayName("null 입력 예외 테스트")
    void validateNullCollectionTest() {
        assertThatThrownBy(() -> ValidationUtils.validateEmptyCollection(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("빈 컬렉션");
    }

    @Test
    @DisplayName("빈 입력 예외 테스트")
    void validateEmptyCollectionTest() {
        assertThatThrownBy(() -> ValidationUtils.validateEmptyCollection(new ArrayList<>()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("빈 컬렉션");
    }
}
