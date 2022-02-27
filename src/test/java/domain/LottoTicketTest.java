package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    private static List<LottoNumber> createNewLottoNumbers(int... value) {
        return Arrays.stream(value)
                .boxed()
                .map(LottoNumber::of)
                .collect(Collectors.toList());
    }

    @DisplayName("자동 생성된 로또의 크기가 6인 경우 성공")
    @Test
    void createAutoLotto_ShouldContainSixElements_Successful() {
        LottoTicket lottoTicket = LottoTicket.createAutoLotto();

        assertThat(lottoTicket.getNumbers().size()).isEqualTo(6);
    }

    @DisplayName("자동 생성된 로또가 오름차순으로 정렬되어 있을 경우 성공")
    @Test
    void createAutoLotto_ShouldBeSortedAsc_Successful() {
        LottoTicket lottoTicket = LottoTicket.createAutoLotto();

        assertThat(lottoTicket.getNumbers()).isSorted();
    }

    @DisplayName("6개의 숫자로 로또 수동 생성 시 성공")
    @Test
    void createManualLotto_CreatedWithSixElements_Successful() {
        LottoTicket lottoTicket = LottoTicket.createManualLotto(createNewLottoNumbers(1, 2, 3, 4, 5, 6));

        assertThat(lottoTicket.getNumbers().size()).isEqualTo(6);
    }

    @DisplayName("6개가 아닌 숫자들로 로또 수동 생성 시 실패")
    @Test
    void createManualLotto_CreatedWithMoreThanSixElements_Fail() {
        List<LottoNumber> lottoNumbers = createNewLottoNumbers(1, 2, 3, 4, 5, 6, 7);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> LottoTicket.createManualLotto(lottoNumbers))
                .withMessageMatching("6개의 당첨 번호를 입력해야 합니다.");
    }

    @DisplayName("중복없는 숫자들로 로또 수동 생성 시 성공")
    @Test
    void createManualLotto_CreatedWithDistinctElements_Successful() {
        List<LottoNumber> lottoNumbers = createNewLottoNumbers(1, 2, 3, 4, 5, 6);

        assertThatNoException()
                .isThrownBy(() -> LottoTicket.createManualLotto(lottoNumbers));
    }

    @DisplayName("중복되는 숫자들로 로또 수동 생성 시 실패")
    @Test
    void createManualLotto_CreatedWithDuplicateElements_Fail() {
        List<LottoNumber> lottoNumbers = createNewLottoNumbers(1, 1, 2, 3, 4, 5);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> LottoTicket.createManualLotto(lottoNumbers))
                .withMessageMatching("당첨 번호는 중복될 수 없습니다.");
    }

    @DisplayName("로또 수동 생성 시 오름차순으로 정렬되어 있을 경우 성공")
    @Test
    void createManualLotto_ShouldBeSortedAsc_Successful() {
        LottoTicket lottoTicket = LottoTicket.createManualLotto(createNewLottoNumbers(6, 5, 4, 3, 2, 1));

        assertThat(lottoTicket.getNumbers()).isSorted();
    }
}
