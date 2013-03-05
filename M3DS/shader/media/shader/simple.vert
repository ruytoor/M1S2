#version 120

varying vec4 couleur;
varying vec2 texCoord;

void main() {
    texCoord=gl_MultiTexCoord0.st;
    couleur=gl_Color;
    gl_Position=gl_ProjectionMatrix*gl_ModelViewMatrix*gl_Vertex;
}
