from itertools import permutations
from sre_constants import IN

INF = 999999
DIST_NULA = '0'
COLUNAS_PERMUTACAO = [1, 2, 3, 4]
ALTERNATIVAS = ['ICC', 'BCE', 'FS', 'FT', 'FD']
matriz = [[DIST_NULA, 905.1900896,	2643.68782,	462.8640896,	624.7508477],
          [1167.945135, DIST_NULA, 2060.57573,	1097.328,	1017.969758],
          [1859.944865,	2024.70473, DIST_NULA, 1699.14573,	1888.620488],
          [429.1181346,	1072.694,	1718.51873, DIST_NULA, 457.7937581],
          [739.7231976,	1111.589063,	2029.123793,	519.251063,	DIST_NULA]]

menor_custo = INF
menor_caminho = ''

permutacoes = permutations([1, 2, 3, 4], 4)

for linha in permutacoes:
    primeiraColuna = linha[0]
    segundaColuna = linha[1]
    terceiraColuna = linha[2]
    quartaColuna = linha[3]
    custo = matriz[0][primeiraColuna] + matriz[primeiraColuna][segundaColuna] + \
        matriz[segundaColuna][terceiraColuna] + \
        matriz[terceiraColuna][quartaColuna] + matriz[quartaColuna][0]
    caminho = ALTERNATIVAS[0] + '-'+ALTERNATIVAS[primeiraColuna] + '-' + ALTERNATIVAS[
        segundaColuna] + '-' + ALTERNATIVAS[terceiraColuna] + '-'+ALTERNATIVAS[quartaColuna] + '-'+ALTERNATIVAS[0]
    if (custo < menor_custo):
        menor_custo = custo
        menor_caminho = caminho
    print('Custo do caminho ', caminho, ': ', custo)

print('Menor ciclo: ', menor_caminho, ' com custo ', menor_custo)
