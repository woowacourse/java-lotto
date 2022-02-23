package study;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class StringTest {

	@Test
	void trim_결과_성공() {
		String name = "찬 ";

		name = name.trim();

		assertThat(name).isEqualTo("찬");
	}
}
