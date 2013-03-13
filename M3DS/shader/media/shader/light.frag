#version 120

varying vec4 color;
varying vec3 N;
varying vec3 L;
varying vec3 V;

void main() {

    float shininessTmp;
    float intensite;
    vec3 V2;
    vec3 N2;
    vec3 L2;
    V2=normalize(V);
    L2=normalize(L);
    N2=normalize(N);


    shininessTmp=pow(max(dot(V2,2*(N2*L2)*N2-L2),0.0),gl_FrontMaterial.shininess);
    intensite=max(dot(L2,N2),0.0);
    gl_FragColor=intensite*(gl_FrontMaterial.diffuse+shininessTmp);

    //  gl_FragColor=vec4(1.0,0.0,0.0,0.0);
}
