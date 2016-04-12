package Gui;

import entidades.Fabricante;
import entidades.Medicamento;
import excecoes.JaExisteException;
import fabricas.FabricaMedicamento;
import interfaces.SisBula;
import logica.SisBulaMemory;

import javax.swing.*;
import java.awt.event.*;

public class MainGui extends JDialog {

    SisBula sis = new SisBulaMemory();

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JList list1;

    public MainGui() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        sis.carregarGerente();

        try {
            list1.setListData(sis.getListMedicamento().toArray());
        } catch (Exception e) {}

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                sis.deletarGerenteGravado();
                sis.gravarGerente();
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        Medicamento m = FabricaMedicamento.getMedicamento(textField1.getText(), Fabricante.SemFabricante);
        try {
            sis.cadastrarMedicamento(m);
        } catch (JaExisteException e) {

        }
    }

    private void onCancel() {
        textField1.setText("");
    }

    public static void main(String[] args) {
        MainGui dialog = new MainGui();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
