//Résultat pour K=1 N=200
// 
//    10.    1087.  
//    9.     868.   
//    8.     1041.  
//    7.     972.   
//    6.     907.   
//    5.     932.   
//    4.     980.   
//    3.     943.   
//    2.     1249.  
//    1.     1021. 
//
// erreur  =
// 
//    0.0942  
//
//Résultat pour K=3 N=200
// 
//    10.    1278.  
//    9.     908.   
//    8.     997.   
//    7.     1018.  
//    6.     902.   
//    5.     827.   
//    4.     925.   
//    3.     887.   
//    2.     1262.  
//    1.     996.   
// 
// erreur  =
// 
//    0.1038  
//
//Résultat pour K=3 N=200
//  
//    10.    1536.  
//    9.     966.   
//    8.     976.   
//    7.     1043.  
//    6.     895.   
//    5.     684.   
//    4.     860.   
//    3.     822.   
//    2.     1253.  
//    1.     965.   
//
// erreur  =
// 
//    0.136  
function retour=kNN(x_train,y_train,x_test,k)
    retour=zeros(size(x_test,1),1);
    for i=1 :size(x_test,1)
        x_tmpTab=ones(k,1).*99999999999;
        y_tmpTab=zeros(k,1);
        for j=1 :size(x_train,1)
            distance=sqrt(sum((x_test(i,:)-x_train(j,:))^2));

            if(distance<x_tmpTab(k)) then//comparaison avec le voisin le plus éloigné
                for ii=1:k
                    if(distance<x_tmpTab(ii)) then
                        x_tmpTab(ii)=distance;
                        y_tmpTab(ii)=y_train(j);
                        break;
                    end
                end
            end//fin if 


        end
        if(k==1) then
            selected=y_tmpTab(1);
        else
            m=tabul(y_tmpTab,'i');
            selected=m(find(m(:,2)==max(m(:,2))),1);// tire les scores pour prendre le plus récurent
            if(size(selected,1)<>1) then // si il y a la même récurence pour différent scores
                selected=y_tmpTab(1);
            end
        end
        retour(i)=selected;
        printf(" %d / 10000 \n",i);
        
    end
endfunction

function retour= compare (estim,y_test)
    retour=zeros(size(estim,1),1);
    for i= 1:size(estim,1)
        if(estim(i)<>y_test(i)) then
            retour(i)=1;
        end 
    end
endfunction


stacksize('max');
loadmatfile('mnist_all.mat');
n=200;//nombre des exemplaires d'entrainement 
k=5; //nombre des voisins
x_train = double([train0(1:n,:); train1(1:n,:); train2(1:n,:); train3(1:n,:); train4(1:n,:); train5(1:n,:); train6(1:n,:); train7(1:n,:); train8(1:n,:); train9(1:n,:)]); 
y_train = [ones(n, 1); ones(n, 1)*2; ones(n, 1)*3; ones(n, 1)*4; ones(n, 1)*5; ones(n, 1)*6; ones(n, 1)*7; ones(n, 1)*8; ones(n, 1)*9; ones(n, 1)*10];
x_test = double([test0; test1; test2; test3; test4; test5; test6; test7; test8; test9]);
y_test = [ones(size(test0,1), 1); ones(size(test1,1),1)*2;ones(size(test2,1), 1)*3; ones(size(test3,1), 1)*4; ones(size(test4,1), 1)*5; ones(size(test5,1), 1)*6; ones(size(test6,1), 1)*7; ones(size(test7,1), 1)*8; ones(size(test8,1), 1)*9; ones(size(test9,1), 1)*10];
//Trouver les k plus proche voisins pour chaque ligne de x_test et estimer sa classe//Sauvegarder les classes dans un vecteur y_estim (de la meme taille que y_test )
//Calculer l'erreur en utilisant l'equation (1)


estim=kNN(x_train,y_train,x_test,k);
print(%io(2),tabul(estim));
t=compare(estim,y_test);
erreur=sum(t)/size(estim,1);
print(%io(2),erreur);
//La ligne numero i de la matrice x_train corresponde a la i'eme image, et l'element i de y_train est sa vrai classe

