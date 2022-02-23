package view;

import dto.LottoDto;
import dto.LottosDto;
import dto.RankDto;

import java.util.List;

public class OutputView {

    private static final int SECOND_RANK = 2;
    private static final String RESULT_MESSAGE = "당첨 통계";
    private static final String DELIMITER = "---------";
    private static final String RESULT_FORMAT = "%d개 일치%s (%d원)- %d개";
    private static final String BONUS_FORMAT = ", 보너스 볼 일치";
    private static final String EMPTY_FORMAT = "";

    public static void printPurchasedLotto(LottosDto lottosDto) {
        System.out.println(String.format("%s개를 구매했습니다.", lottosDto.getQuantity()));
        for (LottoDto lottoDto : lottosDto.getLottoDtos()) {
            printLottoNumbers(lottoDto);
        }
    }

    public static void printLottoNumbers(LottoDto lottoDto) {
        System.out.println(lottoDto.getLottoNumber());
    }

    public static void printRank(List<RankDto> rankDtos) {
        System.out.println(RESULT_MESSAGE);
        System.out.println(DELIMITER);
        for (RankDto rankDto : rankDtos) {
            int criteria = rankDto.getCriteria();
            int reward = rankDto.getReward();
            int hitCount = rankDto.getHitCount();

            if (rankDto.getRankNumber() == SECOND_RANK) {
                System.out.println(String.format(RESULT_FORMAT, criteria, BONUS_FORMAT, reward, hitCount));
                continue;
            }
            System.out.println(String.format(RESULT_FORMAT, criteria, EMPTY_FORMAT, reward, hitCount));
        }
    }
}
