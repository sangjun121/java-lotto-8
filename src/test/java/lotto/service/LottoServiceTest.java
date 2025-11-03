package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.math.BigDecimal;
import java.util.List;
import lotto.config.AppConfig;
import lotto.controller.dto.WinningStatistic;
import lotto.domain.Lottos;
import lotto.domain.vo.BonusNumber;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.PurchaseAmount;
import lotto.domain.vo.WinningNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoServiceTest {
    private static final List<Integer> VALID_WINNING_NUMBER = List.of(1, 2, 3, 4, 5, 6);
    private static final int VALID_BONUS_NUMBER = 7;

    private LottoService lottoService;
    private WinningNumber validWinningNumber;
    private BonusNumber validBonusNumber;

    @BeforeEach
    void setUp() {
        this.lottoService = AppConfig.getInstance().lottoService();
        this.validWinningNumber = WinningNumber.from(VALID_WINNING_NUMBER);
        this.validBonusNumber = BonusNumber.from(VALID_BONUS_NUMBER, validWinningNumber);
    }

    private Lottos createExampleLottos() {
        Lotto first = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto second = Lotto.from(List.of(1, 2, 3, 4, 5, 7));
        Lotto third = Lotto.from(List.of(1, 2, 3, 4, 5, 8));
        Lotto none = Lotto.from(List.of(10, 11, 12, 13, 14, 15));
        return new Lottos(List.of(first, second, third, none));
    }

    @Test
    void 당첨금액을_바탕으로_올바른_개수의_로또를_생성한다() {
        PurchaseAmount purchaseAmount = PurchaseAmount.from(10000);

        Lottos lottos = lottoService.generateLotto(purchaseAmount);
        int resultLottosSize = lottos.getValues().size();

        assertThat(resultLottosSize).isEqualTo(10);
    }

    @Test
    void 로또의_당첨_순위_개수를_올바르게_반환한다() {
        Lottos lottos = createExampleLottos();
        List<WinningStatistic> result = lottoService.calculateWinningStatistic(lottos, validWinningNumber,
                validBonusNumber);

        assertThat(result)
                .extracting("matchCount", "count", "isBonus")
                .containsExactlyInAnyOrder(
                        tuple(3, 0, false),
                        tuple(4, 0, false),
                        tuple(5, 1, false),
                        tuple(5, 1, true),
                        tuple(6, 1, false)
                );
    }

    @Test
    void 로또가_각_1등_2등_3등이면서_구입금액이_만원인_경우_수익률은_20315000프로이다(){
        Lottos lottos = createExampleLottos();
        PurchaseAmount purchaseAmount = PurchaseAmount.from(10000);
        BigDecimal bigDecimal = lottoService.calculateProfitRate(lottos, validWinningNumber, validBonusNumber,
                purchaseAmount);

        assertThat(bigDecimal.toPlainString()).isEqualTo("20315000.0");
    }
}
