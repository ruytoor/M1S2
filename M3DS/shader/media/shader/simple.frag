#version 120

varying vec4 couleur;
varying vec2 texCoord;

void main() {

    gl_FragColor=texture2D(,texCoord);
  //gl_FragColor=couleur;
  //gl_FragColor=vec4(0.0,1.0,0.0,0.0);
  //gl_FragColor=gl_FragCoord.x/512.0*vec4(1.0,0,0,0);
}
