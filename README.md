

### Lexikální analýza

Lexikální analýza je process při kterém se čtou vstupní znaky, vhodně se interpetuje jejich posloupnost
a na výstupu se produkuje lexikální symbol, který z pohledu syntaktické analýzi a teorie gramatik
představuje terminální symbol. Vždy by měla být nějáká royumná dohoda mezi lexikálním a 
syntaktickým analyzátorem ohledně tvaru lexikálních symbolů.

Vnitřně se pracuje s tzv. *vstupními symboly*, což je mírná abstrakce nad jednitlivými znaky. 
Jednomu vstupnímu symbolu může odpovídat více vstupních znaků, např. vstupnímu
symbolu *NUMBER* odpovídají vstupní znaky 0-9.

Lexikalni analyzator umi prijimat jazyk generovany regularni bezkontextovou gramatikou.
Takovy jazyk muzeme vyjadrit take pomoci regularniho vyrazu nebo BNF.
Existuje 2 zakladni realizace - pomoci stavu a prechodove funkce nebo pomoci vnerenych
cyklu a podminek jejich vnorene flow odpovida prechodum stavoveho automatu. Tento druhy
zpusob realizace ma tu vyhodu ze promo do toku prechodu lzesnadno pridava semanticke akce,
napr. kontrola ze identifikator neni prilis dlouhy nebo uprava (vynasobeni 10) ciselne
hodnoty po precteni cislice ve vetvi automatu ktera odpovida ciselnemu literalu.

Pro reprezentaci vsupnich symbolu je vyhodne pouzit Char, protoze jeho hodnota muze reprezentovat
primo vstupni znak, napr. '<', '=', '{', a v pripade vstupnho symbolu kteremu odpovid nekolik
vstupnich znaku muzeme pouzit specialni pimena, napr. 'd' pro cislici, 'c' pro znak. Ale take
napr. 'e' pro konec vstupu. Jadrem lexikalni analyzy je switch nad temito symboly, kde v
jednotlivych vetvych se pokracuje se cteni a testovanim symbolu tak jak je tomu v odpovidajicimu
stavovemu automatu.
Dulezite je zajistit ze automat cte dalsi a dalsi symboly tak dlouho dokud se muze posouvat ve
automatu dopredu. Jakmile narazi na vstupni symbol kteremu nerozumi, konci se ctenim a vraci
nalezeny lexikalni symbol (anebo chybu nebi konec vstupu).

### Syntaktická analýza

*Syntaktická analýza je proces pro nalezení derivačního stromu věty která je generovaná bezkontextovou gramatikou.*

Z praktického hlediska se dnes používá syntaktická analýza **shora dolů** a **sdola nahoru**.

Při syntaktické analýze metodou shora dolů postupujeme při tvorbě derivačního stromu od shora, tedy od počátečního
symbolu (*S*) směrem dolů, kdy expandujeme neterminální symboly z leva jejich pravými stranami pravidel.
Výsledný derivační strom může být v tomto
případě reprezentován tzv. *levým rozkladem* neboli posloupnosti čísel pravidel která byla použita při
levé derivaci (shora dolů, z leva do prava).

Jestliže věty jazyka jsou generované bezkontextovou gramatikou, potom modelem pro rozpoznávání takových vět
(rozklad, tvorba derivačního stromu) je zásobníkový automat. 

R = $(Q, T, G, \delta_0, q_0, Z_0, F)$

Q je konečná množina vnitřních stavů<br>
T je konečná vstupní abeceda<br>
G je konečná abeceda zásobníku<br>
$\delta$ je přechodové zobrazení z Q x (T $\cup$ ${\epsilon}$) x $G^*$ do množiny podmnožin Q x $G^*$<br>
$\delta_0$ je počáteční stav<br>
$Z_0$ je počáteční symbol zásobníku<br>
$F \subseteq K$ je množina koncových stavů<br> 

Počáteční konfigurace (stav) automatu je ($q_0, w, Z_0)$ kde $w \in T^*$ (vstupní terminálový řetězec)

automat pracuje tak že ve stavu *q* přečte vstupní symbol *a*, přesune se do stavu $p_i$ a obsah zásobníku se změní z $\alpha$ na $\gamma_i$:

