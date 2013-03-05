
function imgr=Reduc (image)
    imgr=image(1:size(image,1)/2,1:size(image,2)/2,:);
    imgr(1,1,1)=image(1,1,1)/4+image(2,1,1)/4+image(1,2,1)/4+image(2,2,1)/4;
    imgr(1,1,2)=image(1,1,2)/4+image(2,1,2)/4+image(1,2,2)/4+image(2,2,2)/4;
    imgr(1,1,3)=image(1,1,3)/4+image(2,1,3)/4+image(1,2,3)/4+image(2,2,3)/4;
    for i=2:size(imgr,1)-2
        printf('i : %d\n',i)
        for j=2:size(imgr,2)-1
            imgr(i,j,1)=image(i*2,j*2,1)/4+image((i*2)+1,j*2,1)/4+image(i*2,(j*2)+1,1)/4+image((i*2)+1,(j*2)+1,1)/4;
            imgr(i,j,2)=image(i*2,j*2,2)/4+image((i*2)+1,j*2,2)/4+image(i*2,(j*2)+1,2)/4+image((i*2)+1,(j*2)+1,2)/4;
            imgr(i,j,3)=image(i*2,j*2,3)/4+image((i*2)+1,j*2,3)/4+image(i*2,(j*2)+1,3)/4+image((i*2)+1,(j*2)+1,3)/4;
        end// 1*2 = 2 on loupe l'indice 1
        imgr(i,1,1)=image(i*2,1,1)/4+image((i*2)+1,1,1)/4+image(i*2,2,1)/4+image((i*2)+1,2,1)/4;
        imgr(i,1,2)=image(i*2,1,2)/4+image((i*2)+1,1,2)/4+image(i*2,2,2)/4+image((i*2)+1,2,2)/4;
        imgr(i,1,3)=image(i*2,1,3)/4+image((i*2)+1,1,3)/4+image(i*2,2,3)/4+image((i*2)+1,2,3)/4;
    end
    for j=2:size(imgr,2)-1
        imgr(1,j,1)=image(1,j*2,1)/4+image(2,j*2,1)/4+image(1,(j*2)+1,1)/4+image(2,(j*2)+1,1)/4;
        imgr(1,j,2)=image(1,j*2,2)/4+image(2,j*2,2)/4+image(1,(j*2)+1,2)/4+image(2,(j*2)+1,2)/4;
        imgr(1,j,3)=image(1,j*2,3)/4+image(2,j*2,3)/4+image(1,(j*2)+1,3)/4+image(2,(j*2)+1,3)/4;
    end
endfunction


function imgr=ReducBL (image)
    imgr=image(1:size(image,1)/2,1:size(image,2)/2);
    imgr(1,1)=image(1,1)/4+image(2,1)/4+image(1,2)/4+image(2,2)/4;
    for i=2:size(imgr,1)-1
        printf('i : %d\n',i)
        for j=2:size(imgr,2)
            imgr(i,j)=image(i*2,j*2)/4+image((i*2)+1,j*2)/4+image(i*2,(j*2)+1)/4+image((i*2)+1,(j*2)+1)/4;
        end// 1*2 = 2 on loupe l'indice 1
        imgr(i,1)=image(i*2,1)/4+image((i*2)+1,1)/4+image(i*2,2)/4+image((i*2)+1,2)/4;
    end
    for j=2:size(imgr,2)
        imgr(1,j)=image(1,j*2)/4+image(2,j*2)/4+image(1,(j*2)+1)/4+image(2,(j*2)+1)/4;
    end
endfunction

function img= Qauntif (image,n)
    quantificateur=2^n;
    img=image;
    img=image./(256/quantificateur);
    round(img)
    img=img.*(256/quantificateur);
endfunction




//im=imreimshow ad("ti-semaine-3-lena.png");
im=imread("ti-semaine-3-mire.png");
stacksize(268435454);

red=im;
green=im;
blue=im;


red(:,:,2)=im(:,:,2)*0;
red(:,:,3)=im(:,:,3)*0;
redG=red(:,:,1);
green(:,:,3)=im(:,:,3)*0;
green(:,:,1)=im(:,:,1)*0;
greenG=green(:,:,2);
blue(:,:,1)=im(:,:,1)*0;
blue(:,:,2)=im(:,:,2)*0;
blueG=blue(:,:,3);
//imshow ([redG,greenG,blueG]);

tailleP=size(redG);
tailleP=tailleP/72;//pouce
tailleP=tailleP*2.54;
imr=Reduc(im);
green1=Qauntif(im,1);
green2=Qauntif(im,2);
green3=Qauntif(im,3);
green4=Qauntif(im,4);
green5=Qauntif(im,5);
green2G=green2(:,:,2);
imrRed=Reducim(red)
imshow([im,green1,green2,green3,green4,green5])
