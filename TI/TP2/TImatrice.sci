//binome Benjamin Ruytoor et Aurore Allart
//TP2

// matrice extrinsèque
function rotX = RotationX(theta)
    rotX = ones(4,4);
    rotX =[1,0,0,0;0,cosd(theta),-sind(theta),0;0,sind(theta),cosd(theta),0;0,0,0,1];
endfunction

function rotY = RotationY(theta)

    rotY = [cosd(theta),0,sind(theta),0;0,1,0,0;-sind(theta),0,cosd(theta),0;0,0,0,1];
endfunction

function rotZ = RotationZ(theta)

    rotZ = [cosd(theta),-sind(theta),0,0;sind(theta),cosd(theta),0,0;0,0,1,0;0,0,0,1];
endfunction

function transs = Translation(x,y,z)

    transs = [1,0,0,x;0,1,0,y;0,0,1,z;0,0,0,1];
endfunction

// -----------------------------------------------------------------------
// Fonction d'affichage d'un objet 2D
// nfigure = numero de la figure scilab
// taille = [nblignes, nbpixels]
// points = matrice 2*N des coordonnees des points
// segments = matrice 2*M des indices des points
// -----------------------------------------------------------------------
function tiAfficheObjet2D (nfigure, taille, points, segments)
    // Creer une nouvelle figure ou activer une figure existante
    hf = scf (nfigure);
    hf.figure_name = "Projection numero %d";
    // Echelle identique sur les deux axes
    ha = gca ();
    ha.isoview = "on";
    ha.axes_reverse = ["off", "on", "off"];
    ha.x_location = "top";
    ha.box = "on";
    // Tracer les points
    plot2d (points(1,:), points(2,:), style = -4, ...
    rect=[0,0,taille(2),taille(1)]);
    // Tracer les segments
    sx = [points(1,segments(1,:));points(1,segments(2,:))];
    sy = [points(2,segments(1,:));points(2,segments(2,:))];
    xsegs (sx, sy);
endfunction
