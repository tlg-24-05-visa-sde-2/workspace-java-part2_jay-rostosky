/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.entertainment.catalog;

import static org.junit.Assert.*;
import java.util.Collection;
import java.util.Map;
import org.junit.Test;
import com.entertainment.Television;

public class CatalogTest {

    @Test
    public void findByBrands_shouldReturnPopulatedMap_oneRowPerBrandPassed_severalBrands() {
        Map<String,Collection<Television>> tvMap = Catalog.findByBrands("Sony", "Zenith", "NOT-FOUND");

        assertEquals(3, tvMap.size());

        verifyCollection(tvMap.get("Sony"), 7, "Sony");
        verifyCollection(tvMap.get("Zenith"), 9, "Zenith");
        verifyCollection(tvMap.get("NOT-FOUND"), 0, "NOT-FOUND");
    }

    @Test
    public void findByBrands_shouldReturnPopulatedMap_oneRowPerBrandPassed_singleBrand() {
        Map<String,Collection<Television>> tvMap = Catalog.findByBrands("Sony");

        assertEquals(1, tvMap.size());

        Collection<Television> sonyTvs = tvMap.get("Sony");
        verifyCollection(sonyTvs, 7, "Sony");
    }

    private void verifyCollection(Collection<Television> tvs, int expectedSize, String brand) {
        assertEquals(expectedSize, tvs.size());

        for (Television tv : tvs) {
            assertEquals(brand, tv.getBrand());
        }
    }

    @Test
    public void findByBrand_shouldReturnCollectionWithMatchingBrand_brandFound() {
        Collection<Television> tvs = Catalog.findByBrand("Sony");

        verifyCollection(tvs, 7, "Sony");
    }

    /**
     * Contract: a no-matches result should be an empty collection (not null).
     */
    @Test
    public void findByBrand_shouldReturnEmptyCollection_brandNotFound() {
        Collection<Television> tvs = Catalog.findByBrand("NO-MATCHES");

        assertTrue(tvs.isEmpty());
    }
}