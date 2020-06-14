
layout (location = 0) in vec3 pos;
layout (location=1) in vec2 texCoord;
uniform mat4 projectionMatrix;
uniform mat4 modelMatrix;
uniform int hasTexture;

out vec3 pos_pass;
out vec2 texCoord_pass;

void main() {
    pos_pass = pos;
    texCoord_pass = texCoord;
    gl_Position =  projectionMatrix * modelMatrix * vec4(pos, 1.0);
}