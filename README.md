
### Lexikální analýza

### Syntaktická analýza

*Syntaktická analýza je proces pro nalezení derivačního stromu věty která je generovaná bezkontextovou gramatikou.*

Z praktického hlediska se dnes používá syntaktická analýza **shora dolů** a **sdola nahoru**.

Při syntaktické analýze metodou shora dolů postupujeme při tvorbě derivačního stromu od shora, tedy od počátečního
symbolu (*S*) směrem dolů, kdy expandujeme neterminální symboly z leva jejich pravými stranami pravidel. 
Výsledný derivační strom může být v tomto
případě reprezentován tzv. *levým rozkladem* neboli posloupnosti čísel pravidel která byla použita při
levé derivaci (shora dolů, z leva do prava).

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

*FOLLOW(A) =* {a; S &rarr;* $\alpha$A$\beta$, a $\in$ FIRST($\beta$), $\alpha \in (N \cup T)^*$}

Pokud 
A &rarr; $\alpha | \epsilon$ tedy neterminál mající více pravých stran kde jedna tvoří prázdný řetězec



