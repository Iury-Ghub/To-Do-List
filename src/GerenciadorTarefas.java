import java.io.*;
import java.nio.file.*; // Para o NIO.2 (Log)
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors; // Você pode precisar disso

public class GerenciadorTarefas {

    // 1. COLLECTIONS
    private List<Tarefa> tarefas;
    private static final String ARQUIVO_SERIALIZADO = "tarefas.ser";
    private static final Path ARQUIVO_LOG = Paths.get("log.txt");

    public GerenciadorTarefas() {
        this.tarefas = new ArrayList<>();
        carregarTarefas(); // Tenta carregar dados ao iniciar
    }

    // --- MÉTODOS PARA VOCÊ IMPLEMENTAR ---

    public void adicionarTarefa(String descricao, int prioridade) {
        // 1. Crie uma nova Tarefa
        Tarefa tarefa = new Tarefa(descricao, prioridade);
        // 2. Adicione na lista 'tarefas'
        tarefas.add(tarefa);
        // 3. Chame o método logarAcao("Tarefa adicionada: " + descricao)
        logarAcao("Tarefa adicionada: "+ descricao);
    }

    public void listarTarefas() {
        System.out.println("--- Lista de Tarefas ---");
        // 2. LAMBDAS (forEach)
        // Use o método .forEach() da lista para imprimir cada tarefa
        // Ex: tarefas.forEach( ... );
        tarefas.forEach(tarefa -> System.out.println(tarefa));
        System.out.println("------------------------");
    }

    public void marcarConcluida(String descricaoParcial) {
        tarefas.stream()
                .filter(t -> t.getDescricao().contains(descricaoParcial)) // Filtra
                .findFirst() // Pega o primeiro
                .ifPresent(tarefa -> { // 'tarefa' é o objeto encontrado

                    // Se encontrar (.ifPresent(...)), marque-a como concluída
                    tarefa.setConcluida(true);

                    // Logue a ação
                    System.out.println("Tarefa concluída: " + tarefa.getDescricao()); // Avisa o usuário no console
                    logarAcao("Marcada como concluída: " + tarefa.getDescricao()); // Salva no log.txt
                });
    }

    public void removerConcluidas() {
        // 4. LAMBDAS (removeIf)
        // Esta é a forma mais fácil de remover itens de uma coleção
        // Dica: tarefas.removeIf( ... );
        List<String> descricoesRemovidas = tarefas.stream()
                .filter(tarefa -> tarefa.isConcluida())
                .map(tarefa -> tarefa.getDescricao())
                .collect(Collectors.toList());

        boolean algoFoiRemovido = tarefas.removeIf(tarefa -> tarefa.isConcluida());

        if(algoFoiRemovido){
            logarAcao("Tarefas concluídas removidas: " + descricoesRemovidas);
        }
        // Use uma expressão lambda que retorne 'true' se a tarefa está concluída
        // Logue a ação

    }

    // --- MÉTODOS DE ARQUIVO (JÁ PRONTOS PARA VOCÊ ESTUDAR) ---

    // 5. SERIALIZAÇÃO (Salvando)
    public void salvarTarefas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_SERIALIZADO))) {
            oos.writeObject(tarefas); // Salva a lista inteira!
            logarAcao("Tarefas salvas no arquivo.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    // 6. SERIALIZAÇÃO (Carregando)
    @SuppressWarnings("unchecked") // Suprime o aviso de cast
    public void carregarTarefas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_SERIALIZADO))) {
            tarefas = (List<Tarefa>) ois.readObject(); // Lê a lista inteira!
            logarAcao("Tarefas carregadas do arquivo.");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de tarefas não encontrado. Iniciando com lista vazia.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
        }
    }

    // 7. NIO.2 (Escrita de Log)
    private void logarAcao(String acao) {
        try {
            // StandardOpenOption.APPEND -> Adiciona no fim do arquivo
            // StandardOpenOption.CREATE -> Cria o arquivo se não existir
            Files.writeString(ARQUIVO_LOG, acao + "\n",
                    StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Erro ao escrever log: " + e.getMessage());
        }
    }
}