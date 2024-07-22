package com.javatunes.catalog;

import org.junit.Before;
import org.junit.Test;
import java.util.Collection;
import static org.junit.Assert.*;

public class InMemoryCatalogTest {
    // object(s) under test - called the "fixture"
    private InMemoryCatalog catalog;

    @Before
    public void setUp() {
        catalog = new InMemoryCatalog();
    }

    @Test
    public void findByCategory_shouldReturnCollectionWithThatCategory() {
        Collection<MusicItem> popItems = catalog.findByCategory(MusicCategory.POP);

        assertEquals(4, popItems.size());

        for (MusicItem item : popItems) {
            assertEquals(MusicCategory.POP, item.getMusicCategory());
        }
    }

    @Test
    public void findById_shouldReturnMusicItemWithThatId_idFound() {
        MusicItem item = catalog.findById(18L);

        assertNotNull(item);
        assertEquals(18L, item.getId().longValue());  // extracts the long from the Long
    }

    @Test
    public void findById_shouldReturnNull_idNotFound() {
        MusicItem item = catalog.findById(1001L);
        assertNull(item);
    }
}