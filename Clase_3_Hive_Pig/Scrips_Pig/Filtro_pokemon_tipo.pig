data = load '/user/training/pokemon.csv' using PigStorage(',');

poke = FOREACH data GENERATE $3 as tipo;

grp = GROUP poke BY tipo;

cuenta = FOREACH grp GENERATE group, COUNT(poke) as num;

filtro = FILTER cuenta BY num > 3;

store filtro into '/user/training/out_pokemon';
