package huffman;

/**
 *
 * @author luana
 */

import java.util.Map;

public class No implements Comparable<No> {
    private char simbolo;
    private int contador;

    private No esquerda;
    private No direita;

    public No(char simbolo) {
        this.simbolo = simbolo;
    }

    public No(No esquerda, No direita) {
        this.simbolo = '+';
        this.esquerda = esquerda;
        this.direita = direita;
    }

    public boolean isFolha() {
        return esquerda == null && direita == null;
    }

    public int getFrequencia() {
        if (isFolha())
            return contador;
        return esquerda.getFrequencia() + direita.getFrequencia();
    }

    public char getSimbolo() {
        return simbolo;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void add() {
        contador++;
    }

    @Override
    public int compareTo(No o) {
        return getFrequencia() - o.getFrequencia();
    }

    @Override
    public String toString() {
        String ch = simbolo == '\n' ? "\\n" : "" + simbolo;

        return String.format("'%s': %d", ch, getFrequencia());
    }
    
    
    
   
    

    //recebe por parametro o codemap ja gerado ate entao e uma string contendo o simbolo ja gerado ate entao
    public void preencherCodeMap(Map<Character, String> codemap, String sequencia) {
        if (isFolha()) { //se estivermos em uma folha
            codemap.put(getSimbolo(), sequencia); //colocamos no codemap o simbolo que essa folha contem
                        // e a sequencia gerada ate entao e retornar
            return;
        }
//caso contrario
        esquerda.preencherCodeMap(codemap, sequencia + "0"); // percorre a arvore pra  primeiro para esquerda passando o codemap gerado ate entao e a seq com zero no final
        direita.preencherCodeMap(codemap, sequencia + "1");// percorre a arvore pra  primeiro para esquerda passando o codemap gerado ate entao e a seq com um no final
    }
}
