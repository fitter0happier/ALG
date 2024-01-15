package alg;

public class Warehouse {
    public final Room[] rooms;
    public final Room entrance;
    public final int[] boxes;

    public int minImbalance = 15000;
    public int minTime;


    public Warehouse(int[][] graph, int[] boxes) {
        entrance = new Room(0);

        int roomCount = graph.length;
        this.boxes = boxes;

        rooms = new Room[roomCount];
        rooms[0] = entrance;

        int index = 0;

        for (int i = 0; i < roomCount; ++i) {
            for (int j = 0; j < roomCount; ++j) {

                if (graph[i][j] != 0) {
                    Room room = new Room(graph[i][j] + rooms[i].getPrice());
                    index += 1;
                    rooms[index] = room;
                    if (rooms[i].getLeft() == null) {
                        rooms[i].setLeft(room);
                        rooms[i].getLeft().setPrevious(rooms[i]);
                    } else {
                        rooms[i].setRight(room);
                        rooms[i].getRight().setPrevious(rooms[i]);
                    }
                }

            }
        }
    }

    public void fillWarehouse(int boxIndex, int currImbalance, Room room) {
        if (boxIndex == boxes.length) {
            if (currImbalance == minImbalance) {
                if (minTime > calcTime()) {
                    minTime = calcTime();
                }
            }

            if (currImbalance < minImbalance) {
                minImbalance = currImbalance;
                minTime = calcTime();
            }
            
            return;
        }

        if (room.isAvailable()) {
            room.addBox(boxes[boxIndex]);
            int newImbalance = calcImbalance();
            fillWarehouse(boxIndex + 1, newImbalance, rooms[0]); 
            room.removeBox(boxes[boxIndex]);
        }
    
        if (room.getLeft() != null) {
            Room nextRoom = room.getLeft();
            fillWarehouse(boxIndex, currImbalance, nextRoom); 
        }

        if (room.getRight() != null) {
            Room nextRoom = room.getRight();
            fillWarehouse(boxIndex, currImbalance, nextRoom); 
        }

    }

    public int calcImbalance() {
        int imbalance = 0;
        for (Room room : rooms) {
            if (room.getLeft() != null) {
                imbalance += Math.abs(room.getTotalWeight() - room.getLeft().getTotalWeight());
            }

            if (room.getRight() != null) {
                imbalance += Math.abs(room.getTotalWeight() - room.getRight().getTotalWeight());
            }
        }

        return imbalance;
    }

    public int calcTime() {
        int time = 0;
        for (Room room : rooms) {
            time += room.getTotalPrice();
        }  

        return time;
    }

    public void printAnswer() {
        System.out.println(minImbalance + " " + minTime);
    }
}
