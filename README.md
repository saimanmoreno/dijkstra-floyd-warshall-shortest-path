# Airport Express Problem using Dijsktra and Floyd Warshall Algorithms

Em uma pequena cidade, um serviço de comboios, Airport-Express, leva os residentes ao 
aeroporto mais rapidamente do que outros transportes. </br>

Existem dois tipos de comboios no Airport-Express, o EconomyXpress e o Commercial Xpress. 
Eles viajam em diferentes velocidades, percorrem rotas diferentes e têm custos 
diferentes. João está indo ao aeroporto para encontrar seu amigo. Ele quer pegue o 
Commercial-Xpress, que deveria ser mais rápido, mas ele não tem dinheiro suficiente. 
Felizmente, ele tem um bilhete para o Commercial-Xpress, que pode levar uma estação 
para a frente. E se ele usar o bilhete com sabedoria, ele pode acabar economizando muito 
tempo. </br>

No entanto, escolher a melhor hora para usar o ticket não é fácil para ele. João agora 
procura sua ajuda. As rotas dos dois tipos de comboio são dados. Escreva um programa 
para encontrar a melhor rota para o destino. O programa também deve informar quando 
o ticket deve ser usado. </br>

# Entrada
A entrada consiste em vários casos de teste. Casos consecutivos são separados por uma 
linha em branco. A primeira linha de cada caso contém 3 números inteiros, N, S e E (2 ≤ 
N ≤ 500, 1 ≤ S, E ≤ N), que representam o número de estações, o ponto de partida e a 
localização do aeroporto, respectivamente.


Existe um número inteiro M (1 ≤ M ≤ 1000) representando o número de conexões entre 
as estações da Economy-Xpress. As próximas linhas M fornecem as informações das 
rotas do Economy-Xpress.


Cada um consiste em três números inteiros X, Y e Z (X, Y ≤ N, 1 ≤ Z ≤ 100). Isso significa 
que X e Y são conectado e leva Z minutos para viajar entre essas duas estações.


A próxima linha é outro número inteiro K (1 ≤ K ≤ 1000) representando o número de 
conexões entre as estações do Commercial-Xpress. As próximas linhas K contêm as 
informações do CommercialXpress no mesmo formato que o do Economy-Xpress.


Todas as conexões são bidirecionais. Pode-se assumir que existe exatamente uma rota 
ideal para o aeroporto. Pode haver casos em que deves usar o seu ticket para chegar ao 
aeroporto.

# Saída
Para cada caso de teste, você deve primeiro listar o número de estações que João visitaria 
por ordem. Na próxima linha, digite 'Ticket Not Used' se você decidir NÃO usar o ticket; 
caso contrário, indique a estação onde João deve pegar o comboio do Commercial-Xpress. 
Por fim, imprima o tempo total da viagem na última linha. Conjuntos 
consecutivos de saída devem ser separados por uma linha em branco.


# Exemplo 
Entrada </br>
Número de estações, ponto de partida e localização do aeroporto: </br>
4 1 4  </br>
Número de conexões entre as estações da Economy-Xpress: </br>
4 </br>
Informações das rotas do Economy-Xpress: </br>
1 2 2  </br>
1 3 3 </br>
2 4 4  </br>
3 4 5  </br>
Número de conexões entre as estações da CommercialXpress: </br>
1  </br>
Informações das rotas do CommercialXpress: </br>
2 4 3  </br>
Saída </br>
1 2 4  </br>
2  </br>
5 </br>
