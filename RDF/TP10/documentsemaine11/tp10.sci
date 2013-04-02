//fonction sur le calcul de l'entropie
function i = entropieClass(ii,jj)
    i = zeros(1,40);
    for kk = 1:400
        if stackedFaces(ii,jj,kk)==1 then
            i(1, ceil(kk/10)) = i(1, ceil(kk/10))+ 1; 
            //i(1,indice) = ceil(kk/10);
            //indice = indice+1;
        end
    end
endfunction

function entrop = entropie(ii,jj)
    entrop = 0;
    res = 0;
    for kk = 1 : 400
        res = res  + stackedFaces(ii,jj,kk);
    end
    res = res/400;
    entrop = -log2(res^res) - log2((1-res)^(1-res));
endfunction

//chargement de l'image
stacksize("max");
allFacesName="allFaces.png";
allFaces=im2bw(imread(allFacesName), 0.5);

//taille des images
[tailleVisageL,tailleVisageC] = size(allFaces);
tailleVisageL = tailleVisageL / 20; //33
tailleVisageC = tailleVisageC / 20; //40

// création de stackedFaces :
// Empilement des visages 40x33 dans une matrice stackedFaces 40x33x400
// Attention, exécution possiblement longue ~30 sec, pas de panique...)
stackedFaces=zeros(40,33,400); // numLignes, numColonnes, numFaces
for i=0:19
    for j=0:19
        stackedFaces(:,:, (i*20 + j + 1) ) = allFaces( 1+i*40 : (i+1)*40 , 1+j*33 : (j+1)*33 );
    end
end

//stackedFacesI=stackedFaces(:,:,I);

//calcul de l'entropie
//entrop = entropie(1,1);
entrop = zeros(40,33);
classCount = zeros(40,33,40);

//classCount(1:40,1:33,:) =entropie(1:40,1:33);

for jj =1 : 40
    for ii = 1 : 33
        //class = entropie(jj,ii);
        entrop(jj,ii) = entropie(jj,ii);
        classCount(jj,ii,:) = entropieClass(jj,ii);
    end
end

