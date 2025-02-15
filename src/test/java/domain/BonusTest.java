package domain;

import exception.LottoException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.BonusNumberRepository;
import repository.LottoRepository;
import repository.WinningNumberRepository;
import service.LottoService;

public class BonusTest {

  private final LottoRepository lottoRepository = new LottoRepository();
  private final WinningNumberRepository winningNumberRepository = new WinningNumberRepository();
  private final BonusNumberRepository bonusNumberRepository = new BonusNumberRepository();

  @DisplayName("보너스_번호가_1_45내의_숫자가_아니면_예외가_발생한다")
  @ParameterizedTest
  @ValueSource(strings = {"0", "46"})
  void 보너스_번호가_1_45내의_숫자가_아니면_예외가_발생한다(String numbers) {
    Assertions.assertThatThrownBy(() -> {
      new BonusNumber(numbers);
    }).isInstanceOf(LottoException.class);
  }

  @DisplayName("보너스_번호가_숫자가_아니면_예외가_발생한다")
  @ParameterizedTest
  @ValueSource(strings = {"a", ""})
  void 보너스_번호가_숫자가_아니면_예외가_발생한다(String numbers) {
    Assertions.assertThatThrownBy(() -> {
      new BonusNumber(numbers);
    }).isInstanceOf(LottoException.class);
  }

  @DisplayName("보너스_번호가_당첨_번호에_존재하는_경우_예외가_발생한다")
  @Test
  void 보너스_번호가_당첨_번호에_존재하는_경우_예외가_발생한다() {
    Assertions.assertThatThrownBy(() -> {
      WinningNumber winningNumber = new WinningNumber("1,2,3,4,5,6");
      LottoService lottoService = new LottoService(
          lottoRepository,
          winningNumberRepository,
          bonusNumberRepository
      );
      winningNumberRepository.saveWinningNumber(winningNumber);
      lottoService.inputBonusNumber("6");
    }).isInstanceOf(LottoException.class);
  }

}
