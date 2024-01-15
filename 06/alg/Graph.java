//used code from https://www.programiz.com/dsa/avl-tree for avl basic operations

package alg;

public class Graph {

    private int opsCount = 0;
    private String[] commands = null;

    public Node root = null;

    private int depth = -1;
    private int rotations = 0;
    private int consolidations = 0;

    public Graph(int opsCount, String[] commands) {
        this.opsCount = opsCount;
        this.commands = commands;
    } 

    public int max(int a, int b) {
        return (a > b) ? a : b;
    }
    
    public Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        updateMarkedNum(x);
        updateMarkedNum(y);

        return y;
    }

    public Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        updateMarkedNum(y);
        updateMarkedNum(x);

        return x;
    }

    public int height(Node node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    public int marked(Node node) {
        if (node == null) {
            return 0;
        }

        return node.markedNum;
    }

    public int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }

        return height(node.left) - height(node.right);
    }

    public void updateMarkedNum(Node node) {
        node.markedNum = Math.max(marked(node.left), marked(node.right));

        if (node.deleted) {
            node.markedNum += 1;

        }
    }

    public Node addNode(Node node, int value) {
        if (node == null) {
            node = new Node(value);
            return node;
        } 

        if (node.value > value) {
            node.left = addNode(node.left, value);

        } else if (node.value < value) {
            node.right = addNode(node.right, value);

        } else if (node.value == value){
            node.deleted = false;
            updateMarkedNum(node);
            return node;
        }
        
        node.height = 1 + max(height(node.left), height(node.right));
        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {
            if (value < node.left.value) {
                rotations += 1;
                return rightRotate(node);
                
            } else if (value > node.left.value) {
                rotations += 2;
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

        } else if (balanceFactor < -1) {
            if (value > node.right.value) {
                rotations += 1;
                return leftRotate(node);

            } else if (value < node.right.value) {
                rotations += 2;
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

        } else {
            updateMarkedNum(node);
        }

        return node;
    }

    public Node minValue(Node node) {
        Node current = node;

        while (current.left != null) {
            current = current.left;
        }
        
        return current;
    }

    public Node maxValue(Node node) {
        Node current = node;

        while (current.right != null) {
            current = current.right;
        }

        return current;
    }

    public Node removeTemp(Node node, Node remove) {
        if (remove.value < node.value) {
            node.left = removeTemp(node.left, remove);

        } else if (remove.value > node.value) {
            node.right = removeTemp(node.right, remove);

        } else { 
            if (node.left != null) {
                node = node.left;

            } else if (node.right != null) {
                node = node.right;

            } else {
                return null;
            }
        }

        node.height = 1 + max(height(node.left), height(node.right));
        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) >= 0) {
                rotations += 1;
                node = rightRotate(node);

            } else {
                rotations += 2;
                node.left = leftRotate(node.left);
                node = rightRotate(node);
            }

        } else if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) <= 0) {
                rotations += 1;
                node = leftRotate(node);

            } else {
                rotations += 2;
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            }

        } else {
            updateMarkedNum(node);
        }

        return node;
    }

    public Node removeNode(Node node) {
        if (node == null) {
            return null;
        }

        if (node.deleted == true && node.markedNum == 1) {
            if (node.left == null && node.right == null) {
                node = null;
                return node;

            } else if (node.left != null) {
                Node max = maxValue(node.left);
                node.deleted = false;
                node.value = max.value;
                node.left = removeTemp(node.left, max);

            } else {
                Node min = minValue(node.right);
                node.deleted = false;
                node.value = min.value;
                node.right = removeTemp(node.right, min);
            }

        } else if (marked(node.left) != 0) {
            node.left = removeNode(node.left);

        } else {
            node.right = removeNode(node.right);
        } 

        node.height = 1 + max(height(node.left), height(node.right));
        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) >= 0) {
                rotations += 1;
                node = rightRotate(node);

            } else {
                rotations += 2;
                node.left = leftRotate(node.left);
                node = rightRotate(node);
            }

        } else if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) <= 0) {
                rotations += 1;
                node = leftRotate(node);

            } else {
                rotations += 2;
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            }

        } else {
            updateMarkedNum(node);
        }

        return node;
    } 

    public Node deleteNode(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.value) {
            node.left = deleteNode(node.left, value);

        } else if (value > node.value) {
            node.right = deleteNode(node.right, value);

        } else {
            node.deleted = true;  
        }

        updateMarkedNum(node);

        return node;
    }

    public void buildGraph() {
        for (int i = 0; i < opsCount; ++i) {
            int command = Integer.parseInt(commands[i]);

            if (command > 0) {
                root = addNode(root, command);
                depth = height(root) - 1;

                if (depth != -1 && (Math.floor(depth / 2) + 1) <= root.markedNum) {
                    consolidations += 1;
                    while (root != null && root.markedNum != 0) {
                        root = removeNode(root);
                    }
                }

                depth = height(root) - 1;
                
            } else {
                root = deleteNode(root, Math.abs(command));
                depth = height(root) - 1;

                if (depth != -1 && (Math.floor(depth / 2) + 1) <= root.markedNum) {
                    consolidations += 1;
                    while (root != null && root.markedNum != 0) {
                        root = removeNode(root);
                    }
                }

                depth = height(root) - 1;
            }
        }
    }

    public void printAnswer() {
        System.out.println(depth + " " + rotations + " " + consolidations);
    }
}
