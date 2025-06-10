import javax.swing.*;
import java.awt.*;

public class Theme {

    public static final Color DARK_PURPLE = new Color(0x25224D);
    public static final Color PURPLE = new Color(0x4F409C);
    public static final Color LAVENDER = new Color(0x9B86B9);
    public static final Color GOLD = new Color(0xF9C73F);
    public static final Color BLACK = new Color(0x000000);

    public static void styleButton(JButton button) {
        button.setBackground(LAVENDER);
        button.setForeground(BLACK);
        button.setBorder(BorderFactory.createLineBorder(PURPLE));
        button.setFocusPainted(false);
    }

    public static void styleLabel(JLabel label) {
        label.setForeground(GOLD);
        label.setFont(new Font("Serif", Font.BOLD, 14));
    }

    public static void stylePanel(JPanel panel) {
        panel.setBackground(PURPLE);
    }

    public static void styleList(JList<?> list) {
        list.setBackground(LAVENDER);
        list.setForeground(BLACK);
        list.setSelectionBackground(GOLD);
        list.setSelectionForeground(BLACK);
    }

    public static void styleDialog() {
        UIManager.put("OptionPane.background", PURPLE);
        UIManager.put("Panel.background", PURPLE);
        UIManager.put("OptionPane.messageForeground", GOLD);
        UIManager.put("Button.background", LAVENDER);
        UIManager.put("Button.foreground", BLACK);
        UIManager.put("Button.focus", PURPLE);
    }

    public static void styleTextField(JTextField field) {
        field.setBackground(LAVENDER);
        field.setForeground(BLACK);
        field.setCaretColor(BLACK);
        field.setBorder(BorderFactory.createLineBorder(PURPLE));
    }
    public static void styleTextArea(JTextArea field) {
        field.setBackground(LAVENDER);
        field.setForeground(BLACK);
        field.setCaretColor(BLACK);
        field.setBorder(BorderFactory.createLineBorder(PURPLE));
    }
    public static void styleBar(JProgressBar progressBar) {
        progressBar.setBackground(DARK_PURPLE);
        progressBar.setForeground(LAVENDER);
        progressBar.setBorder(BorderFactory.createLineBorder(PURPLE));

    }

    public static void styleComboBox(JComboBox field) {
        field.setBackground(LAVENDER);
        field.setForeground(BLACK);

        field.setBorder(BorderFactory.createLineBorder(PURPLE));
    }

    public static void styleScroll(JScrollPane scrollPane) {
        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setBackground(PURPLE);
        vertical.setForeground(LAVENDER);
        vertical.getComponent(0).setBackground(LAVENDER);
        vertical.getComponent(1).setBackground(LAVENDER);
    }
}

