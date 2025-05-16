# Árbol AVL en Java - Implementación

*Proyecto de implementación de un árbol AVL en Java. 


## ¿Cómo Ejecutar?

1.  **Necesitas Java:** Asegúrate de tener Java instalado en tu computadora. Abre la terminal y escribe `java -version` para ver si funciona.
2.  **Compilar:** Ve a la carpeta donde guardaste los archivos `.java` y escribe esto en la terminal:
    bash
    javac NodoArbol.java ArbolBinarioAVL.java InterfazArbolAVL.java
   
3.  **Ejecutar:** Luego, escribe esto para que empiece el programa:
    bash
    java InterfazArbolAVL


## Qué Hace Cada Parte del Código?

* **`NodoArbol.java`**: Es como el "ladrillo" del árbol. Guarda el valor, qué tan alto está el nodo (`altura`), y quiénes son sus hijos (izquierda y derecha).
* **`ArbolBinarioAVL.java`**: ¡Aquí está la magia! Tiene todo el código para que el árbol funcione:
    * Cómo agregar (`insertar`) y quitar (`eliminar`) valores.
    * Las "rotaciones" (`rotarDerecha`, `rotarIzquierda`) que hacen que el árbol siempre esté balanceado.
    * Cómo mostrar el árbol en la pantalla (`imprimirArbol`).
    * Cómo empezar de cero (`reiniciar`).
* **`InterfazArbolAVL.java`**: Es la parte con la que tú hablas. Te deja escribir comandos como `insertar 10`, `eliminar 5`, `mostrar` o `reiniciar` en la terminal para usar el árbol.

## Ejemplos Rápidos

Si escribes `i 10`, el árbol se ve algo así:


└─10


Si luego escribes `i 5`, podría verse así:


└─10
    └─5


Si el árbol se "desbalancea" al agregar o quitar cosas, verás cómo se reordena automáticamente. Prueba a insertar números en orden (como 10, 25, 32. etc) para ver cómo el AVL se balancea con las rotaciones.

Espero ayude a entender cómo funciona un arbol AVL