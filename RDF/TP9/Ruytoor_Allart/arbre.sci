//Q7 partage
function [A,B,indice]=partage(I)
    rel=mat(:,I);
    nrel=size(rel,"c");
    hrel=zeros(1,size(rel,1));
    for k=1:size(rel,1)
        hrel(k)=sum(rel(k,:));
    end;
    //calcul de i par l'entropie
    entropierel = -log((hrel./nrel).^(hrel./nrel)) -log((1-hrel./nrel).^(1-hrel./nrel));
    [m,indice]=max(entropierel);

    //calcul de A
    A=[];
    for k=1:n
        A = [A,I(k) & (mat(indice,k) == 1)];
    end
    
    //calcul de B
    B=[];
    for k=1:n
        B = [B,I(k) & (mat(indice,k) == 0)];
    end
endfunction

//Q9
function joue()
    printf("\nChoisissez le nom d''un animal...\n\n");
    n = size(mat,'c')
    I = ([1:n]>0)
    while sum(I)>1 do
        [A,B,i]=partage(I);
        printf("Ce mot contient-il la lettre %c ? [o/n] \n",code2str(i));
        c = scanf("%s");
        if c=='o' then 
            I=A;
        else 
            I=B
        end
    end
    printf("L''animal est un(e) %s\n\n",noms(I))
endfunction

//Q10
function [nbQuestion,maximum] = arbre(I,str,profondeurActuelle, profondeurMax)
    if (sum(I)>1) then
        profondeurActuelle = profondeurActuelle +1;
        [A,B,i]=partage(I);
        [nbQ1,maxiA] = arbre(A,str+'| ',profondeurActuelle, profondeurMax);
        printf("%s%c (%i,%i)\n",str,code2str(i),sum(A),sum(B));
        [nbQ2,maxiB] = arbre(B,str+'| ',profondeurActuelle, profondeurMax);
        maximum = 1 + max(maxiA,maxiB);
        nbQuestion = nbQ1 + nbQ2;
    else
        if profondeurActuelle ==profondeurMax then
            global elements;
            elements = [elements noms(I)];
        end
        nbQuestion = profondeurActuelle;
        printf("%s -> %s\n",str,noms(I));
        maximum = 0;
    end
endfunction

// Chargement de la base de noms d'animaux
exec("rdfAnimaux.txt");
n=size(noms,1);
mat=zeros(35,n);
////Q3
for i=1:n
    c = str2code(noms(i));
    mat(c,i) = 1;
end// code les lettres sous forme d'indice, mat 283 colonnes pour 283 mots et 38 lignes pour 35 lettres (on en prend que 26 pour notre exemple)
// a= 10
// z= 35

//Q4
mat2=mat(10:$,:);
matsum=zeros(size(mat2,1),1);
for i=1:size(mat2,1)
    matsum(i,1)=sum(mat2(i,:));
end
[mattri,K]=gsort(matsum);//tri les lettre
mattri=[K,mattri];//fusion indice lettre , occurence de la lettre

//Q5 entropie
iX = zeros(26,1);
for j=1:size(mattri,1)
    inv = ((n-mattri(j,2))/n)^((n-mattri(j,2))/n);//calcul si la lettre n'est pas dans le mot
    p = (mattri(j,2)/n)^(mattri(j,2)/n);//calcul si la lettre est dans le mot
    iX(j,1) = -(log2(p) + log2(inv));
end
[m,indice] = max(iX,'r');
firstLetter = K(indice);
//iX = [K,iX];//contient la valeur de l'entropie suivant la lettre

//Q8
I=([1:n]>0);
[A,B,i]=partage(I);
premiereLettre = code2str(i); //retourne "o"
motContenantLaSecondeLettre = sum(A); //retourne 140
motNeContenantPasLaSecondeLettre = sum(B); //retourne 143
//3e lettre en prenant comme deuxieme lettre le "o"
[C,D,j]=partage(A);
TLettre1 = code2str(j); //retourne "e"
mCLTL1 = sum(C); //retourne 71
mNCPLTL1 = sum(D); //69
//3e lettre en ne prenant pas le "f" en deuxieme lettre
[E,F,k]=partage(B);
TLettre2 = code2str(k);//retourne "r"
mCLTL2 = sum(E);//retourne 70
mNCPLTL2 = sum(F);//retourne 73

//Q9
joue()

//Q10
I=([1:n]>0);
global elements;
elements = [];
[nbQuestion,maximum] = arbre(I,' ',0,10);
nbQuestion = nbQuestion/n;

