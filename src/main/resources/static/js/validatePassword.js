function validatePassword(senha) {
    // Mínimo 8 caracteres
    if (senha.length < 8) return false;

    // Pelo menos uma letra maiúscula
    if (!/[A-Z]/.test(senha)) return false;

    // Pelo menos um número
    if (!/[0-9]/.test(senha)) return false;

    // Pelo menos um caractere especial
    if (!/[!@#$%^&*(),.?":{}|<>]/.test(senha)) return false;

    // Se passou por todas as verificações, é válida
    return true;
}
