import java.util.ArrayList;
import java.util.LinkedList;

class NossoGrafoCustomizado {
    private ArrayList<String> nodes;
    private ArrayList<LinkedList<String>> conexoes;

    public NossoGrafoCustomizado() {
        nodes = new ArrayList<>();
        conexoes = new ArrayList<>();
    }

    public void adicionarNode(String nomeDoNode) {
        nodes.add(nomeDoNode);
        conexoes.add(new LinkedList<>());
    }

    public void adicionarConexo(String origem, String destino) {
        int indiceDoNodeDeOrigem = nodes.indexOf(origem);
        int indiceDoNodeDeDestino = nodes.indexOf(destino);

        if (indiceDoNodeDeOrigem != -1 && indiceDoNodeDeDestino != -1) {
            conexoes.get(indiceDoNodeDeOrigem).add(destino);
            conexoes.get(indiceDoNodeDeDestino).add(origem);
        }
    }

    public void mostrarConexoesDoGrafo() {
        for (int i = 0; i < conexoes.size(); i++) {
            System.out.println("O nó chamado " + nodes.get(i) + " tem conexões: " + conexoes.get(i));
        }
    }
}

public class Main {
    public static void main(String[] args) {
        NossoGrafoCustomizado grafoDeCidades = new NossoGrafoCustomizado();

        grafoDeCidades.adicionarNode("Curitiba");
        grafoDeCidades.adicionarNode("Florianópolis");
        grafoDeCidades.adicionarNode("São Paulo");
        grafoDeCidades.adicionarNode("Rio de Janeiro");
        grafoDeCidades.adicionarNode("Vitória");
        grafoDeCidades.adicionarNode("Belo Horizonte");

        grafoDeCidades.adicionarConexo("Curitiba", "Florianópolis");
        grafoDeCidades.adicionarConexo("Curitiba", "São Paulo");
        grafoDeCidades.adicionarConexo("São Paulo", "Rio de Janeiro");
        grafoDeCidades.adicionarConexo("São Paulo", "Belo Horizonte");
        grafoDeCidades.adicionarConexo("Rio de Janeiro", "Vitória");

        grafoDeCidades.mostrarConexoesDoGrafo();
    }
}
