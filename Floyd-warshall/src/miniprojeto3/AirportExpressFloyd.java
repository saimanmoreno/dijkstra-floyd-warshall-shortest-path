package miniprojeto3;

import java.util.Scanner;

/**
 *
 * @author Simao Moreno
 */
public class AirportExpressFloyd {

    //constantes
    final static int MIN_N = 2,
            MAX_N = 500,
            MIN_SM = 1,
            MAX_CONEXOES = 1000,
            MAX_Z = 100,
            INF = 99999,
            SEM_PARENTE = -1;

    public static void floydWarshall(int grafo[][]) {

        int dist[][] = new int[grafo.length][grafo.length];
        int i, j, k;

        // inicializamos a matriz solução igual ao grafo de entrada.
        for (i = 0; i < grafo.length; i++) {
            for (j = 0; j < grafo.length; j++) {
                dist[i][j] = grafo[i][j];
            }
        }

        /* adicionamos todos os vértices um por um ao conjunto de
           vértices intermediarios.
          ---> antes do início de uma iteração, temos a menor
               distâncias entre todos os pares de vértices, de modo que
               as distâncias mais curtas consideram apenas os vértices
               no conjunto {0, 1, 2, .. k-1} como vértices intermediários.
          ----> Após o final de uma iteração, o vértice indice k é adicionado
                ao conjunto de vértices intermediários e o conjunto
                torna-se {0, 1, 2, .. k} */
        for (k = 0; k < grafo.length; k++) {
            // escolhemos todos os vértices como ponto de partida, um por um
            for (i = 0; i < grafo.length; i++) {
                // escolhemos todos os vértices como destino para o ponto de partida escolhida acima
                for (j = 0; j < grafo.length; j++) {
                    // se o vértice k estiver no caminho mais curto de i para j, atualizamos o valor de dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // imprimir a matriz de distância mais curta
        imprimirMatriz(dist);
    }

    public static void imprimirMatriz(int dist[][]) {

        System.out.println("A matriz a seguir mostra a menor distâncias entre "
                + "todas as estacoes, incluindo o ponto de partida e o aeroporto");
        for (int i = 0; i < dist.length; ++i) {
            for (int j = 0; j < dist.length; ++j) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + "   ");
                }
            }
            System.out.println();
        }
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
        // igual ao numero de estacoes com INF (consideramos aqui grafos 
        // dirigidos (floyWarshall), e a distancia de uma estacao para si mesma como 0)
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                grafo[i][j] = INF;

                if (i == j) {
                    grafo[i][j] = 0;
                }
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

        floydWarshall(grafo);

        // se existir estacao que usamos o ticket imprimir, caso contrario imprimir que o ticket nao foi usado
        if (cx != 0) {
            System.out.println(cx);
        } else {
            System.out.println("TICKET NOT USED!");
        }

        in.close();
    }
}
