package lottoGame.testData;

import domain.Number;
import domain.*;
import dto.GameResult;
import dto.RankAnalysisDTO;
import dto.RankResult;

import java.util.Arrays;
import java.util.List;

public class TestData {
    public static List<GameResult> gameResults = Arrays.asList(
            GameResult.of(
                    1,
                    LottoGroup.of(
                            Arrays.asList(
                                    LottoFactory.createRandomLotto(),
                                    LottoFactory.createRandomLotto()),
                            Arrays.asList(
                                    LottoFactory.createRandomLotto()
                            )),
                    WinningLotto.of(LottoFactory.createLottoFromNumbers(
                            Arrays.asList(
                                    Number.from(1),
                                    Number.from(2),
                                    Number.from(3),
                                    Number.from(4),
                                    Number.from(5),
                                    Number.from(6)
                            )
                    ), Number.from(45)),
                    RankAnalysisDTO.of(
                            Arrays.asList(RankResult.of(Rank.FIRST, 4),
                                    RankResult.of(Rank.SECOND, 4),
                                    RankResult.of(Rank.THIRD, 4)),
                            10000,
                            10.0
                    )
            )
    );

    public static RankAnalysisDTO rankAalysisDTO = RankAnalysisDTO.of(
            Arrays.asList(RankResult.of(Rank.FIRST, 4),
                    RankResult.of(Rank.SECOND, 4),
                    RankResult.of(Rank.THIRD, 4)),
            10000,
            10.0
    );
}
