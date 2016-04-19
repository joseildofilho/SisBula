package Gui;

import entidades.Fabricante;
import entidades.Medicamento;
import entidades.Sintoma;
import excecoes.JaExisteException;
import fabrica.Fabrica;
import interfaces.SisBula;
import logica.SisBulaFacade;

import javax.swing.*;
import java.awt.event.*;

public class MainGui extends JDialog {

    private SisBula sis = new SisBulaFacade();

    private JPanel contentPane;
    private JButton botaoMedicamento;
    private JButton botaoClearMedicamento;
    private JTextField campoMedicamento;
    private JList medicamentos;
    private JButton botaoSintoma;
    private JButton botaoClearSintoma;
    private JTextField campoSintoma;
    private JList sintomas;
    private JList doencas;

    public MainGui() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(botaoMedicamento);

        sis.carregarTodos();

        prepareList();

        medicamentos.setListData(sis.getListMedicamento().toArray());
        sintomas.setListData(sis.getTodosSintomas().toArray());
        doencas.setListData(sis.getTodasDoencas().toArray());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sis.gravarTodos();
            }
        });

        contentPane.registerKeyboardAction(e -> onClearMedicamento(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


    }

    private void onOKMedicamento() {
        Medicamento m = Fabrica.getMedicamento(campoMedicamento.getText(), Fabricante.SemFabricante);
        try {
            sis.cadastrarMedicamento(m);
        } catch (JaExisteException e) {
            //TODO colocar um alerta de cadastro
        }
    }

    private void onOKSintoma() {
        Sintoma s = Fabrica.getSintoma(campoSintoma.getText());
        try {
            sis.cadastrarSintoma(s);
        } catch (JaExisteException e) {
            //TODO colocar um alerta de cadastro
        }

    }

    private void updateListaMedicamento() {
        medicamentos.setListData(sis.getListMedicamento().toArray());
        medicamentos.repaint();
    }

    private void updateListaSintoma() {
        sintomas.setListData(sis.getTodosSintomas().toArray());
        sintomas.repaint();
    }

    private void onClearMedicamento() {
        campoMedicamento.setText("");
    }

    private void onClearSintoma() {
        campoSintoma.setText("");
    }

    private void prepareList() {
        botaoMedicamento.addActionListener(e -> {
            onOKMedicamento();
            updateListaMedicamento();
        });

        botaoClearMedicamento.addActionListener(e -> onClearMedicamento());

        botaoSintoma.addActionListener(e -> {
            onOKSintoma();
            updateListaSintoma();
        });

        botaoClearSintoma.addActionListener(e -> onClearSintoma());
    }

    public static void main(String[] args) {
        MainGui dialog = new MainGui();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
