package com.duckrace;

import java.util.Collection;

class DuckRacerTest {

    public static void main(String[] args) {
        DuckRacer racer = new DuckRacer(1, "Bullen");

        System.out.println(racer);  // toString() automatically called

        racer.win(Reward.PRIZES);
        racer.win(Reward.PRIZES);
        racer.win(Reward.DEBIT_CARD);

        System.out.println(racer);
        System.out.println();

        // fetch the collection from this existing DuckRacer object
        Collection<Reward> rewards = racer.getRewards();
        System.out.println(rewards);  // shows 3

        racer.win(Reward.DEBIT_CARD);
        System.out.println(rewards);  // shows 4 now (it "reads thru" to the underlying collection)
    }
}