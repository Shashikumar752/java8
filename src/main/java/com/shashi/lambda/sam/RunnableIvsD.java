package com.shashi.lambda.sam;


public class RunnableIvsD {

    public static void main(String[] args) {
        // impertive style of runnable implementation
        Runnable runI = new Runnable() {
            @Override
            public void run() {
                System.out.println("i am running in imperative way");
            }
        };
        Thread tI = new Thread(runI);
        tI.start();

        // declarative way 1
        Thread tD1 = new Thread(() -> {
            System.out.println("i am running in declarative way 1");
        });
        tD1.start();

        // declarative way 2
        Thread tD2 = new Thread(() -> System.out.println("i am running in declarative way 2"));
        tD2.start();

        // declarative way 2
        Runnable rL = () -> System.out.println("i am running in declarative way 3");
        rL.run();
        // Thread tD3 = new Thread(rL);
        // tD3.start();
        runI.run();

    }



}
