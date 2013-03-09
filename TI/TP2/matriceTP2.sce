//TP2 : Modele simple d'objet 3D
exec("TImatrice.sci",-1);

//cube
matriceCube = [-0.5,-0.5,0.5,0.5,0.5,0.5,-0.5,-0.5;0.5,0.5,0.5,0.5,-0.5,-0.5,-0.5,-0.5;-0.5,0.5,0.5,-0.5,-0.5,0.5,0.5,-0.5;1,1,1,1,1,1,1,1];
matriceCube_seg = [1:7,1,2,3,5,8;2:8,4,7,6,8,1];

//grille plane
grille = [-2.5,-1.5,-0.5,0.5,1.5,2.5,-2.5,-1.5,-0.5,0.5,1.5,2.5,-2.5,-1.5,-0.5,0.5,1.5,2.5,-2.5,-1.5,-0.5,0.5,1.5,2.5;-1.5,-1.5,-1.5,-1.5,-1.5,-1.5,-0.5,-0.5,-0.5,-0.5,-0.5,-0.5,0.5,0.5,0.5,0.5,0.5,0.5,1.5,1.5,1.5,1.5,1.5,1.5;0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0;1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1];
liste = 1:5;
listedeux = 1:6;
grille_seg = [liste,liste+6,liste+12,liste+18,listedeux,listedeux+6,listedeux+12;liste+1,liste+7,liste+13,liste+19,listedeux+6,listedeux+12,listedeux+18];

//matrice extrinsèque
//position
position1 = Translation(0,0,-5)*RotationX(90);

position2 = Translation(0,0,5)*RotationY(35.26439)*RotationX(-45);
//position2 = RotationX(45)*RotationY(35.264339)*Translation(0,0,5);
//matrice intrinsèque
f = 20;
Kc = 800/8.8;
Kl = 600/6.6;
Cc = 400;
Cl = 300;
camera = [Kc,0,Cc;0,Kl,Cl;0,0,1]*[f,0,0,0;0,f,0,0;0,0,1,0];

taille = [-5,-5,700,600];
taille2 = [-600,-200,1500,1100];
//matriceCube
//tiAfficheObjet2D(11,taille,matriceCube,matriceCube_seg);

//grille
//tiAfficheObjet2D(12,taille,grille,grille_seg);


//Projection perspective

// position 1
m=camera*position2;
m1=m*matriceCube;

m2=m*grille;

for i =1:size(m1,2)
    m1(1,i) = m1(1,i)/m1(3,i);
    m1(2,i) = m1(2,i)/m1(3,i);
end

for i =1:size(m2,2)
    m2(1,i) = m2(1,i)/m2(3,i);
    m2(2,i) = m2(2,i)/m2(3,i);
end
tiAfficheObjet2D(11,taille,m1,matriceCube_seg);
//tiAfficheObjet2D(12,taille2,m2,grille_seg);
