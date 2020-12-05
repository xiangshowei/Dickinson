package comp132.examples.recursion;

/**
 * Solver for the Tower Of Hanoi problem.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Nov 1, 2009
 */
public class TowerOfHanoi {

    /**
     * Print out the instructions for solving a Tower of Hanoi puzzle with n
     * disks on the src peg and the goal of transferring them to the dest peg.
     * 
     * @param n the number of disks.
     * @param src the peg on which the disks start.
     * @param dest the peg to which to move the disks.
     * @param aux the third peg that is neither the source or the destination.
     */
    public static void towerSolver(int n, String src, String dest, String aux) {
        if (n == 1) {
            // Only one disk - so just move it.
            System.out.println("Move disk from " + src + " to " + dest);
        }
        else { // More than one disk so solve recursively.

            // Move n-1 disks from src to aux.
            towerSolver(n - 1, src, aux, dest);

            // Move the last disk from src to dest.
            towerSolver(1, src, dest, aux);

            // Move n-1 disks from aux to dest.
            towerSolver(n - 1, aux, dest, src);
        }
    }

    /**
     * Solve an instance of the Tower of Hanoi problem that moves 3 disks from
     * peg A to peg C.
     */
    public static void main(String[] args) {
        towerSolver(3, "A", "C", "B");
    }
}
