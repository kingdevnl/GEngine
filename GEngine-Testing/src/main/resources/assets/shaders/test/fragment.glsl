in vec3 pos_pass;
in vec2 texCoord_pass;

out vec4 fragColor;

uniform sampler2D texture_sampler;
uniform int useColor;
uniform bool hasTexture;

void main() {

    if (hasTexture) {
        fragColor = texture(texture_sampler, texCoord_pass);
    } else {
        fragColor = vec4(pos_pass.x+0.5, 0.5, pos_pass.y+0.5f, 1);
    }
}