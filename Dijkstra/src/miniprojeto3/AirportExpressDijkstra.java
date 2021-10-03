package miniprojeto3;

import java.util.Scanner;

/**
 *
 * @author Simao Moreno
 */
public class AirportExpressDijkstra {

    //constantes
    final static int MIN_N = 2,
            MAX_N = 500,
            MIN_SM = 1,
            MAX_CONEXOES = 1000,
            MAX_Z = 100,
            INF = Integer.MAX_VALUE,
            SEM_PARENTE = -1;

    // função para encontrar o vértice com valor mínimo de distância,
    // do conjunto de vértices ainda não incluídos na árvore de caminho mais curto
    public static int minDistancia(int dist[], Boolean visitado[], int n) {

        int min = INF, min_index = -1;              // inicializar valor minimo 

        for (int v = 0; v < n; v++) {
            if (visitado[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }

    // função para imprimir o caminho mais curto da origem para o vértice atual usando matriz dos parentes
    private static void imprimirCaminho(int verticeAtual, int[] parents) {

        if (verticeAtual == SEM_PARENTE) {
            return;
        }

        imprimirCaminho(parents[verticeAtual], parents);

        System.out.print((verticeAtual + 1) + " ");
    }

    // função que calcula o caminho mais curto do ponto de partida para o aeroporto pelo
    // algoritmo de Dijkstra dado um grafo representado usando matriz de adjacência
    public static int dijkstra(int grafo[][], int pto_partida, int no_estacoes, int aeroporto) {

        int dist[] = new int[no_estacoes];                      // matriz de saída dist[i] manterá a menor distância entre pto_partida e i 
        int[] parents = new int[no_estacoes];                   // matriz parente para armazenar a arvore de menor caminho
        int time = 0;                                           // variavel para guardar o tempo total de viagem

        // visitado[i] será verdadeiro se o vértice i for incluído na arvore 
        // de menor caminho ou a menor distância entre pto_partida e i esteja ja finalizada 
        Boolean visitado[] = new Boolean[no_estacoes];

        for (int v = 0; v < no_estacoes; v++) {                 // inicializamos todas as distâncias como INFINITE e visitado[] como false
            dist[v] = INF;
            visitado[v] = false;
        }

        dist[pto_partida] = 0;                                  // a distância do vértice de origem para si proprio é sempre 0

        parents[pto_partida] = SEM_PARENTE;                     // o vertice inicial nao possui um parente

        for (int i = 0; i < no_estacoes; i++) {                 // encontrar o caminho mais curto para todos os vértices

            int u = minDistancia(dist, visitado, no_estacoes);  // selecionar o nó u nao visitado com menor valor dist (choose_best)

            visitado[u] = true;                                 // marcar o vértice selecionado como visitado

            for (int v = 0; v < no_estacoes; v++) {             // atualiza o valor dist dos vértices adjacentes do vertice escolhido

                // atualiza dist[v] somente se não for visitado, se existe um
                // arco de u para v e o peso total do caminho de pto_partida para
                // v passando por u é menor que o valor atual de dist[v]
                if (!visitado[v] && grafo[u][v] != 0 && dist[u] != INF && dist[u] + grafo[u][v] < dist[v]) {
                    dist[v] = dist[u] + grafo[u][v];
                    parents[v] = u;
                }
            }
        }

        time = dist[aeroporto];                                 // tempo total de viagem é a distancia do aeroporto para o ponto de partida
        imprimirCaminho(aeroporto, parents);
        System.out.println();

        return time;
    }

    public static void main(String[] args) {

        // declaracao das variaveis
        Scanner in = new Scanner(System.in);
        int n, s, e, m, k, x, y, z, cx = 0, i, j;
        int grafo[][];

        do {
            System.out.println("Número de estações, ponto de partida e localização do aeroporto:");
            n = in.nextInt();                       // numero de estacoes
            s = in.nextInt();                       // ponto de partida
            e = in.nextInt();                       // localizacao do aeroporto
        } while ((n < MIN_N || n > MAX_N) || s < MIN_SM || e > n);

        grafo = new int[n][n];

        // preencher toda a matriz de adjacencia do grafo ate a dimensao 
        // igual ao numero de estacoes com 0 (consideramos aqui grafos 
        // nao dirigidos (bidirecional), se fosse dirigido inicializar o grafo com INF)
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                grafo[i][j] = 0;
            }
        }

        do {
            System.out.println("Número de conexões entre as estações da Economy-Xpress:");
            m = in.nextInt();
        } while (m < MIN_SM || m > MAX_CONEXOES);

        System.out.println("Informações das rotas do Economy-Xpress:");
        for (i = 0; i < m; i++) {
            x = in.nextInt();                   // estacao numero x
            y = in.nextInt();                   // estacao numero y
            z = in.nextInt();                   // tempo de viagem entre x e y

            if (x <= n && y <= n && (z >= MIN_SM && z <= MAX_Z)) {
                grafo[x - 1][y - 1] = z;
            } else {
                System.out.println("ROTA IMPOSSIVEL");
                i--;
            }
        }

        do {
            System.out.println("Número de conexões entre as estações da CommercialXpress:");
            k = in.nextInt();
        } while (k < MIN_SM || k > MAX_Z);

        System.out.println("Informações das rotas do Commercial-Xpress:");
        for (i = 0; i < k; i++) {
            x = in.nextInt();                   // estacao numero x
            y = in.nextInt();                   // estacao numero y
            z = in.nextInt();                   // tempo de viagem entre x e y

            if (x <= n && y <= n && (z >= MIN_SM && z <= MAX_Z)) {
                // se a distancia do commercial-express for menor que o economy-express
                // e o ticket ainda nao tiver sido utilizado, usar o ticket
                if (grafo[x - 1][y - 1] > z && cx == 0) {
                    grafo[x - 1][y - 1] = z;
                    cx = x;                         // guardar a estacao que deve ser utilizado o ticket
                }
            } else {
                System.out.println("ROTA IMPOSSIVEL");
                i--;
            }

        }

        System.out.println("-----------------------------------------------------------");

        // obter o tempo total de viagem depois de calcular o caminho (s-1 e e-1 pois primeira posicao do vetor = 0)
        int time = dijkstra(grafo, s - 1, n, e - 1);

        // se existir estacao que usamos o ticket imprimir, caso contrario imprimir que o ticket nao foi usado
        if (cx != 0) {
            System.out.println(cx);
        } else {
            System.out.println("TICKET NOT USED!");
        }

        System.out.println(time);

        in.close();
    }
}
