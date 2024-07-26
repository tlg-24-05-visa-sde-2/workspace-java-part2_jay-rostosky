package com.duckrace;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * This is a lookup table of ids to student names.
 * When a duck wins for the first time, we need to find out who that is.
 * This lookup table could be hardcoded with the data, or we could read the data 
 * in from a file, so that no code changes would need to be made for each cohort.
 *
 * Map<Integer,String> studentIdMap;
 * 
 * Integer    String
 * =======    ======
 *    1       John
 *    2       Jane
 *    3       Danny
 *    4       Armando
 *    5       Sheila
 *    6       Tess
 * 
 *
 * We also need a data structure to hold the results of all winners.
 * This data structure should facilitate easy lookup, retrieval, and storage.
 *
 * Map<Integer,DuckRacer> racerMap;
 *
 * Integer    DuckRacer
 * =======    =========
 *            id    name     wins   rewards
 *            --    ----     ----   -------
 *    5        5    Sheila     2    PRIZES, PRIZES
 *    6        6    Tess       1    PRIZES
 *   13       13    Zed        3    PRIZES, DEBIT_CARD, DEBIT_CARD
 *   17       17    Dom        1    DEBIT_CARD
 */

public class Board implements Serializable {
    private static final String DATA_FILE_PATH = "data/board.dat";
    private static final String CONF_FILE_PATH = "conf/student-ids.csv";

    /*
     * If data/board.dat exists, the application has been run before, at least once.
     * Therefore, re-create the Board object from that binary file.
     *
     * Otherwise, this is the very first time the app has been run.
     * Therefore, create and return new Board.
     */
    public static Board getInstance() {
        Board board = null;

        if (Files.exists(Path.of(DATA_FILE_PATH))) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE_PATH))) {
                board = (Board) in.readObject();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            board = new Board();
        }
        return board;
    }

    private final Map<Integer,String> studentIdMap = loadStudentIdMap();
    private final Map<Integer,DuckRacer> racerMap  = new TreeMap<>();

    // private ctor - prevent instantiation outside - only getInstance() can do this
    private Board() {
    }

    /*
     * Updates the board (racerMap) by making a DuckRacer win().
     *
     * This could mean fetching an existing DuckRacer from racerMap,
     * or we might need to create a "new DuckRacer()", put() it in the map,
     * and then make it win().  Either way, it needs to win().
     */
    public void update(int id, Reward reward) {
        DuckRacer racer = null;

        if (racerMap.containsKey(id)) {                       // already in racerMap
            racer = racerMap.get(id);                         // fetch it
        }
        else {
            racer = new DuckRacer(id, studentIdMap.get(id));  // not in map, create new
            racerMap.put(id, racer);                          // insert it
        }
        racer.win(reward);                                    // make it "win"

        save();
    }

    /*
     * TODO: render the data as we see it in the "real" application.
     * See Session 5 in Java Part 1 manual, Formatted Output.
     */
    public void show() {
        if (racerMap.isEmpty()) {
            System.out.println("There are currently no results to show\n");
        }
        else {
            String header = """
                    Duck Race Results
                    =================
                    
                    id    name      wins    rewards
                    --    ----      ----    -------
                    """;

            StringBuilder board = new StringBuilder(header);

            for (DuckRacer racer : racerMap.values()) {
                String rewardsString = racer.getRewards().toString();
                String rewards = rewardsString.substring(1, rewardsString.length() - 1);

                String row = String.format("%2d    %-9s %4d    %s\n",
                        racer.getId(), racer.getName(), racer.getWins(), rewards);
                board.append(row);
            }
            System.out.println(board);
        }
    }

    private void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE_PATH))) {
            out.writeObject(this);  // write "me" to binary file ("I" am a Board object)
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<Integer,String> loadStudentIdMap() {
        Map<Integer,String> map = new HashMap<>();

        // read all lines from CSV file, and process each one into an integer and a string
        try {
            List<String> lines = Files.readAllLines(Path.of(CONF_FILE_PATH));

            // for each line, "split" the string into "tokens"
            for (String line : lines) {
                String[] tokens = line.split(",");  // "1" and "Bullen"
                Integer id = Integer.valueOf(tokens[0]);
                String name = tokens[1];
                map.put(id, name);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}