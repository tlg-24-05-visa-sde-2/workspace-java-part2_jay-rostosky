/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */

package com.javatunes.catalog;

import java.util.Collection;

class InMemoryCatalogTestMain {

    /*
     * One by one, complete each test method below, and then "activate" it by
     * uncommenting the call to that method in main().
     *
     * Once you see that the test method verifies the corresponding business method
     * works correctly, you can comment out that call in main() and proceed to the next one.
     */
    public static void main(String[] args) {
        // testFindById();
        // testFindByKeyword();
        // testFindByCategory();
        // testSize();
        // testGetAll();

        // TASK methods above and beyond the spec (Catalog interface)
        // testFindSelfTitled();
        testFindRockLessThan();
    }

    private static void testFindRockLessThan() {
        InMemoryCatalog catalog = new InMemoryCatalog();

        Collection<MusicItem> cheapRockItems = catalog.findRockLessThan(12.0);  // should be 3
        dump(cheapRockItems);
    }

    // DONE
    private static void testFindSelfTitled() {
        InMemoryCatalog catalog = new InMemoryCatalog();

        Collection<MusicItem> items = catalog.findSelfTitled();
        dump(items);
    }

    // DONE
    private static void testFindById() {
        InMemoryCatalog catalog = new InMemoryCatalog();

        MusicItem item = catalog.findById(6L);  // autobox primitive long to Long object
        System.out.println(item);

        MusicItem notFound = catalog.findById(66L);  // should return null
        System.out.println(notFound);
    }

    // TODO
    private static void testFindByKeyword() {
    }

    // DONE
    private static void testFindByCategory() {
        InMemoryCatalog catalog = new InMemoryCatalog();

        Collection<MusicItem> items = catalog.findByCategory(MusicCategory.POP);
        dump(items);
    }

    // DONE
    private static void testSize() {
        InMemoryCatalog catalog = new InMemoryCatalog();
        System.out.println(catalog.size());  // should be 18
    }

    // DONE
    private static void testGetAll() {
        InMemoryCatalog catalog = new InMemoryCatalog();

        Collection<MusicItem> readOnlyItems = catalog.getAll();  // read-only view of the 18
        readOnlyItems.clear();  // NOPE - throws exception
    }


    private static void dump(Collection<MusicItem> items) {
        for (MusicItem item : items) {
            System.out.println(item);  // toString() automatically called
        }
    }
}