package dotsandboxestry;

import java.util.Collections;
import java.util.Random;

public class Box {

    public static final int UP = 1;
    public static final int DOWN = 3;
    public static final int LEFT = 4;
    public static final int RIGHT = 2;
    public int up;
    public int down;
    public int left;
    public int right;
    public int degree;
    public int x, y, nextside;
    public int parentx, parenty;
    public int chainLength;
    public int hoveredSide;
    public boolean isHumanCompleted=false;
    public boolean isComputerCompleted=false;
    public String name="0";
    public Box() {
        this(0, 0);
    }

    public int getNearestLine(int x, int y, int boxL) {
        int side;
        
        if (y < x) {
            // north west, so north
            if (y < boxL - x) {
//                System.out.println("UP");
                side = UP;
            } // south east, so east
            else {
//                System.out.println("Right");
                side = RIGHT;
            }
        } // south west
        else {
            // north west, so west
            if (y < boxL - x) {
//                System.out.println("Left");
                side = LEFT;
            } // south east, so south
            else {
//                System.out.println("DOWN");
                side = DOWN;
            }
        }
        // return the line
        return side;
    }

    public Box(int x, int y) {
        this.x = x;
        this.y = y;
        up = down = left = right = 0;
        degree = 4;
        chainLength = 0;
        parentx = -1;
        parenty = -1;
    }

    public Box clone() {
        return this;
    }

    public void toString1() {
        if (x > 0 && y > 0) {
            System.out.println("(x, y)=(" + x + " , " + y + ")(px, py)=(" + parentx + " , " + parenty + " ) Length = " + chainLength + " Up = " + up + " down= " + down + " left= " + left + " right= " + right + " degree= " + degree);
        }
    }

    public int getEmpty() {
        int side = 0;

        if (degree <= 0) {
            return -1;
        }
        Random rnd = new Random();
        while (true) {
            int x = rnd.nextInt(4) + 1;
//            System.out.println("rand " + x);
            if (UP == x && up == side) {
                return 1;
            }
            if (DOWN == x && down == side) {
                return 3;
            }
            if (LEFT == x && left == side) {
                return 4;
            }
            if (RIGHT == x && right == side) {
                return 2;
            }
        }
    }

    public void setLine(int side) {
        if (side == 1) {
            up = side;
        } else if (side == 3) {
            down = side;
        } else if (side == 4) {
            left = side;
        } else if (side == 2) {
            right = side;
        }
        degree--;
    }
    
    public void resetLine(int side) {
        if (side == 1) {
            up = 0;
        } else if (side == 3) {
            down = 0;
        } else if (side == 4) {
            left = 0;
        } else if (side == 2) {
            right = 0;
        }
        degree++;
    }

    public void setLineReverse(int side) {
        if (side == 1) {
            down = 3;
        } else if (side == 3) {
            up = 1;
        } else if (side == 4) {
            right = 2;
        } else if (side == 2) {
            left = 4;
        }
        degree--;
    }
    
    public void resetLineReverse(int side) {
        if (side == 1) {
            down = 0;
        } else if (side == 3) {
            up = 0;
        } else if (side == 4) {
            right = 0;
        } else if (side == 2) {
            left = 0;
        }
        degree++;
    }

    public int getReverse(int side) {
        if (side == 1) {
            return 3;
        } else if (side == 3) {
            return 1;
        } else if (side == 4) {
            return 2;
        } else if (side == 2) {
            return 4;
        }
        return -1;
    }

    public boolean isSet(int side) {
        if (up == side) {
            return true;
        }
        if (down == side) {
            return true;
        }
        if (left == side) {
            return true;
        }
        if (right == side) {
            return true;
        }
        return false;
    }

    public void reset() {
        up = down = left = right = 0;
        degree = 4;
    }
}
