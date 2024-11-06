
## Lexikální analýza

## Syntaktická analýza

Pokud máme gramatiku G=(N, T, P, S) ve které má nějaký neterminál více pravých stran,

A &rarr; $\alpha$ | $\beta$

kde $\alpha, \beta \in T \cap N$

a chceme se rozhodnout na základě dopředu přečteného symbolu *a* jaké pravidlo použijeme při expanzi
tohoto neterminálu na vrcholu zásobníku, musíme pro obě pravé strany znát věchny terminální symboly
kterými mohou začít. Pro to se definuje funkce *FIRST($\alpha$)*.

FIRST($\alpha$) = { *a*: $\alpha$ &rarr;* a $\beta$, a$\in$T, $\alpha,\beta \in (T \cap N)*$ } 

neboli je to funkce která vrací množinu všech terminálů kterými může začít řetězec $\alpha$ tvořený
obecně terminály a neterminály.



potom musí platit pokud máme pravidlo s více pravými stranami S -> $\alpha$ | $\beta$:

FIRST($\alpha$) $\cap$ FIRST($\beta$) = 0 




