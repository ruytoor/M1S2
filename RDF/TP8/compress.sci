//Charger l'image
A = imread('bird_small.png');
//Diviser par 255 pour avoir les valeurs entre 0 et 1
B = im2double(A);
//B=A/255;
img_size=size(A);

//Transformer la matrice A en une matrice de Nx3 où
//N = nombre des pixels et chaque ligne contient les valeurs R G et B du pixel. Ceci donne les donnee à clustere:
X = matrix(B, img_size(1) * img_size(2), 3);

//Clusterer les pixels en K=16 clusters (i.e. 16 couleurs)
K=16;
//Fixer le maximum nombre d'iterations de k-means à 10 
max_iter=10;
//N'oubliez pas d'initialiser les premiers centres aléatoirement 
[X_ligne,X_col] = size(X);
I = grand(1,'prm',(1:X_ligne)');
Y = zeros(X_ligne,1);
clusters = X(I(1:16),:);
for k=1:10

    for i = 1:X_ligne
        T=repmat([X(i,1),X(i,2),X(i,3)],K,1);
        dist = zeros(16,1);
        dist(1:16)= sqrt((clusters(1:16,1)-T(1:16,1))^2 + (clusters(1:16,2)-T(1:16,2))^2 + (clusters(1:16,3)-T(1:16,3))^2);
        [m,indice] = min(dist);
        Y(i)=indice;
    end
    for j =1:16
        m = size(find(Y==1),2);
        clusters(j,1) = sum(X(find(Y==j),1))/m;
        clusters(j,2) = sum(X(find(Y==j),2))/m;
        clusters(j,3) = sum(X(find(Y==j),3))/m;
    end
end
X_comp = zeros(X_ligne,3);
X_comp(1:X_ligne,:) = clusters(Y(1:X_ligne),:)*256;
X_compressed = matrix(X_comp, img_size(1)*img_size(2),3);
imshow(X_compressed);
//imagesc(X_compressed);
//Remplacer chaque pixel dans l'image original par le centre de son cluster
//et creer X_compressed


//Apres avoir creer X_compressed retransformer le 
//par le code suivant
//X_compressed=matrix(X_compressed, img_size(1),img_size(2),3)

//imagesc(X_compressed)
//imshow(A);
