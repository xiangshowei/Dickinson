package comp132.examples.inheritance;

/**
 * An example to test understanding of the mechanics of inheritance.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Sep 13, 2009
 */

class A {
    public A() {
    }

    public void foo() {
        System.out.println("In A.foo");
    }

    public void bar() {
        System.out.println("In A.bar");
    }

    public void baz() {
        System.out.println("In A.baz");
    }

    public void quux() {
        System.out.println("In A.quux");
        bar();
    }
}

class B extends A {
    public B() {
        super();
    }

    public void bar() {
        System.out.println("In B.bar");
    }

    public void baz() {
        System.out.println("In B.baz");
        super.baz();
    }

    public void qux() {
        System.out.println("In B.qux");
        this.baz();
    }
}

/*
 * The main method here contains some lines that assign references to objects
 * (some legal, some illegal) and invoke various methods in the A and B classes
 * to test understanding of the mechanics of inheritance.
 */
public class WhatsTheOutput {
    @SuppressWarnings("unused")
    public static void main(String[] args) {

        /*
         * Some legal and illegal assignments.
         */

        A a1 = new A();
        B b1 = new B();

        A a2 = b1;          // Legal
        B b2 = (B) a2;      // Legal
        //B b3 = a2;        // Illegal - just needs cast
        //B b4 = (B) a1;    // Illegal - runtime error
 
        /*
         * Some method calls.
         */

        System.out.println("a1.foo()");
        a1.foo();

        System.out.println();
        System.out.println("b1.foo()");
        b1.foo();

        System.out.println();
        System.out.println("a2.foo()");
        a2.foo();

        // a2.qux();        // Illegal

        System.out.println();
        System.out.println("a1.bar()");
        a1.bar();

        System.out.println();
        System.out.println("b1.bar()");
        b1.bar();

        System.out.println();
        System.out.println("a2.bar()");
        a2.bar();

        System.out.println();
        System.out.println("b1.baz()");
        b1.baz();

        System.out.println();
        System.out.println("a2.baz()");
        a2.baz();

        System.out.println();
        System.out.println("b1.qux()");
        b1.qux();
        
        System.out.println();
        System.out.println("a1.quux()");
        a1.quux();

        System.out.println();
        System.out.println("a2.quux()");
        a2.quux();
    }
}
