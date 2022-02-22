package domain;

/*import static org.assertj.core.api.Assertions.*;*/
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BallTest {

	@DisplayName("볼은 1~45 범위의 숫자여야 한다.")
	@ParameterizedTest
	@ValueSource(ints = {46, 47, 48})
	void rangeOutExceptionTest(int number) {
		assertThatThrownBy(() -> new Ball(number))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("생성자 기능 테스트")
	@ParameterizedTest
	@ValueSource(ints = {43, 44, 45})
	void initTest(int number) {
		assertDoesNotThrow(() -> new Ball(number));
	}

}
