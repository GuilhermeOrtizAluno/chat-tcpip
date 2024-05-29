package Servidor.Services.Validations;

public class UserValidation {
    public static boolean ValidateEmail(String email) {

        if (email.length() < 7 || email.length() > 50) {
            return false;
        }

        // Divide o email em nome de usuário e domínio
        String[] parts = email.split("@");
        if (parts.length != 2) {
            return false;
        }

        String username = parts[0];
        String domain = parts[1];

        // Verifica se o nome de usuário e o domínio atendem aos critérios
        if (username.length() < 1 || domain.length() < 3) {
            return false;
        }

        // Verifica se o domínio contém um ponto
        if (!domain.contains(".")) {
            return false;
        }

        // Verifica se o nome de usuário e o domínio contêm apenas caracteres alfanuméricos e alguns caracteres especiais permitidos
        String allowedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.-_";
        for (char c : username.toCharArray()) {
            if (allowedCharacters.indexOf(c) == -1) {
                return false;
            }
        }
        for (char c : domain.toCharArray()) {
            if (allowedCharacters.indexOf(c) == -1) {
                return false;
            }
        }

        return true;
    }

    public static boolean ValidatePassword(String password) {

        if (password.length() < 3 || password.length() > 8) {
            return false;
        }

        // Verifica se todos os caracteres são dígitos
        for (int i = 0; i < password.length(); i++) {
            if (!Character.isDigit(password.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
