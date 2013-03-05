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
//premiere caméra
camera1 = RotationX(90)*Translation(0,0,-5);
camera2 = RotationX(45)*RotationY(45)*Translation(0,0,5);

//matrice intrinsèque
camera3 = [20/800,0,6.6,0;0,20/600,0,8.8;0,0,1,0;0,0,0,1]*[1,0,0,0;0,1,0,0;0,0,1,0;0,0,0,1];
trans = RotationZ(90)*RotationX(90);
camera3 = camera3*trans;

//Projection perspective
//camera 1
//projectionCamera1 = tiAfficheObjet2D(1,taille,matriceCube,matriceCube_seg);
//projection2Camera1 = tiAfficheObjet2D(2,taille,grille,grille_seg);

//camera 2
//projectionCamera2 = tiAfficheObjet2D(1,taille,matriceCube,matriceCube_seg);
//projection2Camera2 = tiAfficheObjet2D(2,taille,grille,grille_seg);


//camera 3
taille = [10,10];
grille1=camera1*grille;
//projectionCamera3 = tiAfficheObjet2D(1,taille,matriceCube,matriceCube_seg);
projection2Camera3 = tiAfficheObjet2D(2,taille,grille1,grille_seg);


