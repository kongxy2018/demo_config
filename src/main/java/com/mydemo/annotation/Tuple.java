package com.mydemo.annotation;

public class Tuple<A, B> {
    public final A a;
    public final B b;

    public Tuple(A a, B b) {
        this.a = a;
        this.b = b;
    }


    public static void main(String[] args) {
        Tuple<Integer, String> objectObjectTuple = new Tuple<Integer, String>(1, "str");

        System.out.println(objectObjectTuple.a + objectObjectTuple.b);
    }

    static class Test {

        public Tuple<Double, Long> test() {
            Double dou = 11.11;
            Long l = 11L;
            Tuple<Double, Long> doubleLongTuple = new Tuple<>(dou, l);
            return doubleLongTuple;
        }

        public static void main(String[] args) {
            Test test = new Test();
            Tuple<Double, Long> test1 = test.test();

            System.out.println(test1.a);
            System.out.println(test1.b);


        }

    }
}
