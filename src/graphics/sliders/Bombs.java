package graphics.sliders;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Bombs extends JDialog {
    public JSlider slider;
    private JLabel label;
    private int min = 1;
    public int max = 5;
    private int def = (min + max) / 2;

    public Bombs() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(600, 300);
        setTitle("кол-во бомб");
        setSize(300, 100);
        setLayout(new FlowLayout());
        int myValue = def;
        label = new JLabel("Установить количество бомб на : " + myValue);
        add(label);

        slider = new JSlider(JSlider.HORIZONTAL, min, max, def);
        slider.setMajorTickSpacing(2);
        slider.setPaintTicks(true);
        add(slider);

        JButton btn = new JButton("OK");
        btn.addActionListener(e -> dispose());
        add(btn);

        Bombs.event e = new Bombs.event();
        slider.addChangeListener(e);
    }

    public void reload() {
        slider.setMaximum(max);
        slider.setMinimum(min);
    }

    public class event implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            int value = slider.getValue();
            label.setText("Установить количество бомб на : " + value);
        }
    }
}