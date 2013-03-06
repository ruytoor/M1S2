#version 120
uniform sampler2D briques ;
uniform sampler2D lille1 ;
uniform sampler2D light ;

varying vec4 couleur;
varying vec2 texCoord;

void main() {

    gl_FragColor=(texture2D(briques,texCoord)*texture2D(light,texCoord))*texture2D(lille1,texCoord);
  //gl_FragColor=couleur;
  //gl_FragColor=vec4(0.0,1.0,0.0,0.0);
  //gl_FragColor=gl_FragCoord.x/512.0*vec4(1.0,0,0,0);
}
