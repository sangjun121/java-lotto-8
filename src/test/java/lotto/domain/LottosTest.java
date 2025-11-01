package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottosTest {
    private WinningNumber validWinningNumber;
    private BonusNumber validBonusNumber;

    @BeforeEach
    void setUp() {
        validWinningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6));
        validBonusNumber = BonusNumber.of(7, validWinningNumber);
    }

    @Test
    void 매칭_결과에_따라_올바른_Rank_개수가_반환된다() {
        Lotto first = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto second = Lotto.from(List.of(1, 2, 3, 4, 5, 7));
        Lotto third = Lotto.from(List.of(1, 2, 3, 4, 5, 8));
        Lotto fourth = Lotto.from(List.of(1, 2, 3, 4, 7, 8));
        Lotto fifth = Lotto.from(List.of(1, 2, 3, 7, 8, 9));
        Lotto none = Lotto.from(List.of(1, 2, 7, 8, 9, 10));

        Lottos lottos = new Lottos(List.of(first, second, third, fourth, fifth, none));

        Map<Rank, Integer> result = lottos.countByRank(validWinningNumber, validBonusNumber);

        assertThat(result)
                .containsEntry(Rank.FIRST, 1)
                .containsEntry(Rank.SECOND, 1)
                .containsEntry(Rank.THIRD, 1)
                .containsEntry(Rank.FOURTH, 1)
                .containsEntry(Rank.FIFTH, 1)
                .doesNotContainKey(Rank.NONE);
    }

    @Test
    void 동일한_Rank는_개수가_누적된다() {
        Lotto first = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lottos lottos = new Lottos(List.of(first, first, first, first, first));

        Map<Rank, Integer> result = lottos.countByRank(validWinningNumber, validBonusNumber);

        assertThat(result).containsEntry(Rank.FIRST, 5);
    }
}
