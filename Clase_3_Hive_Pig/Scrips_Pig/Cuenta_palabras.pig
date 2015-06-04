data = LOAD '/user/sg/shakespeare/A Lovers Complaint.txt' as (text:CHARARRAY);

b = foreach data generate flatten(TOKENIZE((chararray)$0)) as word;
c = group b by word;
d = foreach c generate COUNT(b), group;
store d into '/user/hue/salida_uno';
