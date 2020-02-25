package domain.lottostore;

import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("랜덤 로또 가게 테스트")
public class RandomLottoStoreTest {

    @Test
    @DisplayName("티켓들 생성 확인")
    void generateRandomTicket() {
        RandomLottoStore randomLottoStore = new RandomLottoStore();
        Money money = new Money(10000);

        assertThat(randomLottoStore.generateTickets(money).size()).isEqualTo(10);
    }
}
