package huffman;

/**
 *
 * @author luana
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Huffman {
    private No raiz;
    

    private char[] getChars(String texto) {
        char[] letras = new char[texto.length()];
        texto.getChars(0, texto.length(), letras, 0);
        return letras;
    }
    
    
    
    
    private PriorityQueue<No> contaFrequencias(char[] letras) {
        Map<Character, No> contador = new HashMap<>();
        //esse for percorre o texto inteiro letra-a-letra
        for (char ch : letras) { //varro todo o texto verificando se a letra já contem no meu contador de frequencia
            if (!contador.containsKey(ch)) { //se a letra não existe na tabela, adiciona com contagem 0
             //se a letra não contem no contador de frequencia, vamos adicioná-la com a contagem ZERO
                contador.put(ch, new No(ch)); //adiciono a letra no contador de frequencia
            }
            //busca a letra na tabela e adiciona 1 a sua contagem
            contador.get(ch).add(); // tendo adicionado anteriormente a letra na tabela de frequencia, 
            					 // pesquiso a prequencia daquela letra no arquivo e vou adicionando +1 
            					 // em cada aparição
        }

        return new PriorityQueue<>(contador.values()); //retorna uma lista priorizada contendo os nós em ordem crescente de frequencia
    						// o java já nos da uma função que faz isso a função PriorityQueue
    }

    
    private No criaArvore(PriorityQueue<No> no) {
        while (true) {
          
            //retiro os dois nós menos frequentes da árvore 
            No no1 = no.poll(); 
            No no2 = no.poll();

            // crio o no pai com a soma da frequencia dos dois nós retirados anteriormente
            No pai = new No(no1, no2);
            
            if (no.isEmpty()) { //se a fila está varia, o processo terminou, entao retorno o no pai
                return pai;
            }

      
            no.add(pai); //caso contrário, readicionamos o nó pai na lista e realizamos novamente o processo
        }
    }

   
    private Map<Character, String> criaCodeMap() {
        //cria codemap vazio e chama essa função passando tb uma seq vazia
        //ao final teremos o mapa que mapeia cada caractere do texto a sua nova seq binaria
        Map<Character, String> resultado = new TreeMap<>();
        raiz.preencherCodeMap(resultado, "");
        return resultado;
    }

  
    public String codificar(String texto) {
        char[] letras = getChars(texto);
        raiz = criaArvore(contaFrequencias(letras));
        Map<Character, String> codemap = criaCodeMap();
        StringBuilder dado = new StringBuilder();
        

//percorro o texto
        for (char ch : letras) {
            //subtitui cada palavra por sua sequencia binaria correspondente
            dado.append(codemap.get(ch));
        }
        return dado.toString();
    }

    public String decodificar(String dados) {
        //inicia na raiz
        No atual = raiz;
        StringBuilder resultado = new StringBuilder();
        
        //percorre a arvora       
        for (char ch : getChars(dados)) {
        //se o simbolo for 0
            if (ch == '0') {
                // percorro o nó da esquerda
                atual = atual.getEsquerda();
            } else { //caso contrário
                //percorro o nó da direita
                atual = atual.getDireita();
            }
             //se for uma folha
            if (atual.isFolha()) {
                //pego o simbolo dafolha e add no resultado
                resultado.append(atual.getSimbolo());
                atual = raiz; //volta ate a raiz da arvore
            }
        }
        //retorno a string final
        return resultado.toString();
    }
}
