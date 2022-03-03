package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {
	static LottoTickets lottoTickets;
	static ManualGenerator manualGenerator;

	@BeforeEach
	void setUp() {
		lottoTickets = new LottoTickets(new LottoCounter(1000, 1));
		manualGenerator = new ManualGenerator(List.of(1, 2, 3, 4, 5, 6));
	}

	@Test
	void addOneLotto() {
		lottoTickets.add(new Lotto(manualGenerator.generateLottoNumbers()));
		assertThat(lottoTickets.getLottoTickets()).contains(
			new Lotto(new ManualGenerator(List.of(1, 2, 3, 4, 5, 6)).generateLottoNumbers()));
	}

	@Test
	void calculateNothingZero() {
		lottoTickets.add(new Lotto(manualGenerator.generateLottoNumbers()));
		List<WinningStatus> result = lottoTickets.calculateAllLottoResult(
			new AnswerLotto(new ManualGenerator(List.of(10, 20, 30, 40, 15, 17)).generateLottoNumbers(), new LottoNumber(45)));
		assertThat(result).contains(WinningStatus.NOTHING);
	}

	@Test
	void calculateNothingOne() {
		lottoTickets.add(new Lotto(manualGenerator.generateLottoNumbers()));
		List<WinningStatus> result = lottoTickets.calculateAllLottoResult(
			new AnswerLotto(new ManualGenerator(List.of(1, 20, 30, 40, 15, 17)).generateLottoNumbers(), new LottoNumber(45)));
		assertThat(result).contains(WinningStatus.NOTHING);
	}

	@Test
	void calculateNothingTwo() {
		lottoTickets.add(new Lotto(manualGenerator.generateLottoNumbers()));
		List<WinningStatus> result = lottoTickets.calculateAllLottoResult(
			new AnswerLotto(new ManualGenerator(List.of(1, 2, 30, 40, 15, 17)).generateLottoNumbers(), new LottoNumber(45)));
		assertThat(result).contains(WinningStatus.NOTHING);
	}

	@Test
	void calculateThree() {
		lottoTickets.add(new Lotto(manualGenerator.generateLottoNumbers()));
		List<WinningStatus> result = lottoTickets.calculateAllLottoResult(
			new AnswerLotto(new ManualGenerator(List.of(1, 2, 3, 40, 15, 17)).generateLottoNumbers(), new LottoNumber(45)));
		assertThat(result).contains(WinningStatus.THREE);
	}

	@Test
	void calculateFour() {
		lottoTickets.add(new Lotto(manualGenerator.generateLottoNumbers()));
		List<WinningStatus> result = lottoTickets.calculateAllLottoResult(
			new AnswerLotto(new ManualGenerator(List.of(1, 2, 3, 4, 15, 17)).generateLottoNumbers(), new LottoNumber(45)));
		assertThat(result).contains(WinningStatus.FOUR);
	}

	@Test
	void calculateFive() {
		lottoTickets.add(new Lotto(manualGenerator.generateLottoNumbers()));
		List<WinningStatus> result = lottoTickets.calculateAllLottoResult(
			new AnswerLotto(new ManualGenerator(List.of(1, 2, 3, 4, 5, 17)).generateLottoNumbers(), new LottoNumber(45)));
		assertThat(result).contains(WinningStatus.FIVE);
	}

	@Test
	void calculateFiveAndBonus() {
		lottoTickets.add(new Lotto(manualGenerator.generateLottoNumbers()));
		List<WinningStatus> result = lottoTickets.calculateAllLottoResult(
			new AnswerLotto(new ManualGenerator(List.of(1, 2, 3, 4, 5, 17)).generateLottoNumbers(), new LottoNumber(6)));
		assertThat(result).contains(WinningStatus.FIVE_AND_BONUS);
	}

	@Test
	void calculateSix() {
		lottoTickets.add(new Lotto(manualGenerator.generateLottoNumbers()));
		List<WinningStatus> result = lottoTickets.calculateAllLottoResult(
			new AnswerLotto(new ManualGenerator(List.of(1, 2, 3, 4, 5, 6)).generateLottoNumbers(), new LottoNumber(7)));
		assertThat(result).contains(WinningStatus.SIX);
	}
}
