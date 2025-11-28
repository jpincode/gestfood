package com.gestfood.gestfood.business.service;

import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import com.gestfood.gestfood.business.exception.BadRequestException;

@Service
public class ValidationService {
    
    private static final String EMAIL_REGEX = 
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    
    protected void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new BadRequestException("Id inválido.");
        }
    }

    protected void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new BadRequestException("Senha inválida.");
        }

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{8,}$";
        boolean isValid = Pattern.matches(regex, password) && !password.contains(" ");

        if (!isValid) {
            throw new BadRequestException("Senha inválida. A senha deve conter pelo menos 8 caracteres, uma letra maiúscula, uma minúscula, um número e um caractere especial.");
        }
    }
    
    protected void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new BadRequestException("Email não pode ser vazio.");
        }
        
        String trimmedEmail = email.trim();
        
        // Verifica formato básico com regex
        if (!EMAIL_PATTERN.matcher(trimmedEmail).matches()) {
            throw new BadRequestException("Formato de email inválido.");
        }
        
        // Verificações adicionais
        if (trimmedEmail.length() > 254) {
            throw new BadRequestException("Email muito longo (máximo 254 caracteres).");
        }
        
        String[] parts = trimmedEmail.split("@");
        if (parts.length != 2) {
            throw new BadRequestException("Email deve conter exatamente um @.");
        }
        
        String localPart = parts[0];
        String domain = parts[1];
        
        // Verifica parte local
        if (localPart.length() > 64) {
            throw new BadRequestException("Parte local do email muito longa (máximo 64 caracteres).");
        }
        
        if (localPart.startsWith(".") || localPart.endsWith(".")) {
            throw new BadRequestException("Parte local não pode começar ou terminar com ponto.");
        }
        
        // Verifica domínio
        if (domain.startsWith(".") || domain.endsWith(".")) {
            throw new BadRequestException("Domínio não pode começar ou terminar com ponto.");
        }
        
        if (domain.contains("..")) {
            throw new BadRequestException("Domínio não pode conter pontos consecutivos.");
        }
        
        // Verifica TLD
        String[] domainParts = domain.split("\\.");
        if (domainParts.length < 2) {
            throw new BadRequestException("Domínio deve conter pelo menos um ponto.");
        }
        
        String tld = domainParts[domainParts.length - 1];
        if (tld.length() < 2) {
            throw new BadRequestException("TLD (última parte do domínio) deve ter pelo menos 2 caracteres.");
        }
    }

    /**
     * Valida um CPF seguindo as regras oficiais da Receita Federal
     * @param cpf CPF a ser validado (com ou sem formatação)
     * @throws BadRequestException se o CPF for inválido
     */
    protected void validateCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new BadRequestException("CPF não pode ser vazio.");
        }

        // Remove caracteres não numéricos
        String cleanedCpf = cpf.replaceAll("[^0-9]", "");
        
        // Verifica se tem 11 dígitos
        if (cleanedCpf.length() != 11) {
            throw new BadRequestException("CPF deve conter 11 dígitos.");
        }

        // Verifica se todos os dígitos são iguais (CPF inválido)
        if (isAllSameDigits(cleanedCpf)) {
            throw new BadRequestException("CPF inválido.");
        }

        // Calcula e verifica o primeiro dígito verificador
        int digito1 = calculateVerifierDigit(cleanedCpf, 10);
        if (digito1 != Character.getNumericValue(cleanedCpf.charAt(9))) {
            throw new BadRequestException("CPF inválido.");
        }

        // Calcula e verifica o segundo dígito verificador
        int digito2 = calculateVerifierDigit(cleanedCpf, 11);
        if (digito2 != Character.getNumericValue(cleanedCpf.charAt(10))) {
            throw new BadRequestException("CPF inválido.");
        }
    }

    /**
     * Valida CPF e retorna a versão limpa (apenas números)
     * @param cpf CPF a ser validado e limpo
     * @return CPF com apenas números
     * @throws BadRequestException se o CPF for inválido
     */
    protected String validateAndCleanCpf(String cpf) {
        validateCpf(cpf);
        return cpf.replaceAll("[^0-9]", "");
    }

    /**
     * Formata o CPF no padrão XXX.XXX.XXX-XX
     * @param cpf CPF a ser formatado (com ou sem formatação)
     * @return CPF formatado
     * @throws BadRequestException se o CPF for inválido
     */
    protected String formatCpf(String cpf) {
        String cleanedCpf = validateAndCleanCpf(cpf);
        return cleanedCpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    /**
     * Verifica se todos os dígitos do CPF são iguais
     */
    private boolean isAllSameDigits(String cpf) {
        char firstDigit = cpf.charAt(0);
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != firstDigit) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calcula o dígito verificador do CPF
     */
    private int calculateVerifierDigit(String cpf, int weight) {
        int sum = 0;
        for (int i = 0; i < weight - 1; i++) {
            int digit = Character.getNumericValue(cpf.charAt(i));
            sum += digit * (weight - i);
        }

        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }
}