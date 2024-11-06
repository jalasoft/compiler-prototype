
## Lexikální analýza

## Syntaktická analýza

*Syntaktická analýza je proces pro nalezení derivačního stromu věty která je generovaná bezkontextovou gramatikou.*

Z praktického hlediska se dnes používá syntaktická analýza **shora dolů** a **sdola nahoru**.

Při syntaktické analýze metodou shora dolů postupujeme při tvorbě derivačního stromu od shora, tedy od počátečního
symbolu (*S*) směrem dolů, kdy expandujeme neterminální symboly z leva jejich pravými stranami pravidel. 
Výsledný derivační strom může být v tomto
případě reprezentován tzv. *levým rozkladem* neboli posloupnosti čísel pravidel která byla použita při
levé derivaci (shora dolů, z leva do prava).

Pokud máme gramatiku *G=(N, T, P, S)* ve které má nějaké pravidlo pro stejný neterminál více pravých stran,

A &rarr; $\alpha$ | $\beta$ kde $\alpha, \beta \in (T \cup N)^*$

a chceme se rozhodnout na základě dopředu přečteného symbolu *a* jaké pravidlo použijeme při expanzi
tohoto neterminálu na vrcholu zásobníku, musíme pro obě pravé strany znát věchny terminální symboly
kterými mohou začít. Pro to se definuje funkce *FIRST($\alpha$)*.

FIRST($\alpha$) = { *a*: $\alpha$&rarr; a $\beta$, a $ \in $ T,


$\alpha,\beta \in (T \cap N)*$ }

neboli je to funkce která vrací množinu všech terminálů kterými může začít řetězec $\alpha$ tvořený
terminály a neterminály.



