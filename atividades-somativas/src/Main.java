import java.util.Objects;
import java.util.*;

class Livro {
    private String titulo;
    private String autor;
    private String genero;

    public Livro(String titulo, String autor, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Livro)) return false;
        Livro livro = (Livro) o;
        return Objects.equals(titulo, livro.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo);
    }

    @Override
    public String toString() {
        return "\"" + titulo + "\" por " + autor + " [" + genero + "]";
    }
}

class GrafoDeLivros {
    private Map<Livro, Set<Livro>> grafo;

    public GrafoDeLivros() {
        grafo = new HashMap<>();
    }

    public void adicionarLivro(Livro livro) {
        grafo.putIfAbsent(livro, new HashSet<>());
    }

    public void adicionarRecomendacao(Livro livroOrigem, Livro livroDestino) {
        if (grafo.containsKey(livroOrigem) && grafo.containsKey(livroDestino)) {
            grafo.get(livroOrigem).add(livroDestino);
            grafo.get(livroDestino).add(livroOrigem); // Recomendação mútua
        }
    }

    public void mostrarConexoes() {
        for (Livro livro : grafo.keySet()) {
            System.out.println(livro + " recomenda:");
            for (Livro recomendado : grafo.get(livro)) {
                System.out.println(" → " + recomendado);
            }
        }
    }

    public Set<Livro> recomendarLivros(Livro livroLido) {
        return grafo.getOrDefault(livroLido, Collections.emptySet());
    }
}

public class Main {
    public static void main(String[] args) {
        GrafoDeLivros grafo = new GrafoDeLivros();

        Livro l1 = new Livro("1984", "George Orwell", "Distopia");
        Livro l2 = new Livro("Admirável Mundo Novo", "Aldous Huxley", "Distopia");
        Livro l3 = new Livro("Fahrenheit 451", "Ray Bradbury", "Distopia");
        Livro l4 = new Livro("Dom Casmurro", "Machado de Assis", "Romance");
        Livro l5 = new Livro("O Alienista", "Machado de Assis", "Satírico");
        Livro l6 = new Livro("Grande Sertão: Veredas", "Guimarães Rosa", "Romance");
        Livro l7 = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia");
        Livro l8 = new Livro("Harry Potter", "J.K. Rowling", "Fantasia");
        Livro l9 = new Livro("O Hobbit", "J.R.R. Tolkien", "Fantasia");
        Livro l10 = new Livro("A Revolução dos Bichos", "George Orwell", "Política");

        Livro[] livros = { l1, l2, l3, l4, l5, l6, l7, l8, l9, l10 };
        for (Livro l : livros) {
            grafo.adicionarLivro(l);
        }

        grafo.adicionarRecomendacao(l1, l2);
        grafo.adicionarRecomendacao(l1, l3);
        grafo.adicionarRecomendacao(l2, l10);
        grafo.adicionarRecomendacao(l3, l10);
        grafo.adicionarRecomendacao(l4, l5);
        grafo.adicionarRecomendacao(l4, l6);
        grafo.adicionarRecomendacao(l5, l6);
        grafo.adicionarRecomendacao(l7, l8);
        grafo.adicionarRecomendacao(l7, l9);
        grafo.adicionarRecomendacao(l8, l9);

        grafo.mostrarConexoes();

        System.out.println("Recomendado por quem leu: " + l1.getTitulo());
        for (Livro recomendado : grafo.recomendarLivros(l1)) {
            System.out.println(" - " + recomendado);
        }
    }
}
