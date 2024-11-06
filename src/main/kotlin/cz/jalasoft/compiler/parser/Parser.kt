package cz.jalasoft.compiler.parser

/**
 * Syntakticka analyza je nalezeni derivacniho stromu vety ktera je generovana bezkontextovou gramatikou.
 * Z praktickeho hlediska se dnes pouziva syntakticka analyza shora dolu a sdola nahoru
 *
 * Pri syntakticke analyze shora dolu postupujeme pri tvorbe derivacnho stromu od shora, teda od pocatecniho
 * symbol smerem dolu, kdy expandujeme neterminalni symboly z leva. Vysledny derivacni strom muze byt v tomto
 * pripade reprezentovan tzy. levym rozkladem neboli posloupnosti cisel pravidel ktera byla pouzita pri
 * leve derivaci (shora dolu, z leva do prava).
 *
 * Jestli ze vety jazyka jsou generovane bezkontextovou gramatikou, potom modelem pro rozpoznavani takovych vet
 * (levy rozklad, tvorba derivacniho stromu) je zasobnikovy automat. Ten obecny se sklada ze: stavu, vstpni abecedou,
 * abecedy zasobniku, prechodovbou funknci z trojice (stav, vstupni symbol, stav zasobniku) -> (stav, stav zasobniku).
 * Z hlediska rozpoznavani ma tento automat jen jeden stav, zasobnik obsahuje aktualne rozpracovanou levou derivaci (tedy
 * obsahuje retezec skladajici se z terminalu a neterminalu). Provadi
 * 2 operace - expanze a srovnani. Expanze se vykona pokud je na vrcholu zasobniku neterminal, kdyz vhodne vybere pravidlo
 * ktere ma leve strane tento neterminal, vyjme tento neterminal z vrcholu a vlozi tam jeho pravou stranu. Druha operace je
 * srovnani kdyz vstupni symbol (terminal) by mel byt stejny jako terminal na vrcholu zasobniku. Ten odebere a precte dalsi
 * terminal na vstupu. Zasobnikovy automat je obecne nedeterministicky. Nedeterminismus tkvi hlavne v tom ze nevime jake
 * pravidlo pouzit pro expanzi neterminalu na vrcholu zasobniku.
 * Pro prakticke pouziti nejsou zasobnikove automaty vhodne. Misto toho se pouzivaji LL gramatiky ktere tvori podmnozimnu
 * bezkontextovych gramatik, maji specialni pravidla diky kterym lze na zaklade dopredu prectenenho vstupniho symbolu najit
 * deterministickym zpusobem jake pravidlo pouzit na expanzi neterminalu.
 *
 * K tomu abychom mohli syntakticky analyzator realizovat je potreba se zbavit determinicnosti zasobnikoveho automatu.
 * Existuje podmnozina bezkontextovych gramatik, tzv. LL gramatiky ktere maji pravidla usporadana tak ze je vzdy jasne
 * jake pravidlo pouzit pri expanzi. Tyka se pravidel ktere maji vice pravych stran a je potreba jednoznacne rozhondnout
 * jakou pravou stranu pouzijeme.
 *
 * FIRST(alfa) je funkce ktera vraci mnozinu vsech terminalu kterymi muze zacit retezec alfa (skladajici se z terminalu a neterminalu).
 *
 * potom musi platit pokud mam pravislo S -> alfa | beta:
 * FIRST(alfa) v pruniku s FIRST(beta) = prazdna mnozina
 *
 */
class Parser {
}