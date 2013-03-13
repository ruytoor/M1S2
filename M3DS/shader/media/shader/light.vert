#version 120
//varying vec4 color;

varying vec3 N;
varying vec3 L;
varying vec3 V;

void main() {

   // float shininessTmp;
  //  float intensite;

    V=vec3(-gl_ModelViewMatrix*gl_Vertex);

    L=gl_LightSource[0].position.xyz;
    N=gl_NormalMatrix*gl_Normal;

   // N=normalize(N);
    //L=normalize(L);
  //  V=normalize(V);

   // shininessTmp=pow(max(dot(V,2*(N*L)*N-L),0.0),gl_FrontMaterial.shininess);
  //  intensite=max(dot(L,N),0.0);

   // color=intensite*gl_FrontMaterial.diffuse;//+shininessTmp);

    //color=intensite*(gl_FrontMaterial.diffuse+shininessTmp);
    //color.y=gl_Normal.z;
    gl_Position=gl_ProjectionMatrix*gl_ModelViewMatrix*gl_Vertex;
}
