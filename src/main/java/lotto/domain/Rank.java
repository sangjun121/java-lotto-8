package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public enum Rank {
    FIRST(6, 2000000000, true) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count == this.getMatchCount();
        }
    },
    SECOND(5, 30000000, true) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count == 5 && isBonus;
        }
    },
    THIRD(5, 1500000, true) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count == 5 && !isBonus;
        }
    },
    FOURTH(4, 50000, true) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count == 4;
        }
    },
    FIFTH(3, 5000, true) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count == 3;
        }
    },
    NONE(-1, 0, false) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count < 3;
        }
    };

    private final int matchCount;
    private final int prizeMoney;
    private final boolean isWinning;

    Rank(int matchCount, int prizeMoney, boolean isWinning) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.isWinning = isWinning;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public boolean isWinning() {
        return isWinning;
    }

    abstract boolean matches(int count, boolean isBonus);

    public static Rank valueOf(int matchCount, boolean isBonus) {
        for (Rank rank : values()) {
            if (rank.matches(matchCount, isBonus)) {
                return rank;
            }
        }

        return NONE;
    }

    public static List<Rank> winningRanks() {
        List<Rank> ranks = new ArrayList<>();
        for (Rank rank : values()) {
            if (rank.isWinning()) {
                ranks.add(rank);
            }
        }
        return ranks;
    }
}

