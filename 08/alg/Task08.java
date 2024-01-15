package alg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task08 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(br);
        br.close();
    }

    public static void solution(BufferedReader br) throws IOException {
        String[] params = br.readLine().split(" ");
        int rows = Integer.parseInt(params[0]);
        int cols = Integer.parseInt(params[1]);

        Sector[][] dpLeft = new Sector[rows][cols];
        Sector[][] dpRight = new Sector[rows][cols];
        Sector[][] dpTotal = new Sector[rows][cols];

        for (int i = 0; i < rows; ++i) {
            String[] gridRow = br.readLine().split(" ");
            for (int j = 0; j < cols; ++j) {
                int currValue = Integer.parseInt(gridRow[j]);
                dpLeft[i][j] = new Sector(0, currValue, currValue == 0);
                dpRight[i][j] = new Sector(0, currValue, currValue == 0);
                if (currValue != 0) {
                    dpTotal[i][j] = dpLeft[i][j];
                }
            }
        }

        int maxValue = 0;
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (dpLeft[i][j].canWalk) {
                    int myVal = myValue(i, j, rows, cols, dpLeft);
                    if (i == 0) {
                        if (j == 0) {
                            dpLeft[i][j].value = myVal;
                            dpLeft[i][j].dist = 1;

                        } else {
                            if (myVal >= dpLeft[i][j - 1].value() + myVal) {
                                dpLeft[i][j].value = myVal;
                                dpLeft[i][j].dist = 1;

                            } else {
                                dpLeft[i][j].value = dpLeft[i][j - 1].value() + myVal;
                                dpLeft[i][j].dist = dpLeft[i][j - 1].dist() + 1;
                            }
                        }

                    } else {
                        if (j == 0) {
                            dpLeft[i][j].value = dpTotal[i - 1][j].value() + myVal;
                            dpLeft[i][j].dist = dpTotal[i - 1][j].dist() + 1;

                        } else {
                            if (dpTotal[i - 1][j].value() > dpLeft[i][j - 1].value()) {
                                dpLeft[i][j].value = dpTotal[i - 1][j].value() + myVal;
                                dpLeft[i][j].dist = dpTotal[i - 1][j].dist() + 1; 

                            } else if (dpTotal[i - 1][j].value() < dpLeft[i][j - 1].value()) {
                                dpLeft[i][j].value = dpLeft[i][j - 1].value() + myVal;
                                dpLeft[i][j].dist = dpLeft[i][j - 1].dist() + 1;

                            } else {
                                if (dpTotal[i - 1][j].dist() < dpLeft[i][j - 1].dist()) {
                                    dpLeft[i][j].value = dpTotal[i - 1][j].value() + myVal;
                                    dpLeft[i][j].dist = dpTotal[i - 1][j].dist() + 1;
                                    
                                } else if (dpTotal[i - 1][j].dist() > dpLeft[i][j - 1].dist()) {
                                    dpLeft[i][j].value = dpLeft[i][j - 1].value() + myVal;
                                    dpLeft[i][j].dist = dpLeft[i][j - 1].dist() + 1;
                                    
                                } else {
                                    if (dpTotal[i - 1][j].dist() == Integer.MAX_VALUE) {
                                        dpLeft[i][j].value = 0;
                                        dpLeft[i][j].dist = Integer.MAX_VALUE;
                                        dpLeft[i][j].canWalk = false;

                                    } else {
                                        dpLeft[i][j].value = dpTotal[i - 1][j].value() + myVal;
                                        dpLeft[i][j].dist = dpTotal[i - 1][j].dist() + 1;
                                    }
                                }
                            }
                        }
                    }

                    dpLeft[i][j].checked = true;
                    if (dpRight[i][j].checked) {
                        if (dpLeft[i][j].value() > dpRight[i][j].value()) {
                            dpTotal[i][j] = dpLeft[i][j];

                        } else if (dpLeft[i][j].value() < dpRight[i][j].value()) {
                            dpTotal[i][j] = dpRight[i][j];

                        } else {
                            if (dpLeft[i][j].dist() < dpRight[i][j].dist()) {
                                dpTotal[i][j] = dpLeft[i][j];

                            } else {
                                dpTotal[i][j] = dpRight[i][j]; 
                            }
                        }

                        if (i == rows - 1) {
                            if (dpTotal[i][j].value() > maxValue) {
                                maxValue = dpTotal[i][j].value();
                                minDist = dpTotal[i][j].dist();
                
                            } else if (dpTotal[i][j].value() == maxValue) {
                                if (dpTotal[i][j].dist() < minDist) {
                                    minDist = dpTotal[i][j].dist();  
                                }
                            }
                        }
                    }
                } 


                int indexRight = cols - j - 1;
                if (dpRight[i][indexRight].canWalk) {
                    int myVal = myValue(i, indexRight, rows, cols, dpRight);
                    if (i == 0) {
                        if (indexRight == cols - 1) {
                            dpRight[i][indexRight].value = myVal;
                            dpRight[i][indexRight].dist = 1;

                        } else {
                            if (myVal >= dpRight[i][indexRight + 1].value() + myVal) {
                                dpRight[i][indexRight].value = myVal;
                                dpRight[i][indexRight].dist = 1;

                            } else {
                                dpRight[i][indexRight].value = dpRight[i][indexRight + 1].value() + myVal;
                                dpRight[i][indexRight].dist = dpRight[i][indexRight + 1].dist() + 1;
                            }
                        }

                    } else {
                        if (indexRight == cols - 1) {
                            dpRight[i][indexRight].value = dpTotal[i - 1][indexRight].value() + myVal;
                            dpRight[i][indexRight].dist = dpTotal[i - 1][indexRight].dist() + 1;

                        } else {
                            if (dpTotal[i - 1][indexRight].value() > dpRight[i][indexRight + 1].value()) {
                                dpRight[i][indexRight].value = dpTotal[i - 1][indexRight].value() + myVal;
                                dpRight[i][indexRight].dist = dpTotal[i - 1][indexRight].dist() + 1; 

                            } else if (dpTotal[i - 1][indexRight].value() < dpRight[i][indexRight + 1].value()) {
                                dpRight[i][indexRight].value = dpRight[i][indexRight + 1].value() + myVal;
                                dpRight[i][indexRight].dist = dpRight[i][indexRight + 1].dist() + 1;

                            } else {
                                if (dpTotal[i - 1][indexRight].dist() < dpRight[i][indexRight + 1].dist()) {
                                    dpRight[i][indexRight].value = dpTotal[i - 1][indexRight].value() + myVal;
                                    dpRight[i][indexRight].dist = dpTotal[i - 1][indexRight].dist() + 1;
                                    
                                } else if (dpTotal[i - 1][indexRight].dist() > dpRight[i][indexRight + 1].dist()) {
                                    dpRight[i][indexRight].value = dpRight[i][indexRight + 1].value() + myVal;
                                    dpRight[i][indexRight].dist = dpRight[i][indexRight + 1].dist() + 1;
                                    
                                } else {
                                    if (dpTotal[i - 1][indexRight].dist() == Integer.MAX_VALUE) {
                                        dpRight[i][indexRight].value = 0;
                                        dpRight[i][indexRight].dist = Integer.MAX_VALUE;
                                        dpRight[i][indexRight].canWalk = false;

                                    } else {
                                        dpRight[i][indexRight].value = dpTotal[i - 1][indexRight].value() + myVal;
                                        dpRight[i][indexRight].dist = dpTotal[i - 1][indexRight].dist() + 1;
                                    }
                                }
                            }   
                        }
                    }

                    dpRight[i][indexRight].checked = true;
                    if (dpLeft[i][indexRight].checked) {
                        if (dpLeft[i][indexRight].value() > dpRight[i][indexRight].value()) {
                            dpTotal[i][indexRight] = dpLeft[i][indexRight];

                        } else if (dpLeft[i][indexRight].value() < dpRight[i][indexRight].value()) {
                            dpTotal[i][indexRight] = dpRight[i][indexRight];

                        } else {
                            if (dpLeft[i][indexRight].dist() < dpRight[i][indexRight].dist()) {
                                dpTotal[i][indexRight] = dpLeft[i][indexRight];

                            } else {
                                dpTotal[i][indexRight] = dpRight[i][indexRight]; 
                            }
                        }

                        if (i == rows - 1) {
                            if (dpTotal[i][indexRight].value() > maxValue) {
                                maxValue = dpTotal[i][indexRight].value();
                                minDist = dpTotal[i][indexRight].dist();
                
                            } else if (dpTotal[i][indexRight].value() == maxValue) {
                                if (dpTotal[i][indexRight].dist() < minDist) {
                                    minDist = dpTotal[i][indexRight].dist();  
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(maxValue + " " + minDist);
    }

    public static int myValue(int i, int j, int rows, int cols, Sector[][] table) {
        int sum = 0;

        if (j != 0) {
            if (table[i][j - 1].canWalk == false) {
                sum += table[i][j - 1].value;
            }
        }

        if (i != 0) {
            if (table[i - 1][j].canWalk == false) {
                sum += table[i - 1][j].value;
            }
        }

        if (j != cols - 1) {
            if (table[i][j + 1].canWalk == false) {
                sum += table[i][j + 1].value;
            }

        }

        if (i != rows - 1) {
            if (table[i + 1][j].canWalk == false) {
                sum += table[i + 1][j].value;
            }

        }

        return sum;
    }
}
