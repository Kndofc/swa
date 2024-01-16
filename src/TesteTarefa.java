import java.time.LocalDate;

public class TesteTarefa {
    public static void main(String[] args) {
        TarefaRepository repositorio = new TarefaRepository();

        Tarefa tarefa1 = new Tarefa("Aprender Java", LocalDate.now());
        repositorio.adicionarTarefa(tarefa1);

        System.out.println("Tarefas após adição:");
        for (Tarefa tarefa : repositorio.getTarefas()) {
            System.out.println(tarefa);
        }

        tarefa1.setDescricao("Aprender Java Avançado");
        tarefa1.setDataConclusao(LocalDate.now().plusDays(7));

        System.out.println("Tarefas após atualização:");
        for (Tarefa tarefa : repositorio.getTarefas()) {
            System.out.println(tarefa);
        }

        repositorio.removerTarefa(tarefa1);

        System.out.println("Tarefas após deleção:");
        for (Tarefa tarefa : repositorio.getTarefas()) {
            System.out.println(tarefa);
        }
    }
}

