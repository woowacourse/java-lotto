package lotto.util;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class StringUtilsTest {
	@Test
	void splitByComma() {
		List<String> actual = StringUtils.splitByComma("1,2,3,4,5,6");
		List<String> expected = Arrays.asList("1", "2", "3", "4", "5", "6");
		assertThat(actual).isEqualTo(expected);
	}
}
