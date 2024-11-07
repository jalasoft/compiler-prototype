
### Lexikální analýza

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

todo

Ten obecný se skládá ze stavu, vstupní abecedy, abecedy zásobníku, prechodové funkce z trojice (stav, vstupní symbol, stav zásobníku) -> (stav, stav zasobníku).

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



