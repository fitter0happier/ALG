package alg;


//typical class structure
public class Class {
    //here we define attributes of class, every method inside class sees them,
    //make them public to use them outside of this class(e.g. in another class, main)

    public int attr = 0;
    public int arg1;

    //class constructor
    public Class(int arg1) {
        //init attributes with arguments from another class
        this.arg1 = arg1;
    }

    public void saySomething() {
        System.out.println(arg1);
    }

    public void calculate() {
        this.arg1 *= 2;
    }

    public void printAnswer() {
        System.out.println(arg1);
    }
}
