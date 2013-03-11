//partie 0: Charger et visualiser les donnees
loadmatfile('ex3data1.mat');
stacksize('max'); 
// données d'entrainement sont enregistrees dans X, Y
[X_lignes,X_cols] = size(X);

//Sélectionner 100 images a visualiser
//Y=grand(n,"prm",vect) génère n permutations aléatoire du vecteur colonne (m x 1) vect.
rand_indices = grand(1,'prm',(1:X_lignes)')';
sel = X(rand_indices(1:100), :);

//arrondi a l'entier le + proche
example_width=round(sqrt(size(sel,2)));

//taille de sel
[m n] = size(sel);
example_height = (n / example_width);

//floor = arrondi vers le bas
display_rows = floor(sqrt(m));
//ceil = arrondi vers le haut. Renvoie dans displays_cols, le + entier sup de chaque m/display_rows
display_cols = ceil(m / display_rows);

//Fixer la distance entre les images en display 
pad = 1;

//Initialiser display_array
display_array = -1* ones(pad + display_rows * (example_height + pad),pad + display_cols * (example_width + pad));

//Copier chaque exemplaire dans une case de display_array
curr_ex = 1;
for j = 1:display_rows
    for i = 1:display_cols
        if curr_ex > m, 
            break; 
        end
        //Copier la case 
        
        //Prendre la valeur maximum de la case
        max_val = max(abs(sel(curr_ex, :)));
        display_array(pad + (j - 1) * (example_height + pad) + (1:example_height), pad + (i - 1) * (example_width + pad) + (1:example_width)) = matrix(sel(curr_ex, :), example_height, example_width) / max_val;
        curr_ex = curr_ex + 1;
    end
    if curr_ex > m, 
        break; 
    end
end
f=gcf();
drawlater()
f.color_map=graycolormap(32);
display_array=(display_array+1)*16;
Matplot(display_array);
a=gca();
a.isoview="on";
a.box="off";
a.axes_visible=["off" "off" "off"];
drawnow()
mprintf('Programme en pause:\n Appuyez sur n''importe quel touche pour continuer')
partie0=input(' ')

//partie1: Classification des chiffres manuscrits par Regression Logistique
printf('Partie 1.1: Entrainement \n');
xdel(winsid());
num_labels=10;//nombre des classes = 10 comme l'on classifie des chiffres 0..9
all_theta=zeros(num_labels,X_cols+1); //all_theta initialise a la bonne taille
X=[ones(X_lignes,1) X];
function g = sigmoid(z)
g = 1.0 ./ (1.0 + exp(-z));
endfunction

function [J, grad, ind]=lrCout(theta,ind, X, new_y)
[X_lignes,X_cols]=size(X);
lambda = 0.1;//Paramettre de regularisation 
taille_theta = size(theta);
oo = ones(taille_theta(1),taille_theta(2));
oo(1)=0;
J =  (1/X_lignes) * ( -new_y'*log(sigmoid(X*theta)) -(1-new_y)'*log(1-sigmoid(X*theta)))  + (lambda /(2*X_lignes)) * sum(theta.^2);

grad =  (1 / X_lignes) * (X' * (sigmoid(X * theta) - new_y) + lambda*theta.*oo);
endfunction

for ii=1:num_labels
    new_y=1*(y==ii);
    costf=list(lrCout, X,new_y);
   [cout, bb]=optim(costf,all_theta(ii,:)',imp=2);
   all_theta(ii,:)=bb';
    //disp()
end

printf('Partie 1.2: Prediction \n');
[valeurMax, pred] = max(sigmoid(X*all_theta'),'c');
printf('\n Percision: %f\n', mean(double(pred == y)) * 100);

X=X(:,2:$);
rp =grand(1,'prm',(1:X_lignes)');
for i = 1:length(rp)
    printf('\nDisplaying Example Image\n');
    f=gcf();
    drawlater()
    cmap=graycolormap(32);
    f.color_map=cmap;
    display_array=matrix(X(rp(i),:),20,20);
    display_array=(display_array+1)*16;
    Matplot(display_array);
    a=gca();
    a.isoview="on";
    a.box="off";
    a.axes_visible=["off" "off" "off"];
    drawnow()

    p = pred(rp(i));
    mprintf('Prediction: %d (chiffre %d)\n', modulo(p,10), modulo(y(rp(i)),10));
    mprintf('Visualisation en Pause:\n Appuyez sur n''importe quel touche pour continuer.\n Appuyez sur 1 pour arreter.\n')
    cont=input(' ');
    if(cont==1)
        break;
    end
end
xdel(winsid());
