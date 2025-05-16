
public class NodoArbol {
    int valor; // Valor del nodo
    int altura; // Altura del nodo esto para balancear
    NodoArbol hijoIzquierdo; // Referencia al hijo izquierdo
    NodoArbol hijoDerecho; // Referencia al hijo derecho

    // Constructor del nodo con valor inicial
    public NodoArbol(int valor) {
        this.valor = valor;
        this.altura = 1; // La altura inicial de un nodo nuevo es 1
    }
}