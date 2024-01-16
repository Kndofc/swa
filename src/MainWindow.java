import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class MainWindow extends JFrame {
    private TarefaRepository repositorio = new TarefaRepository();
    private DefaultListModel<Tarefa> modeloLista = new DefaultListModel<>();
    private JList<Tarefa> listaTarefas = new JList<>(modeloLista);
    private JTextField txtDescricao = new JTextField(20);
    private JTextField txtDataConclusao = new JTextField(10);
    private JButton btnAdicionar = new JButton("Adicionar");
    private JButton btnEditar = new JButton("Editar");
    private JButton btnExcluir = new JButton("Excluir");

    public MainWindow() {
        setTitle("Gerenciador de Tarefas");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JScrollPane(listaTarefas));
        add(new JLabel("Descrição:"));
        add(txtDescricao);
        add(new JLabel("Data de Conclusão (DD/MM/YYYY):"));
        add(txtDataConclusao);
        add(btnAdicionar);
        add(btnEditar);
        add(btnExcluir);

        configurarEventos();
        pack();
        setVisible(true);
    }

    private void configurarEventos() {
        btnAdicionar.addActionListener(e -> adicionarTarefa());
        btnEditar.addActionListener(e -> editarTarefa());
        btnExcluir.addActionListener(e -> excluirTarefa());
    }

    private void adicionarTarefa() {
        if (txtDescricao.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "A descrição da tarefa não pode estar vazia.");
            return;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(txtDataConclusao.getText(), formatter);
            Tarefa tarefa = new Tarefa(txtDescricao.getText(), data);
            repositorio.adicionarTarefa(tarefa);
            modeloLista.addElement(tarefa);
            limparCampos();
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Data inválida. Use o formato DD/MM/YYYY.");
        }
    }
    private void editarTarefa() {
        int selectedIndex = listaTarefas.getSelectedIndex();
        if (selectedIndex != -1) {
            if (txtDescricao.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "A descrição da tarefa não pode estar vazia.");
                return;
            }

            try {
                LocalDate data = LocalDate.parse(txtDataConclusao.getText());
                Tarefa tarefa = modeloLista.getElementAt(selectedIndex);
                tarefa.setDescricao(txtDescricao.getText());
                tarefa.setDataConclusao(data);
                listaTarefas.repaint();
                limparCampos();
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Data inválida. Use o formato YYYY-MM-DD.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhuma tarefa selecionada para editar.");
        }
    }

    private void excluirTarefa() {
        int selectedIndex = listaTarefas.getSelectedIndex();
        if (selectedIndex != -1) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Você realmente deseja excluir esta tarefa?",
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                Tarefa tarefa = modeloLista.getElementAt(selectedIndex);
                repositorio.removerTarefa(tarefa);
                modeloLista.removeElementAt(selectedIndex);
            }
        }
    }
    private void limparCampos() {
        txtDescricao.setText("");
        txtDataConclusao.setText("");
    }
    public static void main(String[] args) {
        new MainWindow();
    }
}
