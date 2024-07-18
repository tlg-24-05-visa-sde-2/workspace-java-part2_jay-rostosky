package com.crisp;

import java.util.Comparator;

class RadishGuysOnTopComparator implements Comparator<Radish> {

    @Override
    public int compare(Radish r1, Radish r2) {
        return Integer.compare(r1.getGuysOnTop(), r2.getGuysOnTop());
    }
}