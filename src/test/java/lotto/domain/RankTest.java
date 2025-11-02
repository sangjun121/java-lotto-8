package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, true, FOURTH",
            "4, false, FOURTH",
            "3, true, FIFTH",
            "3, false, FIFTH",
            "2, false, NONE",
            "2, true, NONE",
            "1, false, NONE",
            "0, true, NONE"
    })
    void 매칭_결과에_따라_올바른_Rank가_반환된다(int matchCount, boolean isBonus, Rank expectedRank) {
        Rank result = Rank.valueOf(matchCount, isBonus);

        assertThat(result).isEqualTo(expectedRank);
    }

    @Test
    void 전체_순위_중_당첨인_순위만_반환한다() {
        List<Rank> result = Rank.winningRanks();

        assertThat(result)
                .containsExactlyInAnyOrder(
                        Rank.FIRST,
                        Rank.SECOND,
                        Rank.THIRD,
                        Rank.FOURTH,
                        Rank.FIFTH
                ).doesNotContain(Rank.NONE);
    }
}
