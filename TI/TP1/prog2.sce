//c=2;
n=10;
tabn=linspace(0,2,n);
// Définition des échantillons sur un axe
axe = [0:199] / 100 + 5e-3;
axe100=[0:99]/ 100 + 5e-3;
// Définition des éléments de surface
x = ones (1:200)' * axe;
y = axe' * ones (1:200);

i0=100/(2 * %pi);
h=0.5;
e0=i0/(h*h);
vtotal=zeros(200,200);

for i=1:n
    for j=1:n
        xs = tabn(i);
        ys = tabn(j);
//        print(%io(2),ys);
//        print(%io(2),xs);
        d = sqrt ((x - xs).^2 + (y - ys).^2);
        v=e0*h^4*(h^2+d.^2).^(-2);
        vtotal=vtotal+v;
    end
end
vMid=vtotal(51:150,51:150);
plot3d1 (axe,axe,vtotal);
scf();
plot3d1 (axe100,axe100,vMid);
variation=((max(vMid)-min(vMid))/max(vMid))
//variation=(vtmp(50,50)-vtmp(1,1))/vtmp(50,50);
print(%io(2),max(vMid));
print(%io(2),min(vMid));
print(%io(2),variation);
f=gcf();
a=gca();
//a.data_bounds=[-0,-0,-0;1,1,600];
f.color_map=hotcolormap(64);
