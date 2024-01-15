package alg;

public class Tree {

    public Vertice root = null;

    private int maxLength = 0;
    private int opsCount = 0;
    private String[] commands = null;

    private int changesCount = 0;

    private Vertice[] branch = null;
    private Vertice[] sortedBranch = null;
    private int index = 0;
    private int branchLength = 0;

    private boolean deleted = false;

    private Vertice parent_delete = null;
    private Vertice search_node = null;

    static int prev = Integer.MIN_VALUE;
    
    public Tree(int maxLength, int opsCount, String[] commands) {
        this.maxLength = maxLength;
        this.opsCount = opsCount;
        this.commands = commands;

        this.branch = new Vertice[maxLength];
        this.sortedBranch = new Vertice[maxLength];

    }

    private Vertice addNode(Vertice node, int value) {
        if (node == null) {
            node = new Vertice(value);

        } else {
            if (value < node.getValue()) {
                node.setLeft(addNode(node.getLeft(), value));
            } else {
                node.setRight(addNode(node.getRight(), value));
            }
        }

        if (node.getLeft() == null || node.getRight() == null) {
            branchLength += 1;
            branch[maxLength - branchLength] = node;
            
            if (branchLength == maxLength) {
                sortBranch(branch[0]);
                node = balanceBranch(sortedBranch, 0, maxLength - 1);

                branchLength = 0;
                index = 0;
                changesCount += 1;
            }

        } else {
            branchLength = 0;
        }

        return node;
    }

    private Vertice deleteNode(Vertice node, int value) {
        if (node == null) {
            return null;
        }

        if (node.getLeft() == null || node.getRight() == null) {
            branch[branchLength] = node;
            branchLength += 1;

            if (node.getLeft() == null && node.getRight() == null && branchLength != maxLength) {
                branchLength -= 1;
                branch[branchLength] = null;
            } else if (node.getValue() == value) {
                branchLength -= 1;
                branch[branchLength] = null;

            }

        } else if (node.getLeft() != null && node.getRight() != null) {

            if (node.getValue() != value) {

                if (node.getLeft().getValue() != value && node.getRight().getValue() != value) {
                    parent_delete = node;
                    branchLength = 0;
                } 

            } else { 

                if ((node.getRight().getRight() != null || node.getRight().getLeft() != null)) {
                    parent_delete = node;
                    branchLength = 0;
                }
            }
            
        }

        if (value < node.getValue()) {
            node.setLeft(deleteNode(node.getLeft(), value));

            if (deleted) {
                deleted = false;
                search_node = node;
            }

        } else if (value > node.getValue()) {
            node.setRight(deleteNode(node.getRight(), value));

            if (deleted) {
                deleted = false;
                search_node = node;
            }

        } else {

            deleted = true;

            if (node.getLeft() == null) {
                return node.getRight();

            } else if (node.getRight() == null) {
                return node.getLeft();

            } else {
                Vertice temp = findMin(node.getRight());
                node.setValue(temp.getValue());
                node.setRight(deleteTempNode(node.getRight(), temp.getValue()));
            }

        }

        return node;
    }

    private Vertice deleteTempNode(Vertice node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.getValue()) {
            node.setLeft(deleteTempNode(node.getLeft(), value));

        } else if (value > node.getValue()) {
            node.setRight(deleteTempNode(node.getRight(), value));

        } else {

            if (node.getLeft() == null) {
                return node.getRight();

            } else if (node.getRight() == null) {
                return node.getLeft();

            } else {
                Vertice temp = findMin(node.getRight());
                node.setValue(temp.getValue());
                node.setRight(deleteTempNode(node.getRight(), temp.getValue()));
            }

        }

        return node;
    }


    private Vertice findMin(Vertice node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }

        return node;
    }

    private void searchBranch(Vertice node, Vertice parent) {


        if (node.getLeft() == null || node.getRight() == null) {
            branch[branchLength] = node;
            branchLength += 1;
            
            if (node.getLeft() == null && node.getRight() == null && branchLength != maxLength) {
                branchLength = 0;
            }

            if (branchLength == maxLength) {
                Vertice child = null;

                if (branch[maxLength - 1].getLeft() != null) {
                    child = branch[maxLength - 1].getLeft();
                    branch[maxLength - 1].setLeft(null);
                } else {
                    child = branch[maxLength - 1].getRight();
                    branch[maxLength - 1].setRight(null);
                }

                sortBranch(branch[0]);
                index = 0;
                node = balanceBranch(sortedBranch, 0, maxLength - 1);

                changesCount += 1;
                branchLength = 0;
                

                if (parent != null) {
                    if (node.getValue() < parent.getValue()) {
                        parent.setLeft(null);
                        parent.setLeft(node);
                    } else {
                        parent.setRight(null);
                        parent.setRight(node);
                    }
                } else {
                    root = node;
                }

                parent = node;

                if (child != null) {
                    addChild(node, child);
                }
            }

        } else if ((node.getLeft() != null && node.getRight() != null)) {
            branchLength = 0;
            parent = node;
        }

        if (node.getLeft() != null) {  
            searchBranch(node.getLeft(), parent);
        }

        if (node.getRight() != null) {  
            searchBranch(node.getRight(), parent);
        }

    }

    private Vertice addChild(Vertice node, Vertice child) {

        if (node == null) {
            return child;
        }

        if (child.getValue() < node.getValue()) {
            node.setLeft(addChild(node.getLeft(), child));
        } else {
            node.setRight(addChild(node.getRight(), child));
        }

        return node;
    }

    public void sortBranch(Vertice node) {
        if (node == null) {
            return;
        } else {
            sortBranch(node.getLeft());
            if (index < maxLength) {
                sortedBranch[index] = node;
                index += 1;
            } else {
                return;
            }
            sortBranch(node.getRight());
        }
    }

    private Vertice balanceBranch(Vertice[] sortedBranch, int low, int high) {
        if (low == high) {
            sortedBranch[low].setLeft(null);
            sortedBranch[low].setRight(null);

            return sortedBranch[low];
        }

        int mid = low + (high - low) / 2;
        Vertice temp = sortedBranch[mid];
        temp.setLeft(balanceBranch(sortedBranch, low, mid - 1));
        temp.setRight(balanceBranch(sortedBranch, mid + 1, high));


        return temp;
    }

    public void buildGraph() {

        for (int i = 0; i < opsCount; ++i) {
            String[] tokens = commands[i].split(" ");
            String command = tokens[0];
            int value = Integer.parseInt(tokens[1]);

            branchLength = 0;
            parent_delete = null;
            search_node = null;


            if (command.equals("I")) {
                root = addNode(root, value);
            }

            if (command.equals("D")) {
                root = deleteNode(root, value);
                
                if (search_node != null) {
                    searchBranch(search_node, parent_delete);
                }
            }
        }
    }

    public void printGraph(Vertice node) {
        if (node == null) {
            return;
        }
        printGraph(node.getLeft());
        System.out.print(node.getValue() + " ");
        printGraph(node.getRight());

    }

    public void printAnswer() {
        System.out.println(changesCount);
        
    }

    static boolean isBST(Vertice root)
    {
        if (root != null) {
            if (!isBST(root.getLeft())) {
                return false;
            }
 
            if (root.getValue() < prev) {
                return false;
            }
            
            prev = root.getValue(); 
 
            return isBST(root.getRight());
        }
 
        return true;
    }
    
}
