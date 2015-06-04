data = load '/user/training/Bateo.csv' using PigStorage(',');

juegos = FOREACH data GENERATE $0 as jugador, $1 as anio, $8 as carreras;

grp_data = GROUP juegos by (anio);

max_runs = FOREACH grp_data GENERATE group as grp,MAX(juegos.carreras) as max_runs;

join_max_run = JOIN max_runs by ($0, max_runs), juegos by (anio,carreras);  

join_data = FOREACH join_max_run GENERATE $0 as year, $2 as jugador, $1 as runs;

dump join_data;
