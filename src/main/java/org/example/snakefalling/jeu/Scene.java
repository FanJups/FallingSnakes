package org.example.snakefalling.jeu;

import org.example.snakefalling.entites.*;
import org.example.snakefalling.rsc.Chrono;
import org.example.snakefalling.rsc.Clavier;
import org.example.snakefalling.rsc.Constantes;

import javax.swing.*;
import java.awt.*;

public class Scene extends JPanel {

    public Vaisseau vaisseau = new Vaisseau();
    public Fraise fraise = new Fraise();
    public Myrtille myrtille = new Myrtille();
    public Bois bois1 = new Bois();
    public Bois bois2 = new Bois();
    public Bois bois3 = new Bois();
    public Piecedor piece = new Piecedor();
    public Tir tir = new Tir();

    public Serpent serpent = new Serpent();


    public Scene() {
        super();
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new Clavier());
        Thread chrono = new Thread(new Chrono());
        chrono.start();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = g;

        g2.setColor(Color.decode("#737373"));
        //Background
        g2.fillRect(0, 0, Constantes.LARGEUR_FENETRE, Constantes.HAUTEUR_FENETRE);
        g2.setColor(Color.white);
        g2.fillRect(0, 530, Constantes.LARGEUR_FENETRE, 5);


        g2.drawImage(this.vaisseau.getImage(), this.vaisseau.deplacementVaisseau(), this.vaisseau.getyPos(), null);


        /////////

        /*
         * 12 * 100
         *
         * 3 * 100
         *
         * 0 x 1100
         * 0 y 200
         *
         * */

        //ICI

        dessinSurfaceJeu(g2);


        ///////


        if (Chrono.compteTours % 2 == 0)
            this.serpent.deplacementSerpent(g2);


        // g2.drawImage(this.fraise.getImage(),  this.fraise.getxFraise(),  this.fraise.getyFraise(), null);
        //g2.drawImage(this.fraise.getImage(), 120, 30, null);

        g2.drawImage(this.fraise.getImage(), 1100, 100, null);

        // g2.drawImage(this.myrtille.getImage(),  this.myrtille.getxMyrtille(),  this.myrtille.getyMyrtille(), null);
        //g2.drawImage(this.myrtille.getImage(), 300, 240, null);
        g2.drawImage(this.myrtille.getImage(), 300, 200, null);


        g2.drawImage(this.bois1.getImage(), 1100, 0, null);
        // g2.drawImage(this.bois2.getImage(), 70, 140, null);
        //  g2.drawImage(this.bois3.getImage(), 230, 90, null);

        g2.drawImage(this.bois2.getImage(), 1000, 0, null);
        g2.drawImage(this.bois3.getImage(), 900, 0, null);

        g2.drawImage(new Bois().getImage(), 0, 200, null);
        // g2.drawImage(this.bois2.getImage(), 70, 140, null);
        //  g2.drawImage(this.bois3.getImage(), 230, 90, null);

        g2.drawImage(new Bois().getImage(), 100, 200, null);
        g2.drawImage(new Bois().getImage(), 200, 200, null);

        g2.drawImage(new Bois().getImage(), 1100, 200, null);


        g2.drawImage(this.piece.getImage(), 400, 0, null);
        //  g2.drawImage(this.piece.getImage(), 400, 220, null);


        tir.dessinTir(g2, this.serpent);


        //this.serpent.diminuerTaille(tir,g2);
    }

    public Color couleurRectangle(Graphics g2, int i, int j) {

        if ((i + j) % 2 == 0) {
            g2.setColor(Color.decode("#D9D9D9"));
        } else {
            g2.setColor(Color.decode("#A6A6A6"));
        }

        return g2.getColor();

    }

    private void dessinSurfaceJeu(Graphics g2) {

        int x = 0, y = 0;

        for (int i = 0; i <= 11; i++) {

            for (int j = 0; j <= 2; j++) {

                couleurRectangle(g2, i, j);

                g2.fillRect(x, y + (100 * j), 100, 100);

            }

            x = x + 100;

        }


    }
}
