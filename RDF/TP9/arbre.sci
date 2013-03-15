// Chargement de la base de noms d'animaux
exec("rdfAnimaux.txt");
n=size(noms,1);
mat=zeros(35,n);
//Q3
for i=1:n
    c = str2code(noms(i));
    mat(c,i) = 1;
end// code les lettres sous forme d'indice, mat 283 col pour 283 mot et 38 ligne pour 38 lettre (on en prend que 26 pour notre exemple)
// a= 10
// z= 35
//Q4
mat=mat(10:$,:);
matsum=zeros(size(mat,1),1);
for i=1:size(mat,1)
    matsum(i,1)=sum(mat(i,:));
end
[mattri,K]=gsort(matsum);//tri les lettre
mattri=[K,mattri];//fusion indice lettre , occurence de la lettre
//matAlpha=mattri;
//tmp=[1:size(matAlpha,1),1];
//for i=1:size(matAlpha,1)
//    tmp(i)=code2str(matAlpha(i,1)+9);
//end
