package huffman;

/**
 *
 * @author luana
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Algoritmo de Huffman ");
        while (true) {
            System.out.println("Use a palavra 'texto' para codificar e decodificar o texto de exemplo");
            String texto = in.nextLine().trim();

            if (texto.equalsIgnoreCase("texto")) {
                texto =
                        "A codificação de Huffman é um método de compressão que usa as probabilidades\n" + 
                        " de ocorrência dos símbolos no conjunto de dados a ser comprimido para determinar\n" +
                        "códigos de tamanho variável para cada símbolo. Ele foi desenvolvido em 1952 por David A. Huffman\n" +
                        "que era, na época, estudante de doutorado no MIT, e foi publicado no artigo \"A Method for the Construction\n" +
                        "of Minimum-Redundancy Codes\".";
            }
            
            if (texto.isEmpty()) {
                return;
            }

            System.out.println();
            Huffman huffman = new Huffman();
            String dados = huffman.codificar(texto);

            int tamanhoNormal = texto.length() * 8;
            int tamanhoComprimido = dados.length();
            System.out.println("Tamanho normal: " + tamanhoNormal);
            System.out.println("Tamanho com compressão: " + tamanhoComprimido);
            System.out.println();
            System.out.println("Texto codificado:");
            System.out.println(dados);
            System.out.println("\nTexto decodificado:");
            System.out.println(huffman.decodificar(dados));
            System.out.println();
            System.out.println();
        }
    }
}
