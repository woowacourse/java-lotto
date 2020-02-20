package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LottoTicketTest {
	@Test
	void LottoTicketToStringTest() {
		List<LottoNumber> lottoNumbers = new ArrayList<>();
		lottoNumbers.add(new LottoNumber(1));
		lottoNumbers.add(new LottoNumber(2));
		lottoNumbers.add(new LottoNumber(13));
		lottoNumbers.add(new LottoNumber(14));
		lottoNumbers.add(new LottoNumber(21));
		lottoNumbers.add(new LottoNumber(34));

		LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
		String expected = "[1, 2, 13, 14, 21, 34]";
		String actual = lottoTicket.toString();

		assertThat(actual).isEqualTo(expected);
	}

}
