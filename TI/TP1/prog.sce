// Définition des échantillons sur un axe
axe = [0:99] / 100 + 5e-3;
// Définition des éléments de surface
x = ones (1:100)' * axe;
y = axe' * ones (1:100);
// Position de la source
xs = 0.5;
ys = 0.5;
// Calcul de la distance
d = sqrt ((x - xs).^2 + (y - ys).^2);
// Tracé de la fonction distance
//plot3d(axe,axe,d);

i0=100/(2 * %pi);
h=0.5;
e0=i0/(h*h);

//ponctuelle
d1=e0*h^3*(h^2 +d.^2).^(-3/2)
// lamber
d2=e0*h^4*(h^2+d.^2).^(-2)

plot3d1 (axe, axe, d1);
plot3d1 (axe, axe, d2);
f=gcf();
f.color_map=hotcolormap(64);







//n=2;
//
//// Définition des échantillons sur un axe
//axe = [0:199] / 200 + 5e-3;
//// Définition des éléments de surface
//x = ones (1:200)' * axe;
//y = axe' * ones (1:200);
//// Position de la source
//xs = 0;
//ys = 0;
//// Calcul de la distance
//d = sqrt ((x - xs).^2 + (y - ys).^2);
//
//xs = 1;
//ys = 1;
//// Calcul de la distance
//d4 = sqrt ((x - xs).^2 + (y - ys).^2);
//
//xs = 0;
//ys = 1;
//// Calcul de la distance
//d3 = sqrt ((x - xs).^2 + (y - ys).^2);
//
//xs = 1;
//ys = 0;
//// Calcul de la distance
//d2 = sqrt ((x - xs).^2 + (y - ys).^2);
//
//i0=100/(2 * %pi);
//h=0.5;
//e0=i0/(h*h);
//
////ponctuelle
////v1=e0*h^3*(h^2 +d.^2).^(-3/2)
//
//// lamber
//v1=e0*h^4*(h^2+d.^2).^(-2);
//v2=e0*h^4*(h^2+d2.^2).^(-2);
//v3=e0*h^4*(h^2+d3.^2).^(-2);
//v4=e0*h^4*(h^2+d4.^2).^(-2);
//
//vtotal=v1+v2+v3+v4;
//
//plot3d1 (axe,axe,vtotal);
//f=gcf();
//f.color_map=hotcolormap(64);
//
