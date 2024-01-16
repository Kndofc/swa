import java.util.ArrayList;
import java.util.List;

public class TarefaRepository {
    private List<Tarefa> tarefas;

    public TarefaRepository() {
        tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void atualizarTarefa(int index, Tarefa tarefa) {
        tarefas.set(index, tarefa);
    }

    public void removerTarefa(Tarefa tarefa) {
        tarefas.remove(tarefa);
    }
}
