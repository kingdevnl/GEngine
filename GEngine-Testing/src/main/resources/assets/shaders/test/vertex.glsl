
layout (location = 0) in vec3 pos;

uniform mat4 projectionMatrix;
uniform mat4 modelMatrix;

out vec3 pos_pass;

void main() {
    pos_pass = pos;
    gl_Position =  projectionMatrix * modelMatrix * vec4(pos, 1.0);
}