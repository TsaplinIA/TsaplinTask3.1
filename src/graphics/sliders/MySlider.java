package graphics.sliders;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MySlider extends JDialog {
    public JSlider slider;
    private JLabel label;
    private JButton btn;
    private int min = 3;
    private int max = 10;
    private int def = (min + max) / 2;
    private int myValue = def;

    public MySlider() {
        setTitle("выберите размер");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(600, 300);
        setSize(300, 100);
        setLayout(new FlowLayout());
        label = new JLabel("Установить размеры поля: " + myValue + "x" + myValue);
        add(label);

        slider = new JSlider(JSlider.HORIZONTAL, min, max, def);
        slider.setMajorTickSpacing(2);
        slider.setPaintTicks(true);
        add(slider);

        btn = new JButton("OK");
        btn.addActionListener(e -> dispose());
        add(btn);

        event e = new event();
        slider.addChangeListener(e);
    }

    public class event implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            int value = slider.getValue();
            label.setText("Установить размеры поля:" + value + "x" + value);
        }
    }
}