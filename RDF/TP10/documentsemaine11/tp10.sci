//fonction sur le calcul de l'entropie
function [contient,count] = entropieClass(ii,jj,stacked)
    contient = zeros(1,400);
    count = zeros(1,40);
    for kk = 1:400
        if stacked(ii,jj,kk)==1 then
            contient(1,kk) = 1;
            count(1, ceil(kk/10)) = count(1, ceil(kk/10))+ 1; 
            //i(1,indice) = ceil(kk/10);
            //indice = indice+1;
        end
    end
endfunction

function entrop = entropie(stacked)
    entrop = zeros(40,33);
    res = 0;
    for jj = 1:40
        for ii = 1:33
            for kk = 1 : 400
                res = res  + stacked(jj,ii,kk);
            end
            res = res/400;
            entrop(jj,ii) = -log2(res^res) - log2((1-res)^(1-res));
        end
    end

endfunction

//fonction partage
function [A,B,i] = partage(I)
    stackedFacesI=stackedFaces(:,:,I);
    entrop = zeros(size(stackedFacesI,"r"),size(stackedFacesI,"c"),size(stackedFacesI,3));
    entrop = entropie(stackedFacesI);
    [i1,i2] = find(entrop==max(entrop));
    // si A[i] est dans I
    i = [i1 i2];
    A = [];
    for k=1:400
        A = [A,I(k) & (stackedFaces(i1,i2,k) == 1)];
    end
    B =  [];
    for k=1:400
       B = [B,I(k) & (stackedFaces(i1,i2,k) == 0)];
    end


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
contient = zeros(40,33,400);

//classCount(1:40,1:33,:) =entropie(1:40,1:33);

for jj =1 : 40
    for ii = 1 : 33
        //class = entropie(jj,ii);
        //entrop(jj,ii) = entropie(jj,ii,stackedFaces);
        [contient(jj,ii,:),classCount(jj,ii,:)] = entropieClass(jj,ii,stackedFaces);
    end
end

entrop = entropie(stackedFaces);
I=([1:400]>0);
indice = zeros(1,2);
[A,B,indice] = partage(I);
sumA = sum(A);
sumB = sum(B);
