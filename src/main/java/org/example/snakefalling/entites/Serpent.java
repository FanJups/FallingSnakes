package org.example.snakefalling.entites;

import org.example.snakefalling.rsc.Chrono;
import org.example.snakefalling.rsc.Constantes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Serpent {

    boolean changementligne=true;

    private static final int ELTS_MAX = 2;
    private static final int ELTS_MIN = 2;
    private final Cercle teteSerpent;
    private final List<Cercle> elementsCorpsSerpent = new ArrayList<>();

    public Serpent() {

        teteSerpent = new Cercle(Couleur.BLEU, 200, 0);

        for (int i = 0; i < ELTS_MIN; i++) {

            int minus = 100 * (i + 1);

            elementsCorpsSerpent.add(new Cercle(Couleur.JAUNE, teteSerpent.getxPos() - minus, teteSerpent.getyPos()));

        }
    }

    public Serpent augementerTaille() {
        int s = this.elementsCorpsSerpent.size();

        if (s < ELTS_MAX){

            Cercle dernierePartie = elementsCorpsSerpent.get(s - 1);

            elementsCorpsSerpent.add(new Cercle(Couleur.JAUNE, dernierePartie.getxPos() - 100, dernierePartie.getyPos()));


        }



        return this;
    }



    public Serpent diminuerTaille(Tir tir,Graphics g) {

        Serpent serpent = null;

        if (tir.getxPos() == this.teteSerpent.getxPos() && tir.getyPos() == this.teteSerpent.getyPos()) {



          gameOver(g);


            return null;
        } else {

            if (this.elementsCorpsSerpent.isEmpty()) {

                serpent = this;

            } else {

                //Java 8 stream
                // Java 8 method reference

                this.elementsCorpsSerpent.stream()
                        .filter(c -> tir.getxPos() == c.getxPos() && tir.getyPos() == c.getyPos())
                        .forEach(c -> {effacer(c.getxPos(),c.getyPos(),g);});

                this.elementsCorpsSerpent.stream()
                        .filter(c -> tir.getxPos() == c.getxPos() && tir.getyPos() == c.getyPos())
                        .forEach(new ArrayList<Cercle>()::remove);

                serpent = this;


            }

        }

        return serpent;
    }

    public void dessinSerpent(Graphics g) {

        Objects.requireNonNull(this);
        Objects.requireNonNull(this.teteSerpent);
        g.drawImage(this.teteSerpent.getImage(), this.teteSerpent.getxPos(), this.teteSerpent.getyPos(), null);

        if (!this.elementsCorpsSerpent.isEmpty())

            for (int i = 0; i < this.elementsCorpsSerpent.size(); i++)
                g.drawImage(this.elementsCorpsSerpent.get(i).getImage(), this.elementsCorpsSerpent.get(i).getxPos(), this.elementsCorpsSerpent.get(i).getyPos(), null);


    }

    public  void deplacementUniteCorps(Cercle c, Graphics g){

        Objects.requireNonNull(c);


       // effacer(c.getxPos(),c.getyPos(),g);

        g.drawImage(c.getImage(), c.getxPos(), c.getyPos(), null);


        int x= c.getxPos();

        int y= c.getyPos();

        int xPar100= x/100;

        int yPar100= y/100;

        xPar100 = xPar100 % 12;

        yPar100 = yPar100 % 3;

        c.setxPos((xPar100 +1)*100);


    }

    public void deplacementSerpent(Graphics g) {


        deplacementUniteCorps(teteSerpent, g);


        if (!this.elementsCorpsSerpent.isEmpty())

            for (int i = 0; i < this.elementsCorpsSerpent.size(); i++)
                deplacementUniteCorps(this.elementsCorpsSerpent.get(i), g);



    }


    public Cercle getTeteSerpent() {
        return teteSerpent;
    }

    public List<Cercle> getElementsCorpsSerpent() {
        return elementsCorpsSerpent;
    }

    public void effacer(int xPos,int yPos,Graphics g){

        int x= xPos/100;

        int y= yPos/100;

        if((x+y)%2==0){
            g.setColor(Color.decode("#D9D9D9"));
        }else{
            g.setColor(Color.decode("#A6A6A6"));
        }

        g.fillRect(xPos, yPos, 100 , 100);



    }

    private void gameOver(Graphics g) {
        String message = "You Win";
        Font small = new Font("Helvetice", Font.BOLD, 15);
        //FontMetrics met = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (Constantes.LARGEUR_FENETRE - stringWidth(message)) / 2, Constantes.HAUTEUR_FENETRE/ 2);
    }

    private int stringWidth(String str) {
        int len = str.length();
        char[] data = new char[len];
        str.getChars(0, len, data, 0);
        return charsWidth(data, 0, len);
    }

    private int charsWidth(char[] data, int off, int len) {
        return stringWidth(new String(data, off, len));
    }


}
