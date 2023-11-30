male(a).
male(b).
male(c).
male(d).
male(e).
male(f).

female(i).
female(j).
female(k).
female(l).
female(z).

parent(a,b).
parent(k, b).
parent(b,c).
parent(i,j).
parent(l,e).
parent(z,f).


is_father(X, Y):- male(X), parent(X,Y).
is_mother(X, Y):- female(X), parent(X,Y).
is_grand_father(X, Y):- male(X), parent(Z, Y), parent(X, Z).
is_grand_father(X, Y):- female(X), parent(Z, Y), parent(X, Z).
is_brother(X,Y):- male(X), parent(X, W), parent(Y, W), X\=Y. 
is_cousin_brother(X,Y):- male(X), parent(X, W), parent(Y, L), W\=L.

find_relation(X,Y):- is_father(X,Y), write('X is father of Y'), nl.
find_relation(X,Y):- is_mother(X,Y), write('X is mother of Y'), nl.
find_relation(X,Y):- write('X has no relation to Y'), nl.

start:-
    write("Enter 2 relations"), read(X), read(Y), nl,
    find_relation(X, Y).