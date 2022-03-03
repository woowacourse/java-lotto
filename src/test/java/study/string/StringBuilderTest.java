package study.string;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StringBuilderTest {
	@Test
	public void append () {
		assertThat(createMessage("야호")).isEqualTo("안녕하세요, 저는 야호입니다.");
	}

	private String createMessage(String name) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("안녕하세요, 저는 ").append(name).append("입니다.");

		return stringBuilder.toString();
	}
}
