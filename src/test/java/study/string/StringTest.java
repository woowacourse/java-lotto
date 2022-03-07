package study.string;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StringTest {

	@Test
	public void 문자열_길이_구하기() {
		String name = "야호";

		assertThat(name.length()).isEqualTo(2);
	}

	@Test
	public void 문자열_더하기() {
		String name = "야호";
		String welcome = "안녕!";

		assertThat(welcome.concat(name)).isEqualTo("안녕!야호");
	}

	@Test
	public void 문자열을_단위로_출력() {
		String name = "야호";

		for (int i = 0; i < name.length(); i++) {
			System.out.println(name.charAt(i));
		}
	}

	@Test
	public void 문자열_뒤집기() {
		String name = "야호";

		String reverseName = "";
		for (int i = name.length() - 1; i >= 0; i--) {
			reverseName = reverseName.concat(String.valueOf(name.charAt(i)));
		}

		assertThat(reverseName).isEqualTo("호야");
	}
}
