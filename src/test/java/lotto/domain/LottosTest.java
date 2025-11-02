package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
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

    private Lottos createAllRankLottos() {
        Lotto first = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto second = Lotto.from(List.of(1, 2, 3, 4, 5, 7));
        Lotto third = Lotto.from(List.of(1, 2, 3, 4, 5, 8));
        Lotto fourth = Lotto.from(List.of(1, 2, 3, 4, 7, 8));
        Lotto fifth = Lotto.from(List.of(1, 2, 3, 7, 8, 9));
        Lotto none = Lotto.from(List.of(1, 2, 7, 8, 9, 10));

        return new Lottos(List.of(first, second, third, fourth, fifth, none));
    }

    @Test
    void 매칭_결과에_따라_올바른_Rank_개수가_반환된다() {
        Lottos allRankLottos = createAllRankLottos();
        Map<Rank, Integer> result = allRankLottos.countByRank(validWinningNumber, validBonusNumber);

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

    @Test
    void 로또의_전체_상금_합계를_계산한다() {
        Lottos allRankLottos = createAllRankLottos();
        int expectedTotalPrizeMoney = Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NONE)
                .mapToInt(Rank::getPrizeMoney)
                .sum();

        int resultTotalPrizeMoney = allRankLottos.calculateTotalPrize(validWinningNumber, validBonusNumber);

        assertThat(resultTotalPrizeMoney).isEqualTo(expectedTotalPrizeMoney);
    }

    @Test
    void 총_상금이_구입_금액과_같을_경우_수익률은_100퍼센트이다() {
        Lotto first = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lottos lottos = new Lottos(List.of(first));
        int purchaseAmount = Rank.FIRST.getPrizeMoney();

        BigDecimal profitRate = lottos.calculateProfitRate(validWinningNumber, validBonusNumber, purchaseAmount);

        assertThat(profitRate.toPlainString()).isEqualTo("100.0");
    }

    @Test
    void 총_상금이_구입_금액의_절반일_경우_수익률은_50퍼센트이다() {
        Lotto third = Lotto.from(List.of(1, 2, 3, 4, 5, 8));
        Lottos lottos = new Lottos(List.of(third));
        int purchaseAmount = Rank.THIRD.getPrizeMoney() * 2;

        BigDecimal profitRate = lottos.calculateProfitRate(validWinningNumber, validBonusNumber, purchaseAmount);

        assertThat(profitRate.toPlainString()).isEqualTo("50.0");
    }

    @Test
    void 수익률은_반올림하여_소수점_첫째자리까지_반환된다() {
        Lottos spyLottos = new Lottos(List.of()) {
            @Override
            public int calculateTotalPrize(WinningNumber winningNumber, BonusNumber bonusNumber) {
                return 3333;
            }
        };
        int purchaseAmount = 10000;

        BigDecimal profitRate = spyLottos.calculateProfitRate(validWinningNumber, validBonusNumber, purchaseAmount);

        assertThat(profitRate.toPlainString()).isEqualTo("33.3");
    }
}
