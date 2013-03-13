//Charger l'image
A = imread('bird_small.png');
//Diviser par 255 pour avoir les valeurs entre 0 et 1
A=A/255;
img_size=size(A);

//Transformer la matrice A en une matrice de Nx3 où
//N = nombre des pixels et chaque ligne contient les valeurs R G et B du pixel. Ceci donne les donnee à clustere:
X = matrix(A, img_size(1) * img_size(2), 3);

//Clusterer les pixels en K=16 clusters (i.e. 16 couleurs)
K=16;
//Fixer le maximum nombre d'iterations de k-means à 10 
max_iter=10;
//N'oubliez pas d'initialiser les premiers centres aléatoirement 

//Remplacer chaque pixel dans l'image original par le centre de son cluster
//et creer X_compressed


//Apres avoir creer X_compressed retransformer le 
//par le code suivant
//X_compressed=matrix(X_compressed, img_size(1),img_size(2),3)

//imagesc(X_compressed)