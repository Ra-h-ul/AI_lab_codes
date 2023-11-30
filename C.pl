car(santro_a, hyundai, yes, yes, red).
car(santro_b, hyundai, no, no, blue).
car(xuv_a, mahindra, yes, yes, yellow).
car(xuv_b, mahindra, no, no, green).


greet_user(Name):- write("Hello "), write(Name).


identify_car(Company, Power_staring, Open_roof, Color):-
    car(Car, Company, Power_staring, Open_roof, Color) ->
        write("Found Car for you - "), write(Car), nl;
        write("No car found"), nl.


start :-
    write("Welcome"), nl,
    write("What is your name"), read(Name), nl,
    greet_user(Name), nl, 
    write("Company"), read(Company), nl,
    write("Power_staring"), read(Power_staring), nl,
    write("Open_roof"), read(Open_roof), nl,
    write("Color"), read(Color), nl,
    identify_car(Company, Power_staring, Open_roof, Color).
    