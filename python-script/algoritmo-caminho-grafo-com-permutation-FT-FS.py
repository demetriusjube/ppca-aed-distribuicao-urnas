from itertools import permutations
from sre_constants import IN

INF = 999999
DIST_NULA = '0'
ALTERNATIVAS = ['ICC',  'FS', 'FT']
matriz = [[DIST_NULA, 2643.68782,	462.8640896],
          [1859.944865,	 DIST_NULA, 1699.14573],
          [429.1181346,	1718.51873, DIST_NULA]]

menor_custo = INF
menor_caminho = ''

permutacoes = permutations([1, 2], 2)

for linha in permutacoes:
    primeiraColuna = linha[0]
    segundaColuna = linha[1]
    custo = matriz[0][primeiraColuna] + matriz[primeiraColuna][segundaColuna] + \
      + matriz[segundaColuna][0]
    caminho = ALTERNATIVAS[0] + '-'+ALTERNATIVAS[primeiraColuna] + '-' + ALTERNATIVAS[
        segundaColuna] +  '-'+ALTERNATIVAS[0]
    if (custo < menor_custo):
        menor_custo = custo
        menor_caminho = caminho
    print('Custo do caminho ', caminho, ': ', custo)

print('Menor ciclo: ', menor_caminho, ' com custo ', menor_custo)
