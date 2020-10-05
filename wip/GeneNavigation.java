package wip;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
/**
 * GeneNavigation class
 *
 * @version 3
 *
 *
 * used to make search methods, all other methods that need to be outside of main
 * think of this class as our control/behind the scenes that makes everything happen
 * my goal is to get the throws exception out of this class
 */

public class GeneNavigation {
    private GeneDataBase gdb;

    public GeneNavigation(String fileName) throws Exception {
        gdb = new GeneDataBase(fileName);
        gdb.createSiblings();
    }


}