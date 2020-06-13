in vec3 pos_pass;
out vec3 fragColor;

void main() {
    fragColor = vec3(pos_pass.x+0.5, 0.5, pos_pass.y+0.5);
}