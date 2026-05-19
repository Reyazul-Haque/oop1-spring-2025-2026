import gui.ResidentGUI;

public class Start {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {

            new ResidentGUI().setVisible(true);
        });
    }
}