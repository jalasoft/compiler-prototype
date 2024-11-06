
## Lexikální analýza

## Syntaktická analýza

FIRST($\alpha$) je funkce která vrací množinu všech terminálů kterými může začít řetězec $\alpha$ 

{ $\alpha$: S ->* $\alpha$; $\alpha \in T \cap N$ }

(skladajici se z terminalu T a neterminalu N)

potom musí platit pokud máme pravidlo 

S -> $\alpha$ | $\beta$:

FIRST($\alpha$) $\cap$ FIRST($\beta$) = 0 




