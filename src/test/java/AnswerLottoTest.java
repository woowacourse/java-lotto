import domain.AnswerLotto;
import domain.LottoNumber;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerLottoTest {

	@Test
	void duplicateInBonusNumber() {
		String[] sampleInput = {"1", "2", "3", "4", "5", "6"};
		LottoNumber sampleBonusNumber = new LottoNumber(1);
		assertThatThrownBy(() -> new AnswerLotto(sampleInput, sampleBonusNumber))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void lottoNumberInAnswerNumbers() {
		String[] sampleInput = {"1", "2", "3", "4", "5", "6"};
		LottoNumber sampleBonusNumber = new LottoNumber(7);
		LottoNumber lottoNumber = new LottoNumber(1);
		assertThat(new AnswerLotto(sampleInput, sampleBonusNumber).isInAnswerNumbers(lottoNumber)).isTrue();
	}

	@Test
	void lottoNumberEqualsBounsNumber() {
		String[] sampleInput = {"1", "2", "3", "4", "5", "6"};
		LottoNumber sampleBonusNumber = new LottoNumber(7);
		LottoNumber lottoNumber = new LottoNumber(7);
		assertThat(new AnswerLotto(sampleInput, sampleBonusNumber).isSameWithBonusNumber(lottoNumber)).isTrue();
	}
}
