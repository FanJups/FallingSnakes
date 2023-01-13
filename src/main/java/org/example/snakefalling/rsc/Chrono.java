package org.example.snakefalling.rsc;


import org.example.snakefalling.jeu.Main;

public class Chrono implements Runnable {
    public static int compteTours = 0;
    private final int pause = 2500;

    @Override
    public void run() {
        while (true) {
            compteTours++;
            System.out.println(compteTours);
            Main.scene.repaint();
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
            }
        }
    }
}
