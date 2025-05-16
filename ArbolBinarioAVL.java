
public class ArbolBinarioAVL {
    NodoArbol raiz; // Nodo raíz del árbol

    // obtener la altura del un nodo
    private int obtenerAlturaNodo(NodoArbol nodo) {
        return (nodo == null) ? 0 : nodo.altura;
    }

    // Obtener el equilibrio de un nodo
    private int obtenerFactorBalance(NodoArbol nodo) {
        return (nodo == null) ? 0 : obtenerAlturaNodo(nodo.hijoIzquierdo) - obtenerAlturaNodo(nodo.hijoDerecho);
    }

    // Actualizar la altura
    private void actualizarAlturaNodo(NodoArbol nodo) {
        nodo.altura = 1 + Math.max(obtenerAlturaNodo(nodo.hijoIzquierdo), obtenerAlturaNodo(nodo.hijoDerecho));
    }

    // Rotación simple a la derecha
    private NodoArbol rotarDerecha(NodoArbol nodoDesbalanceado) {
        NodoArbol nuevoPadre = nodoDesbalanceado.hijoIzquierdo;
        NodoArbol subArbolIntermedio = nuevoPadre.hijoDerecho;

        // Realizar la rotación
        nuevoPadre.hijoDerecho = nodoDesbalanceado;
        nodoDesbalanceado.hijoIzquierdo = subArbolIntermedio;

        // actualizar alturas
        actualizarAlturaNodo(nodoDesbalanceado);
        actualizarAlturaNodo(nuevoPadre);

        return nuevoPadre;
    }

    // Rotación simple a la izquierda
    private NodoArbol rotarIzquierda(NodoArbol nodoDesbalanceado) {
        NodoArbol nuevoPadre = nodoDesbalanceado.hijoDerecho;
        NodoArbol subArbolIntermedio = nuevoPadre.hijoIzquierdo;

        // Realizar la rotación
        nuevoPadre.hijoIzquierdo = nodoDesbalanceado;
        nodoDesbalanceado.hijoDerecho = subArbolIntermedio;

        // Actualizar alturas
        actualizarAlturaNodo(nodoDesbalanceado);
        actualizarAlturaNodo(nuevoPadre);

        return nuevoPadre;
    }

    // Insertar un nuevo valor en el arbol AVL
    private NodoArbol insertarNodo(NodoArbol nodo, int valor) {
        if (nodo == null) {
            return new NodoArbol(valor);
        }

        if (valor < nodo.valor) {
            nodo.hijoIzquierdo = insertarNodo(nodo.hijoIzquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.hijoDerecho = insertarNodo(nodo.hijoDerecho, valor);
        } else {
            // no permitir valores duplicados
            return nodo;
        }

        // Actualizar la altura del nodo actual
        actualizarAlturaNodo(nodo);

        // Obtener el equilibrio - rotaciones si es necesario
        int factorEquilibrio = obtenerFactorBalance(nodo);

        // Casos de desequilibrio
        if (factorEquilibrio > 1 && valor < nodo.hijoIzquierdo.valor) {
            return rotarDerecha(nodo);
        }
        if (factorEquilibrio < -1 && valor > nodo.hijoDerecho.valor) {
            return rotarIzquierda(nodo);
        }
        if (factorEquilibrio > 1 && valor > nodo.hijoIzquierdo.valor) {
            nodo.hijoIzquierdo = rotarIzquierda(nodo.hijoIzquierdo);
            return rotarDerecha(nodo);
        }
        if (factorEquilibrio < -1 && valor < nodo.hijoDerecho.valor) {
            nodo.hijoDerecho = rotarDerecha(nodo.hijoDerecho);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    public void insertar(int valor) {
        raiz = insertarNodo(raiz, valor);
        imprimirArbol(raiz, "", true);
    }

    // Encontrar el nodo con el valor mínimo
    private NodoArbol encontrarMinimo(NodoArbol nodo) {
        while (nodo.hijoIzquierdo != null) {
            nodo = nodo.hijoIzquierdo;
        }
        return nodo;
    }

    // Eliminar un nodo del árbol AVL
    private NodoArbol eliminarNodo(NodoArbol nodo, int valor) {
        if (nodo == null) {
            return null;
        }

        if (valor < nodo.valor) {
            nodo.hijoIzquierdo = eliminarNodo(nodo.hijoIzquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.hijoDerecho = eliminarNodo(nodo.hijoDerecho, valor);
        } else {
            // Nodo encontrado
            if ((nodo.hijoIzquierdo == null) || (nodo.hijoDerecho == null)) {
                nodo = (nodo.hijoIzquierdo != null) ? nodo.hijoIzquierdo : nodo.hijoDerecho;
            } else {
                NodoArbol temp = encontrarMinimo(nodo.hijoDerecho);
                nodo.valor = temp.valor;
                nodo.hijoDerecho = eliminarNodo(nodo.hijoDerecho, temp.valor);
            }
        }

        if (nodo == null) {
            return null;
        }

        // Actualizar la altura y balancear
        actualizarAlturaNodo(nodo);
        int factorEquilibrio = obtenerFactorBalance(nodo);

        
        if (factorEquilibrio > 1 && obtenerFactorBalance(nodo.hijoIzquierdo) >= 0)
            return rotarDerecha(nodo);
        if (factorEquilibrio > 1 && obtenerFactorBalance(nodo.hijoIzquierdo) < 0) {
            nodo.hijoIzquierdo = rotarIzquierda(nodo.hijoIzquierdo);
            return rotarDerecha(nodo);
        }
        if (factorEquilibrio < -1 && obtenerFactorBalance(nodo.hijoDerecho) <= 0)
            return rotarIzquierda(nodo);
        if (factorEquilibrio < -1 && obtenerFactorBalance(nodo.hijoDerecho) > 0) {
            nodo.hijoDerecho = rotarDerecha(nodo.hijoDerecho);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    public void eliminar(int valor) {
        raiz = eliminarNodo(raiz, valor);
        System.out.println("\nÁrbol AVL después de eliminar " + valor + ":");
        imprimirArbol(raiz, "", true);
    }

    // Reiniciar el Arbol
    public void reiniciar() {
        raiz = null;
        System.out.println("\nEl árbol AVL ha sido reiniciado.");
    }

    // Imprimir el arbol en lz consola
    private void imprimirArbol(NodoArbol nodo, String indent, boolean esUltimo) {
        if (nodo != null) {
            System.out.print(indent);
            if (esUltimo) {
                System.out.print("└─");
                indent += "  ";
            } else {
                System.out.print("├─");
                indent += "| ";
            }
            System.out.println(nodo.valor);

            imprimirArbol(nodo.hijoIzquierdo, indent, false);
            imprimirArbol(nodo.hijoDerecho, indent, true);
        }
    }

    // Mostrar el arbol completo
    public void mostrarArbolCompleto() {
        System.out.println("\nÁrbol AVL Completo:");
        imprimirArbol(raiz, "", true);
    }
}