(*q*, aw, $\alpha$) = {($p_1$, $\gamma_1$), ($p_2$, $\gamma_2$), ... }

Toto je nedeterministické chování kdy automat se jakoby nachází ve více stavech současně.
Činnost automatu lze také popsat přechody mezi konfiguracemi (stavy). 

(*q*, *xw*, $\alpha\beta$) &rarr; (*p*, *w*, $\gamma\beta$) když $\delta$(*q*, *x*, $\alpha$) obsahuje (*p*, $\gamma$)

Takto lze popsat jazyk L přijímaný automatem R:

L(R) = {w: ($q_0$, w, $Z_0$) &rarr;\* ($q$, $\epsilon$, $\gamma$), $w \in$ T\*, $q \in F$, $\gamma \in G^*$}

tedy tak že automat postupným čtením vstupní věty *w* postupně přejde do jednoho za svých koncových stavů kdy na vstupu již
není žádný vstupní symbol.

Jeho aplikace pro rozpoznávání vět generované gramatikou obsahuje jen jeden stav, zasobnik obsahuje aktualne rozpracovanou větnou formu (tedy
řetězec skládající se z terminálu a neterminálu). Provádí
2 operace - **expanze** a **srovnání**. 
* *expanze* se vykoná pokud je na vrcholu zasobniku neterminál, vhodně se vybere pravidlo
které má na levé straně tento neterminál, vyjme se tento neterminál z vrcholu a vloží tam místo něj jeho pravou stranu. 
* *srovnani* je operace kdy vstupní symbol (terminál) by měl být na vrcholu zásobníku a měl by se shodovat se symbolem
na vstupu. Pokud tomu tak není, dochází k chybě analýzi kdy vstupní věta není generovaná gramatikou. Jinak se tento vstupní symbol odebere a přečte se další terminál na vstupu. 

Zásobníkový automat je obecně nedeterministický. Nedeterminismus spočívá v tom že nevíme jaké
pravidlo použít pro expanzi neterminálu na vrcholu zásobníku. Pokud bychom vhodně upravili pravidla bezkontextové gramtiky
tak abychom v případě že pro neterminál který má více pravidel věděli které pravidlo použít, mohli bychom deterministicky
provádět syntaktickou analýzu. Lze definovat omezení na pravidla taková abychom vždy vědeli jakou pravou stranu pravidla
pro stejný neterminál na levé straně pravidla použít na základě *dopředu přečteného vstupního symbolu*. Tato myšlenka
tvoří základ pro tzv. LL gramatiky které tvoří podmnožinu beyźkontextových gramatik díký jistým omezením která jsou
kladená na jejich pravidla.

## LL gramatiky

Pokud máme gramatiku *G=(N, T, P, S)* ve které má nějaké pravidlo pro stejný neterminál více pravých stran,

A &rarr; $\alpha$ | $\beta$ kde $\alpha, \beta \in (T \cup N)^*$

a chceme se rozhodnout na základě dopředu přečteného symbolu *a* jaké pravidlo použijeme při expanzi
tohoto neterminálu na vrcholu zásobníku, musíme pro obě pravé strany znát věchny terminální symboly
kterými mohou začít. Pro to se definuje funkce *FIRST($\alpha$)*.

### FIRST($\alpha$)

FIRST($\alpha$) = { *a*: $\alpha$&rarr; a $\beta$, a $\in$ T, $\alpha,\beta \in (T \cup N)^*$ }

neboli je to funkce která vrací množinu všech terminálů kterými může začít řetězec $\alpha$ tvořený
terminály a neterminály.

### FOLLOW(A)

Může však existovat providlo A &rarr; $\epsilon$ které znamená odebrání neterminálu
z vrcholu zásobníku při expanzi. V takovém případě nás také zajímá množina všech terminálů
které moho následovat za neterminálem *A* ve větných formách.

*FOLLOW(A) =* {a; S &rarr;* $\alpha$ A $\beta$, a $\in$ FIRST($\beta$), $\alpha, \beta \in (N \cup T)^*$}

Pokud 
A &rarr; $\alpha | \epsilon$ tedy neterminál mající více pravých stran kde jedna tvoří prázdný řetězec



