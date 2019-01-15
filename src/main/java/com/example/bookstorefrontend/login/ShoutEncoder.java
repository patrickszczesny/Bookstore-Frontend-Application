package com.example.bookstorefrontend.login;

import org.springframework.security.crypto.password.PasswordEncoder;

public class ShoutEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString()+"!!!";
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(s);
    }
}
