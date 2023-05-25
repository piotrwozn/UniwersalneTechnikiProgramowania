package zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class GUI extends JFrame {
    private List<Baza> dane;
    private Podglad podglad;
    private static String[] jezyki = {"pl", "en"};

    GUI(List<Baza> dane) {
        this.dane = dane;

        setPreferredSize(new Dimension(800, 600));
        setTitle("Projekt UTP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        podglad = new Podglad(this);
        setContentPane(podglad);
        pack();
        setVisible(true);
    }


    private class Podglad extends JPanel implements ActionListener {

        GUI gui;

        List<JButton> jButtons = new ArrayList<>();

        Podglad(GUI gui) {
            this.gui = gui;
            int x = 0;

            for (String jezyk : GUI.jezyki) {
                JButton przycisk = new JButton(jezyk);
                przycisk.setActionCommand(jezyk);
                przycisk.addActionListener(this);
                jButtons.add(x, przycisk);
                this.add(jButtons.get(x++));
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            gui.wyborJezyka(e.getActionCommand());

            for (JButton jButton : jButtons) {
                this.remove(jButton);
                jButton.setEnabled(false);
            }
        }
    }

    private List<Baza> getDane() {
        return dane;
    }

    private String jezyk;

    private void wyborJezyka(String akcja) {
        jezyk = akcja;
        String[] kolumny;

        if (jezyk == "pl") {
            kolumny = new String[] {
                    "lokalizacja",
                    "kraj",
                    "dataOd",
                    "dataDo",
                    "miejsce",
                    "cena",
                    "waluta"
            };
        } else {
            kolumny = new String[] {
                    "localization",
                    "country",
                    "dateFrom",
                    "dateTo",
                    "place",
                    "price",
                    "currency"
            };
        }


        Object[][] dane2 = new Object[getDane().size()][];
        for (int i = 0; i < getDane().size(); i++) {
            Baza wiersz = getDane().get(i);
            dane2[i] = wiersz.Lista();
        }

        JTable tabela = new JTable(dane2, kolumny);

        Dimension rozdzielczosc = Toolkit.getDefaultToolkit().getScreenSize();
        tabela.setPreferredSize(new Dimension(800, 600));
        tabela.setLocation(rozdzielczosc.width / 2 - 400, rozdzielczosc.height / 2 - 300);

        remove(podglad);
        add(new JScrollPane(tabela));

        revalidate();
        repaint();
    }
}