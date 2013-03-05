//exemple #1 : Gaussienne
d = grand(1,10000,'nor',0.5,0.1)
histplot(20,d,leg='rang(1,10000,''normal'')',style = 16, rest= [-3,0,3,0.5])


// exemple #2 : Bonomiale n=6, p = 0.5
d = grand(1000,1,"bin",6,0.5);
c = linspace(-0.5,6.5,8);
clf();
histplot(c,d,style = 2)

//exemple #3 : Exponentielle
lambda = 2;
X = grand(100000,1,"exp",1/lambda);
Xmax = max(X);
clf();
histplot(40,X,style=2)
x = linspace(0,max(Xmax),100);
plot2d(x,lambda*exp(-lambda*x),strf = "000",style = 5)
legend(["histogram" "la courbe exacte"]);


