package lotto.utils;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class StringUtilsTest {

	@Test
	void split() {
		List<Integer> list = StringUtils.split("1,2,3,4,5,6");
		assertThat(list).contains(1, 2, 3, 4, 5, 6);

	}
}
