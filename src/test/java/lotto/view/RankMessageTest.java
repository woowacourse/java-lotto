package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RankMessageTest {
	@DisplayName("당첨 통계에 필요한 랭크 메세지들을 알맞게 가져오는지")
	@Test
	void getRankMessages() {
		List<String> rankMessages = RankMessage.getRankMessages();

		List<String> expectedMessages = new ArrayList<>();
		expectedMessages.add("3개 일치 (5000원)-");
		expectedMessages.add("4개 일치 (50000원)-");
		expectedMessages.add("5개 일치 (1500000원)-");
		expectedMessages.add("5개 일치, 보너스 볼 일치(30000000원)-");
		expectedMessages.add("6개 일치 (2000000000원)-");

		assertThat(rankMessages).isEqualTo(expectedMessages);
	}
}